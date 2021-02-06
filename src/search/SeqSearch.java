package search;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/6 0006 23:25
 * @description 查找 -  线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};

        int index = seqSearch(arr, 11);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为 = " + index);
        }
    }

    public static int seqSearch(int[] arr, int value) {
        //线性查找就是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
