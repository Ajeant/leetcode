import java.util.Deque;
import java.util.ArrayDeque;
public class Solution {
    public int evalRPN(String[] tokens) {
        // 需要一个栈，一个存放数字
        Deque<Integer> digit = new ArrayDeque<Integer>();
        for (int i = 0; i < tokens.length; i++) {
            try {
                digit.push(Integer.parseInt(tokens[i]));
            } catch (Exception e) {
                digit.push(calculate(digit.pop(), digit.pop(), tokens[i]));
            }
        }
        return digit.pop();
    }
     
    public int calculate(int a, int b, String op) {
        switch(op) {
            case "+":
                return a + b;
            case "-":
                return b - a;
            case "*":
                return a * b;
            case "/":
                return b / a;
            default:
                return 0;
        }
    }
}