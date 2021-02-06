package search;

import java.util.ArrayList;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/7 0007 22:43
 * @description 二分法
 * 注意：二分法前提是该数组是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(arr, 0, arr.length - 1, 1001);
        System.out.println("下标 >> " + index);

        int arr2[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1000, 1234};
        ArrayList<Integer> integers = binarySearch2(arr2, 0, arr2.length - 1, 1000);
        System.out.println("下标 >> " + integers.toString());
    }
    // 二分查找算法
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        // 向右递归
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1,right,findVal);
        } else if (findVal < midVal) {
            //向 左递归
            return binarySearch(arr, left, mid-1, findVal);
        } else {
            return mid;
        }
    }

    /**
     *
     * 思路分析：
     * 1、在找到mid 索引值，不要马上返回
     * 2、向mid索引值左侧扫描，将所有满足1000的元素的下标加入到集合arrayList中
     * 3、向mid索引值右侧扫描，将所有满足1000的元素的下标加入到集合arrayList中
     * 4、将ArrayList返回
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        // 向右递归
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1,right,findVal);
        } else if (findVal < midVal) {
            //向 左递归
            return binarySearch2(arr, left, mid-1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足1000的元素的下标加入到ArrayList中
            int temp = mid -1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    // 退出
                    break;
                }
                // 否则，就把temp 放入到 resIndexList
                resIndexList.add(temp);
                // temp 左移
                temp -= 1;
            }
            resIndexList.add(mid);
            // 向右边扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    // 退出
                    break;
                }
                // 否则，就把temp 放入到 resIndexList
                resIndexList.add(temp);
                // temp 左移
                temp += 1;
            }
            return resIndexList;
        }

    }

}
