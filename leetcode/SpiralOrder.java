import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    /**
     * 将矩阵以顺时针旋转读取
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
//        List ans = new ArrayList();
//        if (matrix.length == 0) return ans;
//        int R = matrix.length, C = matrix[0].length;
//        boolean[][] seen = new boolean[R][C];
//        int[] dr = {0, 1, 0, -1};
//        int[] dc = {1, 0, -1, 0};
//        int r = 0, c = 0, di = 0;
//        for (int i = 0; i < R * C; i++) {
//            ans.add(matrix[r][c]);
//            seen[r][c] = true;
//            int cr = r + dr[di];
//            int cc = c + dc[di];
//            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
//                r = cr;
//                c = cc;
//            } else {
//                di = (di + 1) % 4;
//                r += dr[di];
//                c += dc[di];
//            }
//        }
//        return ans;
        if(matrix.length == 0){
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        //顺序为上右下左
        int t = matrix[0].length;
        int r = matrix.length;
        int b = 0;
        int l = 0;
        int i = 0;
        int j = 0;

        while (res.size() < matrix.length * matrix[0].length) {
            //上
            for (; j < t; j++) {
                res.add(matrix[i][j]);
            }
            //将行下移，列移到最后
            i++;
            j--;
            t--;
            if(res.size() < matrix.length * matrix[0].length) {
                //右
                for (; i < r; i++) {
                    res.add(matrix[i][j]);
                }
                //将列左移，行移到最后
                j--;
                i--;
                r--;
            }
            if(res.size() < matrix.length * matrix[0].length) {
                //下
                for (; j >= b; j--) {
                    res.add(matrix[i][j]);
                }
                //行上移，列移到最前
                j++;
                i--;
                b++;
            }
            if(res.size() < matrix.length * matrix[0].length) {
                //左
                for (; i > l; i--) {
                    res.add(matrix[i][j]);
                }
                //行回到原位，列右移
                i++;
                j++;
                l++;
            }
        }
        return res;
    }

    public static void main(String[] args){
        SpiralOrder s = new SpiralOrder();
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        List<Integer> res = s.spiralOrder(matrix);
        System.out.println(res);
    }
}
