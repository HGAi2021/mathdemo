package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr1 [] = {3, 9, -1, 10, -2};
        int arr1 [] = {3, 9, 12, 10, 122};
//        int arr1 [] = {1, 2, 3, 4, 5};
        // 冒泡排序 演变过程
        // 1、第一趟排序，就是将最大数排在最后，
        int temp = 0;//临时变量，交换用
        boolean flag = false;
        for (int j = 0; j < arr1.length - 1; j++) {
            for (int i = 0; i < arr1.length - 1 - j; i++) {
                // 如果前面的数比后面的数大，则交换
                if (arr1[i] > arr1[i + 1]) {
                    flag = true;
                    temp = arr1[i];
                    arr1[i] = arr1[i + 1];
                    arr1[i + 1] = temp;
                }
            }
            System.out.println("第"+(j+1)+"趟排序后的数组");
            System.out.println(Arrays.toString(arr1));
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
        System.out.println("演化过程 >> ");
        int arr [] = {3, 9, -1, 10, -2};
        for (int i = 0; i < arr.length - 1 - 0; i++) {
            // 如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i+1]) {
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第一趟排序后的数组");
        System.out.println(Arrays.toString(arr));
        System.out.println("第二趟排序后的数组");
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            // 如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i+1]) {
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));

        System.out.println("第三趟排序后的数组");
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            // 如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));


        System.out.println("第四趟排序后的数组");
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            // 如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));


        System.out.println("测试冒泡算法时间 >> ");
        int [] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println("排序前 >> "+format);

        bubbleSort(arr2);

        Date date1 = new Date();
        String format1 = simpleDateFormat.format(date1);
        System.out.println("排序后 >> "+format1);
    }

    public static void bubbleSort(int[] arr1) {
        int temp = 0;//临时变量，交换用
        boolean flag = false;
        for (int j = 0; j < arr1.length - 1; j++) {
            for (int i = 0; i < arr1.length - 1 - j; i++) {
                // 如果前面的数比后面的数大，则交换
                if (arr1[i] > arr1[i + 1]) {
                    flag = true;
                    temp = arr1[i];
                    arr1[i] = arr1[i + 1];
                    arr1[i + 1] = temp;
                }
            }
//            System.out.println("第"+(j+1)+"趟排序后的数组");
//            System.out.println(Arrays.toString(arr1));
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

}
