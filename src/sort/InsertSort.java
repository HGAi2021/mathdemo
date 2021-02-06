package sort;

import java.util.Arrays;

/**
 * 插入排序
 */

public class InsertSort {
    public static void main(String[] args) {
        int [] arr = {101, 34, 119, 1};
//        insertSort(arr);
        int [] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        insertSort1(arr2);
        long end = System.currentTimeMillis();
        System.out.println("共耗时 >> " + (end-start) + "毫秒");
    }

    //插入排序
    public static void insertSort( int [] arr ) {
        //第一轮 {101, 34, 119, 1}; => {34, 101, 119, 1}
        System.out.println("第一轮 >> ");
        // 定义待插入的数
        int insertVal = arr[1];
        // 即 arr[1] 的前面这个数的下标
        int insertIndex = 1 - 1;
        //说明
        // 1、insertIndex >= 0，保证在insertVal 找插入位置，不越界
        // 2、insertVal < arr[insertIndex] 待插入的数，还没有找到插入的位置
        // 3、将arr[insertIndex]后移

        while ( insertIndex >= 0 && insertVal < arr[ insertIndex ] ) {
            arr [ insertIndex + 1 ] = arr [ insertIndex ];
            insertIndex --;
        }
        // 当退出while循环时，说明插入的位置找到，insertIndex + 1;
        arr [ insertIndex + 1] = insertVal;
        System.out.println("第一轮数组排序后 >> " + Arrays.toString(arr));

        System.out.println("第二轮 >> ");
        insertVal = arr [ 2 ];
        insertIndex = 2 - 1;

        while ( insertIndex >= 0 && insertVal < arr [ insertIndex ] ) {
            arr [ insertIndex + 1 ] = arr [ insertIndex ];
            insertIndex --;
        }
        arr [ insertIndex + 1] = insertVal;
        System.out.println("第二轮数组排序后 >> " + Arrays.toString(arr));


        System.out.println("第三轮 >> ");
        insertVal = arr [ 3 ];
        insertIndex = 3 - 1;

        while ( insertIndex >= 0 && insertVal < arr [ insertIndex ] ) {
            arr [ insertIndex + 1 ] = arr [ insertIndex ];
            insertIndex --;
        }
        arr [ insertIndex + 1] = insertVal;
        System.out.println("第三轮数组排序后 >> " + Arrays.toString(arr));




    }

    // 代码简化
    public static void insertSort1( int [] arr ) {
        for ( int i = 1; i < arr.length; i++) {
            //第一轮 {101, 34, 119, 1}; => {34, 101, 119, 1}
//            System.out.println("第" + i + "轮 >> ");
            // 定义待插入的数
            int insertVal = arr[i];
            // 即 arr[1] 的前面这个数的下标
            int insertIndex = i - 1;
            //说明
            // 1、insertIndex >= 0，保证在insertVal 找插入位置，不越界
            // 2、insertVal < arr[insertIndex] 待插入的数，还没有找到插入的位置
            // 3、将arr[insertIndex]后移

            while ( insertIndex >= 0 && insertVal < arr[ insertIndex ] ) {
                arr [ insertIndex + 1 ] = arr [ insertIndex ];
                insertIndex --;
            }
            // 当退出while循环时，说明插入的位置找到，insertIndex + 1;
            if (insertIndex + 1 != i) {
                arr [ insertIndex + 1] = insertVal;
            }

//            System.out.println("第" + i + "轮数组排序后 >> " + Arrays.toString(arr));
        }
    }
}
