package org.liu.commonsmath3.haversine;

import org.apache.commons.math3.util.FastMath;

public class HaversineExample {

    private static final double EARTH_RADIUS_KM = 6371.0; // 地球平均半径（公里）

    /**
     * 使用 Haversine 公式计算两个经纬度点之间的球面距离
     *
     * @param lat1 点1纬度（度）
     * @param lon1 点1经度（度）
     * @param lat2 点2纬度（度）
     * @param lon2 点2经度（度）
     * @return 距离（公里）
     */
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = FastMath.toRadians(lat1);
        double lon1Rad = FastMath.toRadians(lon1);
        double lat2Rad = FastMath.toRadians(lat2);
        double lon2Rad = FastMath.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = FastMath.pow(FastMath.sin(deltaLat / 2), 2) +
                FastMath.cos(lat1Rad) * FastMath.cos(lat2Rad) *
                        FastMath.pow(FastMath.sin(deltaLon / 2), 2);

        double c = 2 * FastMath.asin(FastMath.sqrt(a));

        return EARTH_RADIUS_KM * c;
    }

    public static void main(String[] args) {
        // 北京到上海的经纬度（近似）
        double beijingLat = 39.907222;
        double beijingLon = 116.391667;
        double shanghaiLat = 31.241667;
        double shanghaiLon = 121.498611;

        double distance = haversineDistance(beijingLat, beijingLon, shanghaiLat, shanghaiLon);
        System.out.printf("北京到上海的距离: %.2f 公里%n", distance);
        //1068.04
    }
}
