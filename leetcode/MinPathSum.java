class Solution {
    /**
    * 寻找最短路径
    * @param grid
    * @return
    */
    public int minPathSum(int[][] grid) {
        
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j == grid[0].length - 1){
                    continue;
                } else if (i == grid.length - 1) {
                    grid[i][j] += grid[i][j + 1];
                } else if (j == grid[0].length - 1) {
                    grid[i][j] += grid[i + 1][j];
                }else if (grid[i][j + 1] < grid[i + 1][j]) {
                    grid[i][j] += grid[i][j + 1];
                } else {
                    grid[i][j] += grid[i + 1][j];
                }
            }
        }
            
        return grid[0][0];
    }
}