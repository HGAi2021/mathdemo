package queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue arrayQueueDemo = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加队列");
            System.out.println("g(get):获取队列");
            System.out.println("h(head):获取队列头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueueDemo.showQueue();
                    break;
                case  'a':
                    System.out.println("添加一个数据");
                    int value = scanner.nextInt();
                    arrayQueueDemo.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = arrayQueueDemo.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int i = arrayQueueDemo.headQueue();
                        System.out.printf("队列头的数据是%d\n",i);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case  'e':
                    scanner.close();
                    loop =  false;
                    break;
                }
        }

    }
}
//使用数组模拟队列 - 编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;//队列最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部的前一个位置
        rear = -1;//指向队列尾部的具体数据。
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n ){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满不能加入数据");
            return;
        }
        rear++;//让rear后移
        arr[rear] = n;
    }
    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;//让front后移
        System.out.println("front>>"+front);
        return arr[front];
    }
    // 显示队列所有数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列为空，不能遍历");
        }
        for (int i =0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列头部数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不能显示数据");
        }
        return arr[front+1];
    }
}
