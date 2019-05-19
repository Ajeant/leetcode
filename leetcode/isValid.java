public class isValid{
	static boolean isValid(String s) {
        Stack<Character> c = new Stack<>();
        for(int i=0; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                c.push(s.charAt(i));
            }else if(!c.empty() && s.charAt(i) == ')' && c.peek() == '('){
                c.pop();
            }else if(!c.empty() && s.charAt(i) == ']' && c.peek() == '['){
                c.pop();
            }else if(!c.empty() && s.charAt(i) == '}' && c.peek() == '{'){
                c.pop();
            }else {
                return false;
            }
        }
        return c.empty();
    }
}