package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制菜单是否退出
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("show 显示栈");
            System.out.println("exit 退出");
            System.out.println("push 入栈");
            System.out.println("pop 出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    stack.list();
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d \n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出了");
    }
}
// 定义一个类，表示栈结构
class ArrayStack{
    private int maxSize;//栈大小
    private int[] stack;//数组模拟栈，数据放在该数组中
    private int top = -1;//top表示栈顶，当没数据时初始化为-1；
    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value) {
        //判断栈是否满了
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    // 出栈 将栈顶的数据返回
    public int pop() {
        //判断栈是否空
        if (isEmpty()){
            throw new RuntimeException("栈空,没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    // 遍历,遍历时从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i>=0; i--){
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }

    }

}