package linkedlist;

/**
 * 约瑟夫问题
 *
 * 构建一个单向环形链表思路
 * 先创建第一个节点，让first指向该节点，并形成闭环。
 * 后面我们创建每一个新节点，就把该节点，加入到已有的环形链表中即可
 *
 * 遍历环形链表
 * 先让一个辅助指针（curboy），指向first节点，
 * 然后通过一个while循环遍历该环形链表即可，curboy.next == first 结束
 */
public class yusepfu {
    public static void main(String[] args) {
        // 构建环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        // 加入5个小孩节点
        circleSingleLinkedList.addBoy(125);
        //遍历环形链表
        circleSingleLinkedList.showBoy();

        //小孩出圈
        circleSingleLinkedList.countBoy(10,20,125);
    }
}
// 创建一个环形的单向链表
class CircleSingleLinkedList{
    // 创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums){
        //nums做一个数据校验
        if (nums < 1){
            System.out.println("nums值不正确");
            return;
        }
        // 辅助指针，帮助构建环形链表
        Boy curBoy = null;
        // 使用for循环来创建我们需要的环形链表
        for (int i = 1; i <= nums; i++){
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);//构成环状
                curBoy = first;//让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy(){
        //判断链表是否为空
        if (first == null){
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号 %d \n",curBoy.getNo());
            //说明遍历完毕
            if (curBoy.getNext() == first){
                break;
            }
            //curBoy后移
            curBoy = curBoy.getNext();

        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 开始编号
     * @param countNum 循环次数
     * @param nums 共有几个小孩
     */
    public void countBoy(int startNo, int countNum, int nums){
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 创建一个辅助指针，帮助小孩出圈
        Boy helper = first;
        //  需要创建一个辅助指针变量helper，事先应指向环形链表的最后这个节点
        while (true){
            if (helper.getNext() == first){ //说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让first和helper移动 startNo-1次，移动到指定位置
        for (int j = 0; j < startNo - 1 ; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        // 小孩报数时，让first和helper 指针同时的移动 m-1次，然后出圈
        // 这是个循环操作，直到圈中只有一个节点
        while (true){
            if (helper == first){
                //说明圈中只有一个节点
                break;
            }
            // 让first  和 helper 指针同时移动 countNum -1 次，然后出圈
            for (int j = 0; j < countNum - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时 first指向的节点就是要出圈的小孩节点
            System.out.printf("小孩 %d 编号，出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号 %d \n",helper.getNo());
    }
}
//创建一个Boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点,默认NUll

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
