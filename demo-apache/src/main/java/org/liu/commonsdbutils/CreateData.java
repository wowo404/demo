package org.liu.commonsdbutils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.Date;

/**
 * @Author lzs
 * @Date 2022/12/12 19:27
 **/
public class CreateData {

    private static final String[] phonePrefix = {"13", "14", "15", "16", "17", "18", "19"};
    private static final String[] type = {"电脑裁板机",
            "封边机",
            "六排钻",
            "立式单轴木工铣床",
            "单头直榫开榫机",
            "双端榫头机",
            "榫槽机",
            "砂光机",
            "梳齿榫开榫机",
            "精密推台锯",
            "快速断料锯",
            "四面木工刨床",
            "三排多轴木工钻床",
            "四头铰链机",
            "梳齿对接机",
            "细木工带锯",
            "单头直榫开榫机",
            "立式单轴木工镂铣机",
            "立式单轴木工铣床",
            "电脑裁板机",
            "卧带式磨光机",
            "宽带砂光机",
            "数控多轴榫槽机",
            "双端数控四轴榫头机",
            "六排多轴钻",
            "电脑裁板锯",
            "精密推台锯",
            "多排多轴钻",
            "木工平刨床",
            "小带机锯",
            "断料锯",
            "单片锯",
            "四面刨铣机",
            "木工多排多轴钻床",
            "六头摇摆机",
            "三排多轴木工钻床",
            "数控榫头机",
            "推台锯",
            "活页钻孔机",
            "多排多轴木工钻床",
            "数控双边铣",
            "刨砂机",
            "木材切断机",
            "两面刨",
            "自动修边机",
            "自动双面刨木机",
            "卧式带锯机",
            "梳齿榫开榫机",
            "短料型木线砂光机（四面砂光机）",
            "直线铣砂边机",
            "四排多轴钻",
            "三排多轴钻",
            "双端四头数控开榫机",
            "1.35 米砂光机",
            "1.3 米砂光机",
            "0.7 米砂光机",
            "自动封边机",
            "三砂架上浮式宽带式砂光机",
            "直线砂边机",
            "异形砂光机",
            "单头出榫机",
            "榫头机",
            "卧式双端榫槽机",
            "细木工带锯",
            "卧式自动榫头机",
            "砂光机",
            "刨机",
            "单片纵锯机",
            "裁料锯",
            "四面木工刨床",
            "拼接机",
            "UV 辊涂机",
            "双端剪锯机",
            "排钻孔机",
            "自动双头钻",
            "整体异形砂光机",
            "异形打磨生产线",
            "封边机",
            "双端数控出榫机",
            "自动单片纵锯机",
            "自动多片纵锯机",
            "双面木工刨床"};

    public static void main(String[] args) throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUrl("jdbc:mysql://192.168.0.201:3306/userplatform?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&autoReconnect=true");
        dataSource.setUrl("jdbc:mysql://124.71.75.172:33306/userplatform?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&autoReconnect=true");
        dataSource.setUser("root");
//        dataSource.setPassword("mailink");
        dataSource.setPassword("Jxbs@747");
        QueryRunner runner = new QueryRunner(dataSource);

        for (int i = 0; i < 20; i++) {
            StringBuilder sqlBuilder = new StringBuilder("insert into temp_data(phone, first_login_time, login_device_type, imei, is_connecting) values");
            sqlBuilder.append("(");
            sqlBuilder.append("'").append(RandomUtil.randomEle(phonePrefix)).append(RandomUtil.randomNumbers(9)).append("'");
            sqlBuilder.append(",");
            sqlBuilder.append("'").append(RandomUtil.randomDate(new Date(), DateField.SECOND, -5 * 365 * 24 * 60 * 60, -5 * 24 * 60 * 60)).append("'");
            sqlBuilder.append(",");
            sqlBuilder.append("'").append(RandomUtil.randomEle(type)).append("'");
            sqlBuilder.append(",");
            sqlBuilder.append("'").append("35788").append(RandomUtil.randomNumbers(10)).append("'");
            sqlBuilder.append(",");
            sqlBuilder.append("1");
            sqlBuilder.append(")");

            System.out.println(sqlBuilder.toString());
            runner.insert(sqlBuilder.toString(), new MapListHandler());
        }

    }

}
