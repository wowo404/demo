package algorithm.search;

/**
 * 插值查找法
 * https://blog.csdn.net/Alian_1223/article/details/126971030
 *
 * @Author lzs
 * @Date 2023/7/5 9:25
 **/
public class InterpolationSearch {

    public static int interpolationSearch(int[] arr, int targetValue){
        int startIndex = 0;
        int endIndex = arr.length - 1;
        while (startIndex <= endIndex && targetValue >= arr[startIndex] && targetValue <= arr[endIndex]) {
            int midIndex = (targetValue - arr[startIndex]) / (arr[endIndex] - targetValue) * (endIndex - startIndex) + startIndex;
            int pos = startIndex + (((endIndex - startIndex) / (arr[endIndex] - arr[startIndex])) * (targetValue - arr[startIndex]));
            System.out.println("公式1计算出的位置在" + midIndex + "，公式2计算的位置在" + pos);
            if (arr[midIndex] > targetValue) {
                endIndex = midIndex - 1;
            } else if (arr[midIndex] < targetValue) {
                startIndex = midIndex + 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,23,34,35,45,65,87,345,666};
        int index = interpolationSearch(arr, 87);
        System.out.println(index);
    }

}
