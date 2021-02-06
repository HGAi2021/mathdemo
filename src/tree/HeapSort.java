package tree;

import java.util.Arrays;

/**
 * @author AiGH
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        // 升序排列 -> 大顶堆
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    // 堆排序
    public static void heapSort(int[] arr) {
        System.out.println("堆排序 >>> ");
        // 分步完成
        adjustHeap(arr,1, arr.length);
        System.out.println("第一次 " + Arrays.toString(arr));
        adjustHeap(arr,0, arr.length);
        System.out.println("第二次 " + Arrays.toString(arr));
        //完成最终代码
        // 完成大顶堆
        int temp = 0;
        for (int i = arr.length / 2 -1; i >= 0; i-- ) {
            adjustHeap(arr, i, arr.length);
        }
        // 将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
        // 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
        for(int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0, j);
        }
        System.out.println("最终 >>> " + Arrays.toString(arr));
    }

    /**
     * 将二叉树(数组)调整成为大顶堆
     * @param arr 二叉树(数组)
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 取出当前元素的值，变保存在临时量
        int temp = arr[i];
        // k = i * 2 + 1; k是i的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 左子节点的值 小于 右子节点的值
            if (k + 1 <length && arr[k] < arr[k + 1]) {
                // k 指向右子节点
                k++;
            }
            if (arr[k] > temp) {
                // 把较大的值赋给当前节点
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        // 当 for 循环结束后, 我们已经将 i 为 父节点的树的最大值 放在了顶部（局部）,将temp 放在调整后的位置
        arr[i] = temp;
    }

}
