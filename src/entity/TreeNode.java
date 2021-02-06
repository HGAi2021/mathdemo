package entity;

/**
 * 二叉树 数据结构
 */
public class TreeNode {
    public int val;
    public TreeNode leftChild;
    public TreeNode rightChild;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
