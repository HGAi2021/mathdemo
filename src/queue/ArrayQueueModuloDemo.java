package queue;

import java.util.Scanner;

public class ArrayQueueModuloDemo {

    public static void main(String[] args) {
        //创建一个队列 设置4 说明队列有效数据最大是3
        ArrayQueueModulo arrayQueueDemo = new ArrayQueueModulo(4);
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
class ArrayQueueModulo{
    private int maxSize;//队列最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueueModulo(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n ){
        System.out.printf("addQueue->fornt=%d\nrear=%d\n",front,rear);
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满不能加入数据");
            return;
        }
        arr[rear] = n;
        //将rear后移,这里必须考虑取模
        rear = (rear+1)%maxSize;
        System.out.printf("addQueueEnd->fornt=%d\nrear=%d\n",front,rear);

    }
    //获取队列的数据，出队列
    public int getQueue(){
        System.out.printf("getQueue->fornt=%d\nrear=%d\n",front,rear);
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        //1、先把front 对应的值保存到一个临时变量
        //2、将front后移,考虑取模
        //3、将临时保存的变量返回
        int value = arr[front];
        front = (front+1)%maxSize;
        return  value;
    }
    // 显示队列所有数据
    public void showQueue(){
        System.out.printf("showQueue->fornt=%d\nrear=%d\n",front,rear);
        //遍历
        if (isEmpty()){
            System.out.println("队列为空，不能遍历");
        }
        // 思路 从front开始遍历,遍历多少个元素
        /**
         * 2
         * 3
         */
        for (int i =front;i<front+size();i++){
            System.out.printf("showQueue===For->fornt=%d\nrear=%d\n",front,rear);
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
//            System.out.printf("arr[%d]=%d\n",i,arr[i]);// 错误代码
        }
    }
    // 求出当前队列有效数据的个数
    public int size(){
        System.out.printf("size->fornt=%d\nrear=%d\n",front,rear);
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列头部数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不能显示数据");
        }
        return arr[front];
    }
}
