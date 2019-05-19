package com.blog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveNQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>(0);
        //二维数组代表棋盘
        String[] queens = new String[n];
        char[] rows = new char[n];
        //初始化为"."
        for(int i=0; i < rows.length; i++){
            rows[i] = '.';
        }
        for(int i=0; i < queens.length; i++){
            queens[i] = String.valueOf(rows);
        }
        //回溯
        int j=0;
        boolean flag = true;
        for(int i=0; i < queens.length; i++){
            for(; j < queens[i].length(); j++){
                //String为不可更改，只能将此行重新赋值
                queens[i] = modifyQ(j, rows.length);
                flag = judge(queens, i, j);
                //如果flag为false，当前不可置为Q，用下一个，直到末尾
                if(!flag){
                    queens[i] = String.valueOf(rows);
                }else {
                    break;
                }
            }
            //到了末尾还是不行，回到上一行，让其从Q的后一个开始继续
            System.out.println(flag);
            if(!flag) {
                i--;
                if(i < 0){
                    i = 0;
                }
                j = searchQ(queens, i)+1;
                if(i == 0 && j == queens.length){
                    break;
                }
                //将其置为"."
                queens[i] = String.valueOf(rows);
                i--;
            }else {
                j = 0;
            }
            if(i == queens.length-1 && !(String.valueOf(rows).equals(queens[queens.length-1]))) {
                //可行，添加一个
                res.add(Arrays.asList(queens.clone()));
                //如何添加第二个？将最后一个当成flag为false重新回溯
                j = searchQ(queens, i)+1;
                queens[queens.length-1] = String.valueOf(rows);
                i--;
                flag = false;
            }
        }
        return res;
    }

    /**
     * 将位置m置为Q
     * @param m
     * @param l
     * @return
     */
    public String modifyQ(int m, int l){
        char[] rows = new char[l];
        for(int i=0; i < rows.length; i++){
            if(i == m){
                rows[i] = 'Q';
            }else {
                rows[i] = '.';
            }
        }
        return String.valueOf(rows);
    }

    /**
     * 查找到Q的位置
     * @param queens
     * @param m
     * @return
     */
    public int searchQ(String[] queens, int m){
        for(int i=0; i < queens.length; i++){
            if(queens[m].charAt(i) == 'Q'){
                return i;
            }
        }
        return 0;
    }

    /**
     * 判断当前位置可否置为Q
     * @param queens
     * @param m
     * @param n
     * @return
     */
    public boolean judge(String[] queens, int m, int n){
        //判断当前位置的上方，斜上方是否有Q
        //判断上方
        for(int i=0; i < m; i++){
            if('Q' == queens[i].charAt(n)){
                return false;
            }
        }
        //判断斜上方
        for(int i=1; i <= m && i <= n; i++){
            if('Q' == queens[m-i].charAt(n-i)){
                return false;
            }
        }
        for(int i=1; i <= m && i+n < queens[m].length(); i++){
            if('Q' == queens[m-i].charAt(n+i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
//        String[][] queens = new String[3][3];
//        for(int i=0; i < queens.length; i++){
//            for(int j=0; j < queens[i].length; j++){
//                queens[i][j] = ".";
//            }
//        }
//        for(String[] s : queens){
//            for(String str : s){
//                System.out.print(str+"\t");
//            }
//            System.out.println();
//        }
//        char[] c = new char[3];
//        for(int i=0; i < 3; i++){
//            c[i] = '.';
//        }
//        String s = String.valueOf(c);
//        System.out.println(s.charAt(0) != '.');
        SolveNQueens s = new SolveNQueens();
        List<List<String>> res = s.solveNQueens(5);
        System.out.println(res);
    }
}
