package algorithm.search;

/**
 * 二分查找法
 */
public class BinarySearch {

    /**
     * @param array 必须是一个有序的数组
     * @param a     被查找的项
     * @return
     */
    public static int search(int[] array, int a) {
        int lo = 0;
        int hi = array.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;//中间位置
            if (array[mid] == a) {
                return mid + 1;
            } else if (array[mid] < a) { //向右查找
                lo = mid + 1;
            } else { //向左查找
                hi = mid - 1;
            }
        }
        return -1;
    }

}
