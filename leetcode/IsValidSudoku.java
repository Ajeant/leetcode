package leecode;

import java.util.HashSet;
import java.util.Set;

public class IsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        //循环board两轮，遍历所有元素
        //不能出现重复，可以利用set
        for(int i=0; i < board.length; i++){
            //遍历行
            Set<Character> rows = new HashSet<>();
            Set<Character> columns = new HashSet<>();
            Set<Character> box = new HashSet<>();
            for(int j=0; j < board[i].length; j++){
                //判断行
                if(board[i][j] != '.' && rows.add(board[i][j]) == false){
                    //添加失败，说明有重复
                    return false;
                }
                //判断列
                if(board[j][i] != '.' && columns.add(board[j][i]) == false){
                    //添加失败，说明有重复
                    return false;
                }
                //判断box
                if(i % 3 == 0 && j % 3 == 0){
                    //判断box
                    for(int k=0; k < 3; k++){
                        for(int l=0; l < 3; l++) {
                            if (board[i+k][j+l] != '.' && box.add(board[i+k][j+l]) == false) {
                                return false;
                            }
                        }
                    }
                    box.clear();
                }
            }
            //判断一行一列后清空
            rows.clear();
            columns.clear();
        }
        return true;
    }
    public static void main(String[] args){
        char board[][] = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
//        [
//                ['.','.','4','.','.','.','6','3','.'],
//                ['.','.','.','.','.','.','.','.','.'],
//                ['5','.','.','.','.','.','.','9','.'],
//                ['.','.','.','5','6','.','.','.','.'],
//                ['4','.','3','.','.','.','.','.','1'],
//                ['.','.','.','7','.','.','.','.','.'],
//                ['.','.','.','5','.','.','.','.','.'],
//                ['.','.','.','.','.','.','.','.','.'],
//                ['.','.','.','.','.','.','.','.','.']
//        ]
        IsValidSudoku sudoku = new IsValidSudoku();
        boolean res = sudoku.isValidSudoku(board);
        System.out.println(res);
    }
}
