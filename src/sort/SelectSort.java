package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int [] arr = { 101, 34, 119, 1 };

        int [] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }

        System.out.println("原数组是 >> "+Arrays.toString(arr));
//        selectSort(arr);
//        selectSort1(arr);
        long start = System.currentTimeMillis();
        selectSort1(arr2);
        long end = System.currentTimeMillis();
        System.out.println("共耗时 >> "+ (end - start) + "毫秒");
    }
    // 选择排序
    public static void selectSort(int [] arr) {
        // 假定最小值下标为0
        int minIndex = 0;
        // 假定最小数是第一个
        int min = arr [ 0 ];

        //逐步 101,34,119,1
        //第一轮 1,34,119,101
        System.out.println("第一轮 >> ");
        for ( int j = 0 + 1; j < arr.length; j++ ) {
            if ( min > arr [ j ] ) { //说明假定最小值并不是最小的
                min = arr [ j ]; // 重置最小值
                minIndex  = j; //重置最小值下标

            }
        }
        //将最小值，放在arr [ 0 ]，即 交换
        if (minIndex != 0) {
            arr [ minIndex ] = arr [ 0 ];
            arr [ 0 ] = min;
        }
        System.out.println("第一轮后数组是 >> ");
        System.out.println( Arrays.toString( arr ) );

        System.out.println("第二轮 >> ");
        // 假定最小值下标为0
        minIndex = 1;
        // 假定最小数是第一个
        min = arr [ 1 ];
        for ( int j = 1 + 1; j < arr.length; j++ ) {
            if ( min > arr [ j ] ) { //说明假定最小值并不是最小的
                min = arr [ j ]; // 重置最小值
                minIndex  = j; //重置最小值下标

            }
        }
        //将最小值，放在arr [ 0 ]，即 交换
        if (minIndex != 1) {
            arr [ minIndex ] = arr [ 1 ];
            arr [ 1 ] = min;
        }
        System.out.println("第二轮后数组是 >> ");
        System.out.println( Arrays.toString( arr ) );//1,34,119,101

        System.out.println("第三轮 >> ");
        // 假定最小值下标为0
        minIndex = 2;
        // 假定最小数是第一个
        min = arr [ 2 ];
        for ( int j = 2 + 1; j < arr.length; j++ ) {
            if ( min > arr [ j ] ) { //说明假定最小值并不是最小的
                min = arr [ j ]; // 重置最小值
                minIndex  = j; //重置最小值下标

            }
        }
        //将最小值，放在arr [ 0 ]，即 交换
        if (minIndex != 2) {
            arr [ minIndex ] = arr [ 2 ];
            arr [ 2 ] = min;
        }
        System.out.println("第三轮后数组是 >> ");
        System.out.println( Arrays.toString( arr ) );//1,34,119,101
    }

    public static void selectSort1(int [] arr) {
        for ( int i = 0; i < arr.length - 1; i++) {
            // 假定最小值下标为0
            int minIndex = i;
            // 假定最小数是第一个
            int min = arr [ i ];

            //逐步 101,34,119,1
            //第一轮 1,34,119,101
//            System.out.println("第"+ ( i + 1 ) +"轮 >> ");
            for ( int j = i + 1; j < arr.length; j++ ) {
                if ( min > arr [ j ] ) { //说明假定最小值并不是最小的
                    min = arr [ j ]; // 重置最小值
                    minIndex  = j; //重置最小值下标

                }
            }
            //将最小值，放在arr [ 0 ]，即 交换
            if (minIndex != i) {
                arr [ minIndex ] = arr [ i ];
                arr [ i ] = min;
            }
/*            System.out.println("第"+ ( i + 1 ) +"轮后数组是 >> ");
            System.out.println( Arrays.toString( arr ) );*/
        }

    }
}
