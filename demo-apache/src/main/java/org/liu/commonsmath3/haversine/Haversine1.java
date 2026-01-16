package org.liu.commonsmath3.haversine;

public class Haversine1 {
    private static final double EARTH_RADIUS = 6371; // 地球平均半径，单位公里

    /**
     * 计算两个经纬度点之间的距离，单位：公里
     *
     * @param lon1 第一个点的经度
     * @param lat1 第一个点的纬度
     * @param lon2 第二个点的经度
     * @param lat2 第二个点的纬度
     * @return 两点之间的距离（公里）
     */
    public static double calculateDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double radLon1 = Math.toRadians(lon1);
        double radLon2 = Math.toRadians(lon2);

        double deltaLat = radLat2 - radLat1;
        double deltaLon = radLon2 - radLon1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(radLat1) * Math.cos(radLat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static void main(String[] args) {
        // 示例：北京天安门和上海东方明珠的经纬度
        double lon1 = 116.391667; // 北京天安门经度
        double lat1 = 39.907222;  // 北京天安门纬度
        double lon2 = 121.498611; // 上海东方明珠经度
        double lat2 = 31.241667;  // 上海东方明珠纬度

        double distance = calculateDistance(lon1, lat1, lon2, lat2);
        System.out.println("两点之间的距离约为：" + distance + " 公里");
        //1068.041183740238
    }
}
