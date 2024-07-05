package org.liu.demo.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ByteOrderValues;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzs
 * @Date 2024/5/24 15:31
 **/
@MappedTypes({Geometry.class})
@MappedJdbcTypes(JdbcType.BINARY)
public class GeometryTypeHandler extends BaseTypeHandler<Geometry> {
    private static final PrecisionModel PRECISION_MODEL = new PrecisionModel(PrecisionModel.FLOATING);
    private static final Map<Integer, GeometryFactory> GEOMETRY_FACTORIES = new ConcurrentHashMap<>();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Geometry parameter, JdbcType jdbcType) throws SQLException {
        byte[] bytes = serializeGeometry(parameter);
        ps.setBytes(i, bytes);
    }

    @Override
    public Geometry getNullableResult(ResultSet rs, String columnName) throws SQLException {
        byte[] bytes = rs.getBytes(columnName);
        try {
            return deserializeGeometry(bytes);
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Geometry getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        byte[] bytes = rs.getBytes(columnIndex);
        try {
            return deserializeGeometry(bytes);
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Geometry getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        byte[] bytes = cs.getBytes(columnIndex);
        try {
            return deserializeGeometry(bytes);
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    private static Geometry deserializeGeometry(byte[] bytes) throws ParseException {
        if (bytes == null) {
            return null;
        }

        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        int srid = buffer.getInt();
        byte[] geometryBytes = new byte[buffer.remaining()];
        buffer.get(geometryBytes);

        // Thread-safe
        GeometryFactory geometryFactory = GEOMETRY_FACTORIES.computeIfAbsent(srid, i -> new GeometryFactory(PRECISION_MODEL, i));

        // WKBReader is not thread-safe. a ThreadLocal variable can be used instead of creating a reader for each call.
        WKBReader reader = new WKBReader(geometryFactory);
        return reader.read(geometryBytes);
    }

    private byte[] serializeGeometry(Geometry geometry) {
        int srid = geometry.getSRID();
        byte[] bytes = new WKBWriter(2, ByteOrderValues.LITTLE_ENDIAN).write(geometry);
        return ByteBuffer.allocate(bytes.length + 4).order(ByteOrder.LITTLE_ENDIAN)
                .putInt(srid)
                .put(bytes)
                .array();
    }
}
