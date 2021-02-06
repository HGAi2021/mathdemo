import entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树
 */
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode tes1 = new TreeNode(1);
        TreeNode tes2 = new TreeNode(2);
        TreeNode tes3 = new TreeNode(3);
        tes1.setLeftChild(tes2);
        tes1.setRightChild(tes3);
        System.out.println(tes1);

//      int [] a = {1,2,3,4,5};
//        TreeNode tree = createTree(a);
//        System.out.println(tree);
    }

    // 创建二叉树的方法
    public static TreeNode createTree(int[] value){
        List<TreeNode> list = new ArrayList<>();
        for (Integer a :value ) {
            TreeNode treeNode = new TreeNode(a);
            list.add(treeNode);
        }

        // list -> {val1}{val2}{val3}
        //指针
        TreeNode root;// i=0-> root = {val:val1,left:{val2},right:{val3}};
                     //  i=1-> root = {val:val2,left:null,right:null};
        for (int i =0;i<list.size()/2;i++){
            root = list.get(i);
            TreeNode left = list.get(i*2+1);
            root.leftChild = left;
            if (i*2<list.size()){
                TreeNode right = list.get(i*2+2);
                root.rightChild = right;
            }


        }
        return  list.get(0);
    }


}
