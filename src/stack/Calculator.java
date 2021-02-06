package stack;

/**
 * 使用栈完成表达式的计算思路
 *
 * 通过一个 index 值(索引)，来遍历我们的表达式
 * 如果我们发现是一个数字，直接进入数字栈
 * 如果我们发现是一个符号，就区分情况
 *      情况1：当前符号栈为空，直接进入
 *      情况2：当前符号栈不为空，进行比较
 *             比较1：如果当前操作符的优先级 小于或等于 栈中操作符，就需要从数字栈中pop出两个数，在从符号栈中pop出一个符号，进行运算。
 *             将得到的结果入栈，数字进入数字栈，符号进入符号栈，
 *             比较2：如果当前操作符优先级 大于 栈中操作符，直接进入符号栈。
 * 当表达式扫描完毕，就顺序从数字栈和符号栈中pop出相应的数字和符号，进行运算。
 * 最后在数字栈中只有一个数字，就是表达式的结果。
 */
public class Calculator {
    public static void main(String[] args) {

        // 根据思路 完成表达式运算 ########如何处理多位数的问题?
//        String expression = "3+2*6-2";
        String expression = "70+2*6-4";
        // 创建两个栈，数字栈 符号栈
        ArrayStack2 numStack2 = new ArrayStack2(10);
        ArrayStack2 operStack2 = new ArrayStack2(10);
        // 定义需要的相关变量
        int index = 0; //用于扫描

        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';// 将每次扫描的char保存到ch 中
        String keepNum = "";//用于拼接多位数
        // 开始扫描
        while (true) {
            // 依次得到每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断 ch 是什么，做相应处理
            if (operStack2.isOper(ch)) { // 如果是运算符
                // 判断符号栈是否为空
                if (!operStack2.isEmpty()) {
                    // 当前优先级 小于 等于 栈中优先级
                    // 从数据栈中pop出两个数
                    // 从符号栈中pop出一个符号
                    // 进行运算，得到结果入数栈，符号入符号栈
                    if (operStack2.priority(ch) <= operStack2.priority(operStack2.peek())) {
                        // 从数据栈抛出两个数字
                        num1 = numStack2.pop();
                        num2 = numStack2.pop();
                        // 符号栈pop
                        oper = operStack2.pop();
                        // 运算
                        res = numStack2.cal(num1, num2, oper);
                        // 结果放到数栈
                        numStack2.push(res);
                        // 符号放到符号栈
                        operStack2.push(ch);

                    } else {
                        operStack2.push(ch);
                    }
                } else {
                    // 如果为空 直接入栈
                    operStack2.push(ch);
                }

            } else {
                //如果是数
//                numStack2.push(ch -48);
                // 当处理多位数时，不能发现是一个数就入栈，因为他可能是多位数
                // 在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果符号才入栈
                // 因此我们需要定义一个变量，字符串用于拼接

                //处理多位数
                keepNum += ch;

                //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，就入栈
                //如果ch 是最后一位,直接入栈
                if (index == expression.length()-1) {
                    numStack2.push(Integer.parseInt(keepNum));
                }

                else if (operStack2.isOper(expression.substring(index+1,index+2).charAt(0))) {
                    //如果最后一位是运算符，则入栈
                    numStack2.push(Integer.parseInt(keepNum));
                    // 重要的是，keepNum清空
                    keepNum ="";
                }

            }
            // 让 index + 1,判断是否扫描到expression最后，
            index++;
            if (index >= expression.length()){
                // 扫描到最后
                break;
            }
        }

        // 当表达式扫描完毕，就顺序的从 数栈 和 符号栈 中pop出相应的数和符号，并运行
        while (true) {
            // 如果符号栈为空,则计算到最后结果，数栈只有一个数值(这个就是最后数值)
            if (operStack2.isEmpty()) {
                break;
            }
            num1 = numStack2.pop();
            num2 = numStack2.pop();
            oper = operStack2.pop();
            res = numStack2.cal(num1, num2, oper);

            numStack2.push(res);
        }

        // 将数栈的最后数pop出，就是结果
        System.out.printf("表达式 %s = %d \n",expression,numStack2.pop());



    }
}
//先创建一个栈,直接使用前面创造好的，需要扩展功能
class ArrayStack2{
    private int maxSize;//栈大小
    private int[] stack;//数组模拟栈，数据放在该数组中
    private int top = -1;//top表示栈顶，当没数据时初始化为-1；
    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    // 返回当前栈顶的值但不出栈
    public int peek() {
        return stack[top];
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
    //返回运算符的优先级，优先级是程序员来确定的,优先级使用数字表示，数字越大，则优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '-' || oper == '+'){
            return 0;
        } else {
            return -1; // 假定目前表达式 只有 + - * /
        }
    }
    // 判断是不是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //  计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; //res 用于存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序，后面减前面的
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 /num1; // 注意顺序，后面除以前面的
                break;
        }
        return res;
    }



}