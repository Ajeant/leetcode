package leecode;

import java.util.ArrayList;
import java.util.List;

public class SolveSudoku {
    private List<List<Character>> rows = new ArrayList<>();
    private List<List<Character>> columns = new ArrayList<>();
    private List<List<Character>> boxs = new ArrayList<>();
    public boolean solve(char[][] board, int i, int j, int k){
        for(; k < 10; k++){
            //从1-9，一旦发现有合适的就填上
            if(rows.get(i).contains((char)(k+'0')) == false &&
                    columns.get(j).contains((char)(k+'0')) == false &&
                    boxs.get(i/3*3+j/3).contains((char)(k+'0')) == false){
                //判断之后发现可行，则修改.为(char)(k+'0')，并新加到list中
                board[i][j] = (char)(k+'0');
                rows.get(i).set(j, (char)(k+'0'));
                columns.get(j).set(i, (char)(k+'0'));
                boxs.get(i/3*3+j/3).set(i%3*3+j%3, (char)(k+'0'));
                return true;
            }
        }
        //如果出了循环，说明没有合适的，需要回到上一步
        return false;
    }
    public void solveSudoku(char[][] board) {
        //回溯时要用
        char[][] boardCopy = new char[9][9];
        for(int i=0; i < 9; i++){
            for(int j=0; j < 9; j++){
                boardCopy[i][j] = board[i][j];
            }
        }
        //先将已有元素装入list
        contained(board);
        boolean flag;
        boolean b = false;
        for(int i=0; i < board.length; i++) {
            for (int j=0; j < board[i].length; j++) {
                if(boardCopy[i][j] == '.'){
                    b = false;
                }
                if(boardCopy[i][j] == '.' && b == false){
                    if(board[i][j] != '.'){
                        //是回溯来的
                        rows.get(i).set(j, '.');
                        columns.get(j).set(i, '.');
                        boxs.get(i / 3 * 3 + j / 3).set(i % 3 * 3 + j % 3, '.');
                        int temp1 = board[i][j] - '0' + 1;
                        board[i][j] = '.';
                        flag = solve(board, i, j, temp1);
                    }else {
                        flag = solve(board, i, j, 1);
                    }
                    if(flag){
                        continue;
                    }else {
                        //此路不通，回撤
                        if(j > 0) {
                            j -= 2;
                        }else {
                            j = 7;
                            i--;
                        }
                        b = true;
                    }
                }else {
                    if(b) {
                        if (j > 0) {
                            j -= 2;
                        } else {
                            i--;
                            j = 7;
                        }
                    }
                }
            }
        }
    }

    /**
     * 将9行9列9个3×3宫格的所有值用List装起来
     */
    public void contained(char[][] board){
        for(int i=0; i < board.length; i++){
            List<Character> row = new ArrayList<>();
            List<Character> column = new ArrayList<>();
            for(int j=0; j < board[i].length; j++){
                row.add(board[i][j]);
                column.add(board[j][i]);
                //判断box
                if(i % 3 == 0 && j % 3 == 0){
                    List<Character> box = new ArrayList<>();
                    //判断box
                    for(int k=0; k < 3; k++){
                        for(int l=0; l < 3; l++) {
                            box.add(board[i+k][j+l]);
                        }
                    }
                    boxs.add(box);
                }
            }
            rows.add(row);
            columns.add(column);
        }
    }
    public static void main(String[] args) {
        char board[][] = {
                {'5', '3', '.',   '.', '7', '.',   '.', '.', '.'},
                {'6', '.', '.',   '1', '9', '5',   '.', '.', '.'},
                {'.', '9', '8',   '.', '.', '.',   '.', '6', '.'},

                {'8', '.', '.',   '.', '6', '.',   '.', '.', '3'},
                {'4', '.', '.',   '8', '.', '3',   '.', '.', '1'},
                {'7', '.', '.',   '.', '2', '.',   '.', '.', '6'},

                {'.', '6', '.',   '.', '.', '.',   '2', '8', '.'},
                {'.', '.', '.',   '4', '1', '9',   '.', '.', '5'},
                {'.', '.', '.',   '.', '8', '.',   '.', '7', '9'}
        };
        SolveSudoku s = new SolveSudoku();
//        s.contained(board);
//        int test = 5;
//        System.out.println(test/3*3);
//        char c = (char)(test+'0');
//        System.out.println(board[0][0] == c);
//        char c = '9';
//        int test = c-'0'+1;
//        System.out.println(test);
        s.solveSudoku(board);
        System.out.println(s.rows);
        System.out.println(s.columns);
        System.out.println(s.boxs);
        for(int i=0; i < 9; i++){
            for(int j=0; j < 9; j++){
                System.out.print(board[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
