package sort;

import java.util.Arrays;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/6 0006 19:12
 * @description 基数排序法（桶排序）
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        radixSort1(arr);

        int [] arr2 = new int[80000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        radixSort1(arr2);
        long end = System.currentTimeMillis();
        System.out.println("arr >> " + Arrays.toString(arr2));
        System.out.println("共耗时 >> " + (end - start) + " 毫秒" );





    }

    /**
     * v1 版本
     * 基数排序方法 逐步
     * @param arr 数组
     */
    public static void radixSort(int[] arr) {
        // 第一轮 针对每个元素的个位进行排序
        System.out.println("第一轮排序 >> ");
        // 定义一个二维数组，表示10 个桶，每个桶就是一个一维数组
        // 说明
        // 1、二维数组包含10个一维数组
        // 2、防止数据溢出，每个一维数组(桶)，大小定为arr.length
        int[][] bucket = new int[10][arr.length];

        // 记录每个桶中存放多少数据
        // 例如：bucketElementCount[0] 记录的是 => bucket[0]桶中存放的数据量
        int[] bucketElementCount = new int[10];

        // 第一轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个数的个位的值
            int digitOfElement = arr[j] / 1 % 10;
            // 放入桶中
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];

            bucketElementCount[digitOfElement]++;
        }
        // 按照桶的顺序（一维数组下标依次取出数据，放入原来数组）
        int index = 0;
        // 遍历每个桶，并将桶中的数据，放入到原数组中
        for (int k = 0; k < bucketElementCount.length; k++) {
            //如果桶中有数据放入到原数组
            if (bucketElementCount.length != 0) {
                // 循环桶 k桶 即 第k个一维数组，放入数据
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            // 第一轮处理后需要将每个bucketElementCount[k] = 0 ！！！
            bucketElementCount[k] = 0;
        }

        System.out.println("第一轮对个位的排序处理 >> " + Arrays.toString(arr));


        // 第二轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个数的个位的值
            int digitOfElement = arr[j] / 10 % 10;
            // 放入桶中
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];

            bucketElementCount[digitOfElement]++;
        }
        // 按照桶的顺序（一维数组下标依次取出数据，放入原来数组）
        index = 0;
        // 遍历每个桶，并将桶中的数据，放入到原数组中
        for (int k = 0; k < bucketElementCount.length; k++) {
            //如果桶中有数据放入到原数组
            if (bucketElementCount.length != 0) {
                // 循环桶 k桶 即 第k个一维数组，放入数据
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            // 第二轮处理后需要将每个bucketElementCount[k] = 0 ！！！
            bucketElementCount[k] = 0;
        }
        System.out.println("第二轮对十位的排序处理 >> " + Arrays.toString(arr));


        // 第三轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个数的个位的值
            int digitOfElement = arr[j] / 100 % 10;
            // 放入桶中
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];

            bucketElementCount[digitOfElement]++;
        }
        // 按照桶的顺序（一维数组下标依次取出数据，放入原来数组）
        index = 0;
        // 遍历每个桶，并将桶中的数据，放入到原数组中
        for (int k = 0; k < bucketElementCount.length; k++) {
            //如果桶中有数据放入到原数组
            if (bucketElementCount.length != 0) {
                // 循环桶 k桶 即 第k个一维数组，放入数据
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            // 第二轮处理后需要将每个bucketElementCount[k] = 0 ！！！
            bucketElementCount[k] = 0;
        }

        System.out.println("第三轮对百位的排序处理 >> " + Arrays.toString(arr));
    }

    /**
     * v2 升级版
     * @param arr
     */
    public static void radixSort1(int[] arr) {


        // 得到数组中最大数的位数
        // 假设 第一个数就是最大数
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 得到最大数是几位位数
        int maxLength = (max + "").length();

        // 定义一个二维数组，表示10 个桶，每个桶就是一个一维数组
        // 说明
        // 1、二维数组包含10个一维数组
        // 2、防止数据溢出，每个一维数组(桶)，大小定为arr.length
        int[][] bucket = new int[10][arr.length];

        // 记录每个桶中存放多少数据
        // 例如：bucketElementCount[0] 记录的是 => bucket[0]桶中存放的数据量
        int[] bucketElementCount = new int[10];

        // 使用循环处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                // 取出每个数的个位的值
                int digitOfElement = arr[j] / n % 10;
                // 放入桶中
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];

                bucketElementCount[digitOfElement]++;
            }
            // 按照桶的顺序（一维数组下标依次取出数据，放入原来数组）
            int index = 0;
            // 遍历每个桶，并将桶中的数据，放入到原数组中
            for (int k = 0; k < bucketElementCount.length; k++) {
                //如果桶中有数据放入到原数组
                if (bucketElementCount.length != 0) {
                    // 循环桶 k桶 即 第k个一维数组，放入数据
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                // 第一轮处理后需要将每个bucketElementCount[k] = 0 ！！！
                bucketElementCount[k] = 0;
            }
//            System.out.println("第" +(i + 1)+ "轮对个位的排序处理 >> " + Arrays.toString(arr));
        }
    }

}
