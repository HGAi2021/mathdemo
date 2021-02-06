package tree;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/30 0030 16:57
 * @description 线索二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode1 root = new HeroNode1(1, "tom");
        HeroNode1 heroNode2 = new HeroNode1(3, "jack");
        HeroNode1 heroNode3 = new HeroNode1(6, "smith");
        HeroNode1 heroNode4 = new HeroNode1(8, "mary");
        HeroNode1 heroNode5 = new HeroNode1(10, "king");
        HeroNode1 heroNode6 = new HeroNode1(14, "dim");

        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setRight(heroNode6);

        // 测试线索化
        BinaryTree1 binaryTree1 = new BinaryTree1();
        binaryTree1.setRoot(root);
        binaryTree1.threadedNodes(root);
        HeroNode1 left = heroNode5.getLeft();
        System.out.println("leftHeroNode >>> " + left.toString());
        HeroNode1 right = heroNode5.getRight();
        System.out.println("rightHeroNode >>> " + right.toString());

        // 遍历线索二叉树
        binaryTree1.threadedList();


    }
}

/**
 * 实现了线索化功能的二叉树
 */
class BinaryTree1 {
    private HeroNode1 root1;
    /**
     * 前驱节点指针
     * 在递归线索化时, pre总保留前一个节点
     */
    private HeroNode1 pre = null;

    public void setRoot(HeroNode1 root1) {
        this.root1 = root1;
    }

    /**
     * 遍历线索二叉树
     */
    public void threadedList() {
        // 存储当前便利的节点，从root开始
        HeroNode1 node1 = root1;
        while (node1 != null) {
            // 循环找到leftType == 1 的节点，leftType == 1 是线索化后的节点
            while (node1.getLeftType() == 0) {
                node1 = node1.getLeft();
            }

            System.out.println("打印当前节点 >>> " + node1);
            // 如果当前节点的右指针指向的是后继节点, 就一直输出
            while (node1.getRightType() == 1) {
                node1 = node1.getRight();
                System.out.println(node1);
            }

            // 替换这个遍历的节点
            node1 = node1.getRight();

        }
    }

    /**
     * 当前需要线索化的节点
     *
     * @param node1
     */
    public void threadedNodes(HeroNode1 node1) {
        // 判断当前节点是否为空
        if (node1 == null) {
            return;
        }
        // 流程: 先线索化左子树 -> 线索化当前节点<难点> -> 线索化右子树
        threadedNodes(node1.getLeft());
        /*
        处理当前节点的前驱节点
        8节点.left = null;
        8节点.leftType = 1;
         */
        if (node1.getLeft() == null) {
            // 当前节点左指针指向前驱节点, 左指针类型为线索(1)
            node1.setLeft(pre);
            node1.setLeftType(1);
        }
        /*
        处理后继节点
         */
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node1);
            pre.setRightType(1);
        }
        // 每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node1;
        threadedNodes(node1.getRight());
    }

}

class HeroNode1 {
    private int no;
    private String name;
    private HeroNode1 left;
    private HeroNode1 right;
    /**
     * 说明：
     * 0，表明指向左子树/右子树
     * 1，表明指向前驱节点/后继节点
     */
    private int leftType;
    private int rightType;

    public HeroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


}