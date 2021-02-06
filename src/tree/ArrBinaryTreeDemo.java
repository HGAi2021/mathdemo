package tree;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/30 0030 15:24
 * @description 顺序存储2叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}

/**
 * 编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
 */
class ArrBinaryTree {
    /**
     * 存储数据节点的数组
     */
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     * @param index 数组下标
     */
    public void preOrder(int index) {

        //如果数组为空, 或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空, 不能二叉树前序遍历");

        }

        //输出当前这个元素
        System.out.println("当前元素 >>> " + arr[index]);

        //向左递归
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);

        }
        //向右递归
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);

        }

    }

}

