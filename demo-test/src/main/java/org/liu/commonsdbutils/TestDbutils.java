package org.liu.commonsdbutils;

import cn.hutool.core.io.FileUtil;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author lzs
 * @Date 2022/3/4 14:57
 **/
public class TestDbutils {

    private static final String[] tables = {"bis_collection", "bis_goods_record", "bis_oper_log", "bis_order", "bis_order_product",
            "bis_promoter", "bis_sales_goods", "bis_sales_man", "bis_shop", "bis_shopping_cart", "goods_brand", "goods_brand_contact",
            "goods_category", "goods_label", "goods_sku", "goods_sku_spec_value", "goods_space", "goods_spec", "goods_spec_value",
            "goods_spu", "goods_style", "sys_config", "sys_config_storage", "sys_dept", "sys_dict_data", "sys_dict_type",
            "sys_logininfor", "sys_menu", "sys_notice", "sys_oper_log", "sys_post", "sys_role", "sys_role_dept", "sys_role_menu",
            "sys_tenant", "sys_user", "sys_user_address", "sys_user_post", "sys_user_role", "tv_qrcode", "wx_user"};

    public static void main(String[] args) throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://124.71.75.172:33306/shop-sim?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&autoReconnect=true");
        dataSource.setUser("jxbs");
        dataSource.setPassword("Jxbs@!197");
        QueryRunner runner = new QueryRunner(dataSource);

        for (String table : tables) {
            File file = new File("D:\\data\\export-db\\shop-sim\\" + table + ".sql");
            String sql = "select * from " + table;
            boolean haveTenantIdColumn = checkHaveTenantIdColumn(runner, table);
            if (haveTenantIdColumn) {
                sql += " where tenant_id in (1, 76)";
            }
            List<Map<String, Object>> result = runner.query(sql, new MapListHandler());
            for (Map<String, Object> map : result) {
                String insertSql = "insert into " + table + "(";
                List<String> columns = new ArrayList<>();
                List<String> values = new ArrayList<>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (null == entry.getValue()) {
                        continue;
                    }
                    columns.add(entry.getKey());
                    values.add(convert(entry.getValue()));
                }
                insertSql = insertSql + String.join(",", columns) + ") values (" + String.join(",", values) + ");";
                FileUtil.appendLines(Collections.singletonList(insertSql), file, Charset.defaultCharset());
            }
        }
    }

    private static boolean checkHaveTenantIdColumn(QueryRunner runner, String table) throws SQLException {
        String sql = "select * from information_schema.`COLUMNS` where TABLE_SCHEMA = 'shop-sim' and TABLE_NAME = '" + table + "' and COLUMN_NAME = 'tenant_id'";
        Map<String, Object> map = runner.query(sql, new MapHandler());
        if (null == map) {
            return false;
        }
        return !map.isEmpty();
    }

    private static String convert(Object value) {
        if (value instanceof Integer || value instanceof Long) {
            return String.valueOf(value);
        }
        if (value instanceof LocalDateTime || value instanceof Date || value instanceof LocalDate) {
            return "'" + value.toString() + "'";
        }
        if (value instanceof String) {
            return "'" + value.toString() + "'";
        }
        if (value instanceof Double || value instanceof BigDecimal) {
            return value.toString();
        }
        if (value instanceof Boolean) {
            return (Boolean)value ? "1" : "0";
        }
        return null;
    }

}
