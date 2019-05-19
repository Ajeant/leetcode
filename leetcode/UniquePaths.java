public class UniquePaths {
    /**
     * m×n矩阵左上角到右下角总共路径
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        //动态规划解决
        int[][] mn = new int[m][n];
        mn[0][0] = 1;
        //最后一行最后一列必定只有一条路径到达右下角
        for (int i = 0; i < n; i++) {
            mn[m-1][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            mn[i][n-1] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                //从右下角开始写
                mn[i][j] = mn[i+1][j] + mn[i][j+1];
            }
        }
        return mn[0][0];
    }
    
    public static void main (String[] args) {
        UniquePaths s = new UniquePaths();
        int res = s.uniquePaths(3, 7);
        System.out.println(res);
    }
}
