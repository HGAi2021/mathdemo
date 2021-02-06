package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算（后缀表达式）
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        // (3+4)*5-6 => 3 4 + 5 * 6 -
        // 为了方便 逆波兰表达式 中的数字和符号用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";

        //4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        // 思路：
        // 1、"3 4 + 5 * 6 -" 放到arrayList中
        // 2、将arrayList 传递给一个方法，遍历arrayList 配合栈完成计算。

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList>>" + rpnList);

        int res = calculate(rpnList);
        System.out.println("计算得到的结果是>>"+res);


        //完成一个中缀表达式转成后缀表达式的功能
        //说明
        // 1、1+((2+3)*4)-5 =>转成 1 2 3 + 4 * 5 -
        // 2、因为直接对str 进行操作，不方便，因此 先将"1+((2+3)*4)-5"=>中缀表达式对应的list
        // 即："1+((2+3)*4)-5" => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        // 3、将得到的中缀表达式对应的list => 后缀表达式对应的list [1,2,3,+,4,*,+,5,-]

        String expression = "1+((2+3)*4)-5";

        List<String> list = toInfixExpressionList(expression);
        System.out.println("中缀表达式>>"+list);

        //中缀表达式转后缀表达式
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("后缀表达式>>"+list1);

        // 计算后缀表达式结果
        int calculate = calculate(list1);
        System.out.println("后缀表达式结果>>"+calculate);


    }
     // 将一个逆波兰表达式，依次将数据和运算符放入到arrayList中
    public static List<String> getListString(String suffixExpression) {
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s1 : s) {
            list.add(s1);
        }
        return list;
    }

    /**
     * 从左到右扫描，将3和4压入堆栈
     * 遇到+运算符，弹出4,3，计算3+4的值
     * 将7入栈
     * 将5入栈
     * 遇到* 运算符，计算7*5的值
     * 将35入栈
     * 将6入栈
     * 最后是-运算符
     * 计算35-6
     * 将29入栈得到最后结果
     */
    public static int calculate(List<String> list) {
        // 创建一个栈，只需要一个栈
        Stack<String> stack = new Stack<>();
        // 遍历list
        for (String item: list
             ) {
            if (item.matches("\\d+")) {
                // 匹配的是多位数,入栈
                stack.push(item);
            } else {
                //pop 两个数，并运算，再入栈
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num2 + num1;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num2 * num1;
                } else if (item.equals("/")) {
                    res = num1 /num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                // 把res 入栈
                stack.push(res+"");
            }
        }
        // 最后留在stack中的结果 就是运算结果
        return Integer.parseInt(stack.pop());
    }

    // 将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s ) {
        // 定义一个List,存放中缀表达式对应的内容
        List<String > ls = new ArrayList<>();
        //定义指针，用于遍历中缀表达式字符串
        int i = 0;
        // 用于多位数的拼接
        String str;
        // 每遍历到一个字符，就放到c
        char c;
        do {
            // 如果c是一个非数字，我们就需要加入到ls中去
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(""+c);
                i++; // i需要后移
            } else {
                // 如果是一个数，需要考虑多位数问题
                str = "";//先将str置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while(i < s.length());
        return ls; //返回
    }
    //中缀转换成为后缀表达式
    // 思路：
    // 1、初始化两个栈
    // 2、从左到右扫描中缀表达式
    // 3、遇到操作数时候加入s2
    // 4、遇到运算符
    // 情况1：s1 为空 或者 s1顶端为“（”时，直接入栈
    // 情况2：s1不为空
    // 若优先级比s1栈顶运算符高，也将运算符压入s1,
    // 若优先级比s1栈顶运算符低，将s1栈顶运算符弹出并压入s2中，再次从情况1开始比较
    // 5、遇到括号时：
    // 左括号；直接加入s1
    // 右括号：依次弹出s1栈顶运算符，压入s2，直到遇到左括号为止，同时丢弃这对括号
    // 6、重复步骤2-5，直到扫描到最右端
    // 7、将s1剩余的运算符依次弹出并压入s2
    // 8 、依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> list) {
        // 定义两个栈
        // 符号栈
        Stack<String> s1 = new Stack<>();
        // 存储中间结果的栈
        // 由于s2这个栈，在整个转换过程中，没有pop操作，而且我们后面还需要逆序输出，因此我们就不需要用Stack<String> 直接使用List<String> s2
//        Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        // 遍历list
        for (String item : list) {
            //如果是一个数 就加入入s2栈
            if (item. matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                // 如果是一个左括号
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号,依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时这对括号丢弃
                while (!s1.peek().equals("(")) {
                    //不存在该运算符
                    s2.add(s1.pop());
                }
                //s1弹出左括号，丢弃括号
                s1.pop();
            } else {
                // 当item的优先级 小于等于 s1栈顶的运算符的优先级，
                // 将s1栈顶的运算符弹出 加入s2，
                // 问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item 压入栈中
                s1.push(item);
            }
        }
        // 将s1中剩余的运算符依次弹出，并加入s2
        while (s1.size() !=0) {
            s2.add(s1.pop());
        }
        //因为添加到list中，按顺序输出即为逆波兰表达式list
        return s2;
    }

}

//编写一个类 可以返回一个运算符对应 的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级 数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
        }
        return result;
    }
}


