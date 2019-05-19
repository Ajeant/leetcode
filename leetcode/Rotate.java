package leecode;

public class Rotate {
    /**
     * 旋转二维数组
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        //把matrix[i][j]转为matrix[j][matrix.length - i - 1]
        //记录新数组
        int[][] res = new int[matrix.length][matrix[0].length];
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < matrix[i].length; j++){
                res[j][matrix.length-i-1] = matrix[i][j];
            }
        }
        //赋值给matrix
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < matrix[i].length; j++){
                matrix[i][j] = res[i][j];
            }
        }
    }
    public static void main(String[] args){
        Rotate r = new Rotate();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        r.rotate(matrix);
        for(int[] i : matrix){
            for (int j : i){
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
}
