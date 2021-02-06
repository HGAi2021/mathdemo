package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author HGAi
 * @date 2021/2/6 0006 17:16
 *
 * 哈夫曼树代码实现
 */
public class Huffmantree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }
    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("这棵树是空树，不能遍历");
        }
    }

    // 创建赫夫曼树
    public static Node createHuffmanTree(int[] arr) {
        // 遍历arr数组 -> 将arr数组中的每一个元素构建成为一个Node -> 将Node放入ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);
            System.out.println("nodes{} = " + nodes);
            // 取出根节点权值最小的两棵二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 构建新的二叉树
            Node parent  = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 从arrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将parent加入到nodes
            nodes.add(parent);
            System.out.println("处理后 >>> " + nodes.toString());
        }
        // 返回赫夫曼树的root节点
        return nodes.get(0);
    }

}

// 创建节点类
class Node implements Comparable<Node>{
    /**
     * 节点权值
     */
    int value;
    /**
     * 左子节点
     */
    Node left;
    /**
     * 右子节点
     */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    // 从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
