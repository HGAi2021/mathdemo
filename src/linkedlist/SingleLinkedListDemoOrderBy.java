package linkedlist;

import java.util.Stack;

/**
 * @author H_Java
 * 有序添加单向链表
 * 首先找到新添加节点的位置，通过辅助变量，遍历得到
 * 新节点  next = temp.next
 * temp.next = 新节点
 */
public class SingleLinkedListDemoOrderBy {
    public static void main(String[] args) {
        //先创建节点
        HeroNodeOrderBy heroNode1 = new HeroNodeOrderBy(1,"宋江","及时雨");
        HeroNodeOrderBy heroNode2 = new HeroNodeOrderBy(4,"卢俊义","玉麒麟");
        HeroNodeOrderBy heroNode3 = new HeroNodeOrderBy(3,"吴用","智多星");
        HeroNodeOrderBy heroNode4 = new HeroNodeOrderBy(2,"林冲","豹子头");
        System.out.println("创建");
        //创建一个链表
        SingleLinkedListOrderBy singleLinkedListOrderBy = new SingleLinkedListOrderBy();
        singleLinkedListOrderBy.add(heroNode1);
        singleLinkedListOrderBy.add(heroNode2);
        singleLinkedListOrderBy.add(heroNode3);
        singleLinkedListOrderBy.add(heroNode4);
        //显示链表
        singleLinkedListOrderBy.list();
        System.out.println("更新");
        HeroNodeOrderBy heroNode2v1 = new HeroNodeOrderBy(4,"卢","玉");
        singleLinkedListOrderBy.update(heroNode2v1);
        //显示链表
        singleLinkedListOrderBy.list();
        System.out.println("删除");
        //删除一个节点
        HeroNodeOrderBy heroNode2v2 = new HeroNodeOrderBy(4,"卢","玉");
        singleLinkedListOrderBy.delete(heroNode2v2);
        //显示链表
        singleLinkedListOrderBy.list();
        System.out.println("单链表有效节点个数");
        System.out.println( getLength(singleLinkedListOrderBy.getHead()));
        //倒数第K个节点
        System.out.println("倒数第K个节点"+findLastNode(singleLinkedListOrderBy.getHead(),1));
        //单链表翻转
        System.out.println("原链表情况");
        singleLinkedListOrderBy.list();
        reverseList(singleLinkedListOrderBy.getHead());
        System.out.println("翻转链表后");
        singleLinkedListOrderBy.list();
        //逆序打印单链表
        System.out.println("逆序打印单链表,没有改变原有链表本身结构");
        reversePrint(singleLinkedListOrderBy.getHead());

    }

