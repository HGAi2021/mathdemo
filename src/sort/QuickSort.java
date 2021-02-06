package sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr,0,arr.length-1);
        System.out.println("arr >> " + Arrays.toString(arr));

        int [] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr2,0,arr2.length-1);
        long end = System.currentTimeMillis();
        System.out.println("arr >> " + Arrays.toString(arr2));
        System.out.println("共耗时 >> " + (end - start) + " 毫秒" );
    }
    // 快速排序法
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right;// 右下标
        // pivot 中轴值
        int pivot = arr[(left + right) / 2];

        int temp = 0; // 临时变量，交换时使用
//        {1,5,6,4,4,5,6} 4  1.2.4.4.5.6
        // while 循环的目的 让 比pivot值小的放到左边，大的放在右边
        while (l < r) {
            // 在pivot的左边一直找直到找到 大于等于pivot值，退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 同上 反之
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 交换 l >= r 说明pivot 左右两边值，已经按照左边全部小于等于pivot的值，右侧全部大于pivot值
            if (l >= r) {
                break;
            }
            //交换
            temp  = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现arr[l] == pivot，前移 r--
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换完成后，发现这个arr[r]  == pivot，l++
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 如果l == r ，必须 l++ r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
