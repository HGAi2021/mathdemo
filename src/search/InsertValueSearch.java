package search;

import java.util.Arrays;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/8 0008 19:44
 * @description 插值查找算法 要求数据有序 适合等差数列
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        // 生成数组
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;

        }
        System.out.println(Arrays.toString(arr));
        int i = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println("下标 >> " + i);
    }

    /**
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        //
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }
        // mid
        int mid = left + (right - left) * ((findVal - arr[left]) / (arr[right] - arr[left]));
        int midVal = arr[mid];
        // 向右查找
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal ) {
            // 向左查找
            return insertValueSearch(arr, left, mid - 1,findVal);
        } else {
            return mid;
        }
    }
}
