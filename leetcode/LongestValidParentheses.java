package leecode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Jeanye
 * 此算法打败100%的人
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if(s.length() == 0){
            return 0;
        }
        //如果最后栈内留有元素，则记下他的进栈顺序
        int count = 0;
        List<Integer> list = new ArrayList<>();
        int res = 0;
        Deque<Character> stack = new ArrayDeque<>();
        //一旦出栈说明有两个匹配
        for(int i=0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.add('(');
                count++;
                list.add(count);
            }
            if(s.charAt(i) == ')'){
                //如果此时stack为空，也要记下当前位置，当前位置也是阻断位置
                if( stack.peek() == null){
                    //)没有匹配，说明当前匹配被阻断，可以计算
                    if(a(res, count, list) > res) {
                        res = a(res, count, list);
                    }
                    //重新从0计算
                    count = 0;
                }else {
                    stack.removeLast();
                    list.remove(list.size() - 1);
                }
            }
        }
        if(a(res, count, list) > res){
            return a(res, count, list);
        }else {
            return res;
        }
    }
    int a(int res, int count, List<Integer> list){
        //如果list为空
        if(list.size() == 0){
            res = 2*count;
        }else {
            //将list拿出来分段，然后比较每一段的大小
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    res = 2 * (list.get(i) - 1);
                }
                if (i + 1 < list.size()) {
                    if (2 * (list.get(i + 1) - list.get(i)) > res) {
                        res = 2 * (list.get(i + 1) - list.get(i));
                    }
                }
                if(i == list.size()-1){
                    if(2 * (count - list.get(i)) > res){
                        res = 2 * (count - list.get(i));
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        Deque stack = new ArrayDeque();
        stack.addLast("a");
        stack.add("b");
        stack.addLast("c");
        stack.add("d");
        stack.addFirst("e");
        //add与addLast相同
        while (stack.peekLast() != null){
            System.out.print(stack.removeLast());
        }
        System.out.println();
        LongestValidParentheses l = new LongestValidParentheses();
        int res = l.longestValidParentheses("(()))())(");
        System.out.print(res);
    }
}
