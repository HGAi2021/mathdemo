package tree;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/26 0026 21:21
 * @description
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {

        // 先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");
        HeroNode node6 = new HeroNode(6,"张飞");

        // 说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        node2.setLeft(node6);

        //测试
        binaryTree.setRoot(root);

        System.out.println("前序遍历 >>> ");
        binaryTree.preOrder();
        System.out.println("中序遍历 >>> ");
        binaryTree.infixOrder();
        System.out.println("后序遍历 >>> ");
        binaryTree.postOrder();

        // 测试 查找id 为6 的节点
        // 前序查找
        binaryTree.preOrderSearch(6);
        // 中序查找 3次
        binaryTree.infixOrderSerach(6);
        // 后序查找 2次
        binaryTree.postOrderSearch(6);

        // 测试 删除节点
        binaryTree.delNode(3);

        System.out.println("后序遍历 >>> ");
        binaryTree.postOrder();

    }
}
//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 删除节点
    public void delNode(int no) {
        //判断root是否为空
        if (this.root != null) {
            // 判断root是否为要删除的节点
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 后续遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序查找
    public void preOrderSearch(int no) {
        if (this.root != null) {

            HeroNode heroNode = this.root.preOrderSearch(no);
            System.out.println("前序查找到的节点信息 >>> " + heroNode.toString());
        } else {
            System.out.println("二叉树为空，无法查找");
        }
    }
    // 中序查找
    public void infixOrderSerach(int no) {
        if (this.root != null) {
            HeroNode heroNode = this.root.infixOrderSerach(no);
            System.out.println("中序查找到的节点信息 >>>> " + heroNode.toString());
        } else {
            System.out.println("二叉树为空，无法直接查找");
        }
    }
    // 后序查找
    public void postOrderSearch(int no) {
        if (this.root != null) {
            HeroNode heroNode = this.root.postOrderSearch(no);
            System.out.println("后序查找到的节点信息 >>>> " + heroNode.toString());
        } else {
            System.out.println("二叉树为空，无法直接查找");
        }
    }
}

// 先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    // 默认 null
    private HeroNode left;
    // 默认 null
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    // 递归删除节点
    //1、如果删除的节点是叶子节点,则删除该节点
    //2、如果删除的节点是非叶子节点,则删除该子树
    public void delNode(int no) {
        // 判断 左字节是否为空，是否为该删除节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 判断 右子节点是否为空，是否为该删除的节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 向左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 向右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    // 前序遍历
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    // 中序遍历
    public void infixOrder() {

        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        // 输出父节点
        System.out.println(this);

        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    // 后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param no 编号no
     * @return 如果找到返回该Node，如果没有找到就返回 null
     */
    public HeroNode preOrderSearch(int no) {
        // 比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        // 判断当前节点的左子节点是否为空，不为空则递归前序查找
        // 如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        // 说明左子树上找到了
        if (resNode != null) {
            return resNode;
        }
        // 当前节点的右子节点是否为空，如果不空，则继续向右查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        // 无论是否找到都返回，如果找到返回该Node节点，如果没有则为null
        return resNode;
    }

    /**
     * 中序查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSerach(int no) {
        // 判断当前节点的左子节点是否为空，如果不为空，则继续向左递归查找
        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.infixOrderSerach(no);
        }

        // 如果为null 说明没有找到，如果不为null 说明找到了该节点。
        if (resNode != null) {

            return  resNode;
        }
        // 判断当前节点是否是要找的节点 是则返回当前节点，否则，继续遍历。
        if (this.no == no) {
            return this;
        }
        // 否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSerach(no);
        }
        // null 说明没有找到,如果不为null 说明找到了该节点
        return resNode;
    }

    public HeroNode postOrderSearch(int no) {
        // 判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        // 说明在左子树找到
        if (resNode != null) {
            return  resNode;
        }
        // 如果左子树没有找到，则向右子树递归进行后序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //如果左右子树都没有找到
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

}


