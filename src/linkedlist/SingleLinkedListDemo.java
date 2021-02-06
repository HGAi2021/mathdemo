package linkedlist;

/**
 * @author H_Java
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode heroNode1 = new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode heroNode3 = new HeroNode(3,"吴用","智多星");
        HeroNode heroNode4 = new HeroNode(3,"林冲","豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);
        //显示链表
        singleLinkedList.list();

    }
}

//定义一个LinkedList来管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动。 不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    // 思路：不考虑编号顺序时，
    // 1、 找到当前链表的最后节点
    // 2、 将最后这个节点的next域 指向 新的节点即可。
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量
        HeroNode temp  = head;
        //遍历链表，找到最后
        while (true){
            // 找到最后一个
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将Temp后移
            temp = temp.next;

        }
        // 当退出while循环时，temp就指向了链表的最后
        //最后这个节点指向新的节点
        temp.next = heroNode;
    }
    // 显示链表
    //思路：通过一个辅助变量进行遍历，帮助遍历整个链表。
    public void list() {
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量进行遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null){
                break;
            }
            //输出
            System.out.println(temp);
            //后移
            temp = temp.next;
        }
    }
}


//定义 HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    //指向下一个节点
    public HeroNode next;
    public HeroNode(int hnox, String hName, String hNickName){
        this.no = hnox;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "no>>"+no+">>name>>"+name+">>nickName>>"+nickName+">>next>>"+next;
    }
}

