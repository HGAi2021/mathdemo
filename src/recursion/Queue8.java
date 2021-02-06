package recursion;

/**
 * 8皇后问题
 *
 */
public class Queue8 {

    // 定义一个Max 表示共有多少个皇后
    int max = 4;
    // 定义数组array，保存皇后存放位置的结果，比如 arr = {0,4,7，5，2，6，1,3} 下标代表行  数组的值代表列
    int [] array = new int[max];

    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有 %d 种解法\n", count);
        queue8.print();
    }

    //放置下标为n的皇后（即皇后在第n行）
    public void check(int n) {
        if (n == max ) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后 n ，放到该行第一列。
            array[n] = i;
            // 判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) { // 不冲突
                check(n + 1);
            }
            // 如果冲突，就继续执行array[n] = i ,即将第n个皇后放置在本行的后移一个位置
        }
    }


    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 放置的第n个皇后的列  n+1 代表第几个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 同一列   同一斜线
            // 没有必要 判断是否在同一行，n每次都在递增
            if (array[i] == array[n] || Math.abs(n - i ) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

    }
}
