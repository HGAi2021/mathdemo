package sort;

import java.util.Arrays;

/**
 * 希尔排序 -> 交换法（效率不高）
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);

        int [] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
//        shellSort2(arr2);
        shellSort3(arr2);
        long end = System.currentTimeMillis();
        System.out.println("共耗时 >> " + (end - start) + " 毫秒" );
    }

    public static void shellSort(int [] arr) {
        //希尔排序 第一轮 将十个数据分成5组
        System.out.println("第一轮 >> ");
        int temp = 0;
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素(共 5组，每组2个元素)，步长 5
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的元素，交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("第一轮结果 >> " + Arrays.toString(arr));

        // 第二轮 将十个数据分成了5/2组，两组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
            System.out.println(" >> " + Arrays.toString(arr));
        }
        System.out.println("第二轮结果 >> " + Arrays.toString(arr));


        // 第三轮 将十个数据分成了一组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println(" >> " + Arrays.toString(arr));
        }
        System.out.println("第三轮结果 >> " + Arrays.toString(arr));



    }

    public static void shellSort2(int [] arr)   {
        //希尔排序
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共 gap组，)，步长 gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的元素，交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序 >> " + gap +" 分组 " + Arrays.toString(arr) );
        }
    }

    // 对 交换式的希尔排序进行优化 -> 移位法
    public static void shellSort3(int [] arr)   {
        //希尔排序
        int temp = 0;
        // 增量gap，逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组 进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 当退出while循环后，就给Temp找到了插入的位置
                    arr[j] = temp;
                }
            }
//            System.out.println("希尔排序 >> " + gap +" 分组 " + Arrays.toString(arr) );
        }
    }
}
