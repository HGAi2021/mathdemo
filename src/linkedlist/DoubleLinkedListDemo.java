package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        //
        HeroNode2 heroNode1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 heroNode4 = new HeroNode2(4,"林冲","豹子头");
        HeroNode2 heroNode5 = new HeroNode2(4,"林","豹");
//        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        //
        System.out.println("添加");
        doubleLinkedList.list();

        doubleLinkedList.update(heroNode5);
        System.out.println("修改后");
        doubleLinkedList.list();

        //删除节点
        doubleLinkedList.delete(heroNode5);
        System.out.println("删除后");
        doubleLinkedList.list();
    }
}



//创建一个双向链表类
class DoubleLinkedList{
    // 初始化
    private HeroNode2 head = new HeroNode2(0,"","");
    // 返回头节点
    public HeroNode2 getHead(){
        return head;
    }
    //遍历双向链表
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true){
            //链表最后
            if (temp == null){
                break;
            }
            System.out.println(temp);
            //链表后移
            temp = temp.next;
        }
    }
    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode2){
        HeroNode2 temp = head;
        //遍历链表找到最后
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        // 当退出while循环时，temp指向了链表的最后，将最后节点的next指向新的节点
        temp.next = heroNode2;
        // 让新节点的pre节点指向原最后节点,形成双向链表，
        heroNode2.pre = temp;
    }
    // 修改一个节点的内容
    public void update(HeroNode2 heroNode2){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no编号
        //  定义一个辅助变量
        HeroNode2 temp = head.next;
        // 表示是否找到该节点
        boolean flag = false;
        while (true){
            if (temp == null){
                break;//已经遍历完列表
            }
          if (temp.no == heroNode2.no){//找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //找到
            temp.name = heroNode2.name;
            temp.nickName = heroNode2.nickName;
        }else {
            //没有找到
            System.out.printf("没有找到编号为 %d 的节点,不能修改\n",heroNode2.no);
        }

    }
    // 从双向链表中删除一个节点
    // 说明:对于双向链表，我们可以直接找到要删除的这个节点
    // 找到后自我删除即可
    public void delete(HeroNode2 heroNode2){
        //直接找到待删除节点，head的话是找到待删除节点的前一个节点
        HeroNode2 temp = head.next;
        boolean flag = false;
        //判读当前链表是否为空
        if (temp == null){
            return;
        }
        while (true){
            if (temp == null){
                //链表最后
                break;
            }
            if (temp.no == heroNode2.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            // 如果是最后一个节点,就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }

        }else {
            System.out.printf("没有找到编号为 %d 的节点，不能删除\n",heroNode2.no);
        }
    }
}
//定义 HeroNode，每个HeroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    //指向下一个节点，默认为null
    public HeroNode2 next;
    // 指向前一个节点，默认为null
    public HeroNode2 pre;

    public HeroNode2(int hnox, String hName, String hNickName){
        this.no = hnox;
        this.name = hName;
        this.nickName = hNickName;
    }

    @Override
    public String toString() {
        return "no>>"+no+">>name>>"+name+">>nickName>>"+nickName/*+">>next>>"+next+">>pre>>"+pre*/;
    }
}

