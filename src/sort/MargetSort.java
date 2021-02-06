package sort;


import java.util.Arrays;

/**
 * 归并排序（分治法）
 * @author H_Java
 */
public class MargetSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 7, 5, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergetSort(arr, 0, arr.length - 1, temp);
        System.out.println("归并排序后arr >> " + Arrays.toString(arr) );


        int [] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        int[] temp2 = new int[arr2.length];
        mergetSort(arr2, 0, arr2.length - 1, temp2);
        long end = System.currentTimeMillis();
        System.out.println("共耗时 >> " + (end - start) + " 毫秒" );
    }


    /**
     * 分 + 合
     * @param arr 原数组
     * @param left 原数组左侧指针位置
     * @param right 原数组右侧指针位置
     * @param temp 中转数组
     */
    public static void mergetSort(int[] arr, int left, int right, int[] temp) {
        // 当跳出循环时，说明无法在继续分组了，即 只有一组
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergetSort(arr, left, mid, temp);
            // 向右递归分解
            mergetSort(arr, mid + 1, right, temp);
            // 合并
            merget(arr, left, right, mid, temp);

        }
    }


    /**
     * 合并方法
     * @param arr 排序的原始数组
     * @param left 拆分后 左侧指针当前位置
     * @param right 拆分后 右侧指针当前位置
     * @param mid 拆分后 中间指针位置
     * @param temp 中转数组
     */
    public static void merget(int[] arr, int left, int right, int mid, int[] temp) {
        // 左侧初始化索引
        int i = left;
        // 右侧初始化索引
        int j = mid + 1;
        // 指向temp数组的当前索引
        int t = 0;
        // 第一步 ：将左右两侧数据按照规则填充到temp数组中，直到其中任意一边处理完成停止
        while (i <= mid && right >= j) {
            // 如果左边的有序序列当前元素小于或等于右边有序序列的当前元素，将左边的当前元素拷贝的temp元素，
            // 同时后移左侧指针和暂存数组指针
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                // 反之将右侧的代码填充到数组中
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        // 第二步 ：将剩余的数字填充到temp中
        // 左边还有剩余的，全部填充到temp
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }


        // 第三步： 将temp数组元素拷贝到arr中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
