package recursion;

/**
 * 递归调用问题
 * 打印问题
 * 结成问题
 */
public class RecursionTest {

    public static void main(String[] args) {

        test(4);

        int res = factorial(1);
        System.out.println("res >> "+ res);
    }

    public static void test(int n) {
        if (n > 2){
            test(n - 1);
        } else {
            System.out.println("n = " + n);
        }
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
