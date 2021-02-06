package hash;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author HGAI
 * @version v1
 * @date 2021/1/14 0014 12:25
 * @description
 */
public class HashTableDemo {
    public static void main(String[] args) {

        // 创建哈希表
        HashTable hashTable = new HashTable(7);

        //写一个简单的菜单
        String key = "";

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("add : 添加雇员");
            System.out.println("list : 显示雇员");
            System.out.println("find : 查找雇员");
            System.out.println("exit : 退出系统");

            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }

    }

}

// 创建HashTable 管理多条链表
class HashTable {

    // 数组里面放的链表
    private EmpLinkedList[] empLinkedListArray;

    //表示有多少条链表
    private int size;


    // 构造器
    public HashTable(int size) {
        //初始化 empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        this.size = size;
        //留了一个坑，这时不要忘了分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id ， 得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        // 将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表，遍历hashtab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id,查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int i = hashFun(id);
        Emp emp = empLinkedListArray[i].findEmpById(id);
        if (emp != null) {
            //说明找到了
            System.out.printf("在第 %d 条中找到该雇员，雇员id =%d \n ", i, id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    // 编写一个散列函数（使用取模方法）
    public int hashFun(int id) {
        return id % size;
    }

}

/**
 * 表示一个雇员
 */
class Emp {

    public int id;

    public String name;

    public Emp next; // 默认为null

    public Emp (int id, String name ) {

        super();

        this.id = id;

        this.name = name;

    }

}
// 表示链表
class EmpLinkedList {
    // 头指针，执行第一个Emp，因此我们链表的head 是直接指向第一个Emp，默认为null
    private Emp head;

    //添加雇员到链表
    // 说明
    // 1、假定，当添加雇员时, id是自增长的,即id的分配总是从小到大
    // 因此直接加入到本链表最后即可
    public void add(Emp emp) {

        // 如果是添加第一个雇员
        while (head == null) {
            head = emp;
            return;
        }

        // 如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;

        while (true) {
            if (curEmp.next == null) {
                // 说明链表最后
                break;
            }
            // 后移，直到链表最后
            curEmp = curEmp.next;
        }

        // 退出时，直接将emp加入链表
        curEmp.next = emp;
    }

    // 遍历链表的雇员信息
    public void list(int no) {

        if (head == null) { // 说明链表为空
            System.out.println("第" + no + "链表为空");
            return;
        }
        System.out.print("第" + no + "条链表信息 >>>> ");

        // 辅助指针
        Emp curEmp = head;

        while (true) {
            System.out.printf("=> id = %d name = %s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                // 说明curEmp已经是最后节点
                break;
            }
            // 后移，遍历
            curEmp = curEmp.next;
        }

        System.out.println();
    }

    // 根据id查找雇员
    // 如果查找到就返回Emp 如果没有找到就返回空
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        //辅助指针
        Emp curEmp = head;

        // 假定id 不重复
        while (true) {
            if (curEmp.id == id) {
                //说明找到了,这时curEmp就指向要查找的雇员
                break;
            }
            //退出
            if (curEmp.next == null) {
                //说明遍历当前链表没有找到该雇员,
                curEmp = null;
                break;
            }
            //后移
            curEmp = curEmp.next;
        }

        return curEmp;
    }
}
