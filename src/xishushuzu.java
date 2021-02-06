public class xishushuzu {
    /**
     * 稀疏数组
     * 场景：当一个数组中大部分元素是0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
     * 处理方法：记录数组一共有几行几列，有多少个不同值，把不同值得元素的行列以及值记录在一个小规模的数组中，从而缩小编程规模
     */
    public static void main(String[] args) {
        // 创建一个二维数组
        // 0 表示没有棋子，1表示黑色的子，2表示蓝色的子
        int chessArr1[][] = new int[11][11];
        // 第二行第三列
        chessArr1[1][2] = 1;
        // 第三行第五列
        chessArr1[2][3] = 2;
        //原始二维数组
        for (int[]i:chessArr1) {
            for (int j :i){
                System.out.printf("%d\t",j);
            }
            System.out.println();
        }
        // 将二维数组转成稀疏数组
        // 思路：先遍历二维数组，得到非0数据个数
        int sum =  0;
        for (int[]i:chessArr1) {
            for (int j :i){
                if (j!=0){
                    sum++;
                }
            }
        }
        System.out.println("遍历二维数组获取非0个数>>"+sum);
        //创建稀疏数组
        int sparseArr [][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历二维数组，将非0的值存放到稀疏数组中
        // count记录是第几个非0数据
        int count = 0 ;
        for (int i =0;i<chessArr1.length;i++) {
            for (int j = 0;j<chessArr1[i].length;j++){
                if (chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 输出稀疏数组
        for (int i =0;i<sparseArr.length;i++){
            System.out.printf("稀疏数组结果>>>%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        // 将稀疏数组恢复成二维数组
        // 思路：先读取稀疏数组的第一行，根据第一行的数据，创建原始二维数组，再读取稀疏数组后面几行，并赋给原始的二维数组 即可。
        int i0 = sparseArr[0][0];
        int i1 = sparseArr[0][1];
        int i2 = sparseArr[0][2];
        System.out.println(i0);
        System.out.println(i1);
        System.out.println(i2);

        int chessArr2[][]= new int[sparseArr[0][0]][sparseArr[0][1]];
        // 恢复后的二维数组
        for (int i = 0;i<chessArr2.length;i++){
            for (int j =0;j<chessArr2[i].length;j++){
                System.out.printf("%d\t",chessArr2[i][j]);
            }
            System.out.println();
        }
        System.out.println("========");
        // 读取稀疏数组后几行数据(从第二行开始)，并赋值
        for (int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        for (int i =0; i<chessArr2.length;i++){
            for (int j =0;j<chessArr2[i].length;j++){
                System.out.printf("%d\t",chessArr2[i][j]);
            }
            System.out.println();
        }








    }


}