    //获取单链表有效节点的个数（带头结点的链表,不统计头节点）
    public static int getLength(HeroNodeOrderBy head){
        //空链表
        if (head.next == null){
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量，这里没有统计头节点
        HeroNodeOrderBy cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;//遍历
        }
        return length;

    }
    //查找单链表中倒数第K个节点
    //思路：编写一个方法接受head节点，同时接受一个index，index表示倒数第index个，
    // 先把链表从头到尾遍历一下，得到链表总的长度，
    // 得到长度后，从链表的第一个开始遍历，遍历长度-index个。
    // 如果找到了，返回该节点，否则返回null
    public static HeroNodeOrderBy findLastNode(HeroNodeOrderBy head,int index){
        // 如果链表为空，返回null
        if (head.next == null){
            return  null;
        }
        //第一个遍历，得到链表的长度
        int size = getLength(head);
        // 第二次遍历，size-index，就是我们倒数的第k个节点
        //先做一个index的校验
        if (index <=0 || index>size){
            return null;
        }
        //定义一个辅助变量 for 循环定位到 倒数的index个
        HeroNodeOrderBy cur = head.next;
        for (int i = 0; i<size-index;i++){
            cur = cur.next;
        }
        return cur;
    };
    // 单链表翻转
    public static void reverseList(HeroNodeOrderBy head){
        // 当前链表为空，无需翻转
        if (head.next == null || head.next.next ==null){
            return;
        }
        HeroNodeOrderBy cur = head.next;// 定义一个辅助的变量，帮助我们遍历原来的链表
        HeroNodeOrderBy next = null;//指向当前节点[cur]的下一个节点
        HeroNodeOrderBy reverseHead = new HeroNodeOrderBy(0,"","");
        //遍历原来的链表，每遍历一个节点，将其取出，并放在新的链表的最前端
        while (cur !=null){
            next = cur.next;//先暂时保留当前节点的下一个节点，因为后面需要。
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端。
            reverseHead.next = cur;//将原来的链表连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next，实现了单链表的翻转
        head.next = reverseHead.next;
    }
    // 从尾到头打印单链表
    // 思路1：翻转单链表,再遍历，这样做的问题是会破坏原来的单链表的结构，不建议。
    // 方式2：利用栈这个数据结构，将各个节点压入到栈中，利用栈的先进后出特点，实现了逆序打印效果。
    public static void reversePrint(HeroNodeOrderBy head){
        if (head.next == null){//空链表 不打印
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNodeOrderBy> stack = new Stack<>();
        HeroNodeOrderBy cur = head.next;
        //将链表所有的节点压入栈中
        while (cur.next!=null){
            stack.push(cur);
            cur = cur.next;//cur后移，可以压入下一个节点
        }
        //将栈中的节点进行打印,pop出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }


}

//定义一个LinkedList来管理我们的英雄
class SingleLinkedListOrderBy{
    //先初始化一个头节点，头节点不要动。 不存放具体的数据
    private HeroNodeOrderBy head = new HeroNodeOrderBy(0,"","");

    public HeroNodeOrderBy getHead() {
        return head;
    }

    // 因为是单链表，因为我们找的temp是位于添加位置的前一个节点，否则加入不了。
    public void add(HeroNodeOrderBy heroNodeOrderBy){
        //因为head节点不能动，因此我们需要一个辅助变量
        HeroNodeOrderBy tempOrderBy  = head;
        //标志添加的编号是否 存在，默认不存在。
        boolean flag = false;
        //遍历链表，找到最后
        while (true){
            // 找到最后一个
            if (tempOrderBy.next == null){
                break;
            }
            if (tempOrderBy.next.no > heroNodeOrderBy.no){//位置找到，就在temp后面插入
                break;
            }else if (tempOrderBy.next.no == heroNodeOrderBy.no){//说明编号存在
                flag = true;
                break;
            }
            //如果没有找到最后，将Temp后移
            tempOrderBy = tempOrderBy.next;
        }
        // 判断flag的值 如果true 不能添加，说明编号存在，false 则可以添加
        if (flag){
            System.out.printf("准备插入的英雄编号 %d 已经存在\n",heroNodeOrderBy.no);
        }else {
            //加入到链表中,temp的后面
            heroNodeOrderBy.next = tempOrderBy.next;
            tempOrderBy.next = heroNodeOrderBy;
        }

    }
    // 显示链表
    //思路：通过一个辅助变量进行遍历，帮助遍历整个链表。
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量进行遍历
        HeroNodeOrderBy temp = head.next;
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
    //修改节点信息，根据no编号来修改，即no编号不能修改。
    // 说明： 根据新节点信息的no来修改，即可。
    public void update (HeroNodeOrderBy heroNodeOrderBy){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，即no编号。
        // 定义一个辅助变量
        HeroNodeOrderBy temp = head.next;
        boolean flag = false;//是否找到该节点
        while (true){
            if (temp == null){//已经遍历完链表了
                break;
            }
            if (temp.no == heroNodeOrderBy.no){
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = heroNodeOrderBy.name;
            temp.nickName = heroNodeOrderBy.nickName;
        }else {
            System.out.printf("没有找到编号 %d 的节点，不能修改\n ",heroNodeOrderBy.no);
        }
    }

    // 删除节点信息思路
    // 先找到删除节点的前一个节点temp
    // temp.next = temp.next.next
    // 被删除的节点将不会有其他引用指向，会被垃圾回收机制回收
    public void delete (HeroNodeOrderBy heroNodeOrderBy){
        HeroNodeOrderBy tempOrderBy  = head;
        boolean flag = false;//标记是否找到待删除节点的前一个节点
        while (true){
            if (tempOrderBy.next == null){
                // 最后一个
                break;
            }
            if (tempOrderBy.next.no == heroNodeOrderBy.no){
                //找到
                flag = true;
                break;
            }
            //后移
            tempOrderBy = tempOrderBy.next;
        }
        if (flag){
            //删除
            tempOrderBy.next = tempOrderBy.next.next;
        }else {
            System.out.printf("没有找到编号 %d 的节点，不存在\n ",heroNodeOrderBy.no);
        }
    }
}


//定义 HeroNode，每个HeroNode对象就是一个节点
class HeroNodeOrderBy{
    public int no;
    public String name;
    public String nickName;
    //指向下一个节点
    public HeroNodeOrderBy next;
    public HeroNodeOrderBy(int hnox, String hName, String hNickName){
        this.no = hnox;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "no>>"+no+">>name>>"+name+">>nickName>>"+nickName+">>next>>"+next;
    }
}

