package search;

import java.util.Arrays;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/13 0013 17:45
 * @description 斐波那契算法
 */
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = fibSearch(arr, 1234);
        System.out.println("index  >> " + i);
    }
    // 因为后面我们mid = low + F(k - 1) - 1,需要使用斐波那契数列，因此我们

    /**
     * 得到一个长度为20的斐波那契数列
     * @return
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++ ) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
    //  斐波那契算法

    /**
     *
     * @param a 数组
     * @param key 查找的关键码
     * @return 没有返回 -1 ，有则返回下标
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int height = a.length - 1;
        int k = 0; // 表示斐波那契分割数值的下标
        int mid = 0; // 存放mid值
        int f[] = fib(); // 获得斐波那契数列

        // 获取到斐波那契分割数值的下标
        while (height > f[k] - 1) {
            k++;
        }

        // 因为f[k] 值 可能大于数组的长度 a.length，因此我们需要使用Arrays类，构建一个新的数组，并指向a
        // 不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a,f[k]);
        // 实际上需要使用a数组最后数填充 temp
        // temp = {1, 8, 10, 89, 1000, 1234, 0, 0, 0 } => {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234}
        for (int i = height + 1; i < temp.length; i++) {
            temp[i] = a[height];
        }
        // 使用while来循环处理，找到我们的数key
        while (low <= height) {
            mid = low + f[k-1] - 1;
            if (key < temp[mid]) {
                //向数组的左侧查找
                height = mid -1;
                //说明
                // 1、全部元素 = 前面元素 + 后面的元素
                // 2、f[k] = f[k-1] + f[k-2]
                // 因为前面 有f[k-1]个元素, 所以我们可以继续拆分 f[k-1] = f[k-2] + f[k-3];
                // 即在f[k-1]的前面继续查找 k--;
                // 即下次循环mid = low + f[k-1-1] -1;
                k--;
            } else if (key > temp[mid]){
                // 向右查找
                low = mid + 1;
                // 说明
                // 1、全部元素 = 前面元素 + 后面的元素
                // 2、f[k] = f[k-1] + f[k-2];
                // 3、因为后面我们有f[k-2],所以我们可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                // 4、即在f[k-2] 的前面进行查找 k -=2;
                // 5、即下次循环 mid = low + f[k-1-2] -1
                k -= 2;
            } else {
                // 需要确定，返回的是哪个下标
                if (mid <= height) {
                    return mid;
                } else {
                    return height;
                }
            }
        }

        return -1;
    }
}
