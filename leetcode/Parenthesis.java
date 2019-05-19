public class Parenthesis{
	static List<String> generateParenthesis(int n) {
        Set<String> set = new HashSet<>();
        for(int i=1; i < n+1; i++){
            set = generateParenthesis1(set, i);
        }
        List<String> res = new ArrayList<>(set);
        return res;
    }
    static Set<String> generateParenthesis1(Set<String> set, int n) {
        Set<String> res = new HashSet<>();
        String str;
        if(n == 1){
            set.add("()");
            return set;
        }else {
            Iterator<String> it = set.iterator();
            while(it.hasNext()){
                str = it.next();
                for(int i=0; i < str.length(); i++){
                    if(i == 0){
                        res.add("()"+str);
                    }else {
                        res.add(str.substring(0, i) + "()" + str.substring(i, str.length()));
                    }
                }
            }
            set.clear();
            set = res;
            return set;
        }
    }
}