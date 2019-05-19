package leecode;

public class IsMatch {
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[s.length()][p.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) != '*')
                break;
            else
                //后路可通
                match[s.length()][i] = true;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                    match[i][j] = match[i + 1][j + 1];
                else if (p.charAt(j) == '*')
                    match[i][j] = match[i + 1][j] || match[i][j + 1];
                else
                    match[i][j] = false;
            }
        }
        return match[0][0];
    }
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[p.length() + 1][s.length() + 1];
        match[0][0] = true;
        //从后向前判断，往斜上方，若当前是*，可回溯然后让其左移
        int m=0;
        int n=0;
        //往斜下方走，遇到*，则将后面全部变为true
        while (m < p.length() && n < s.length()){
            for(int i=n+1; i <= s.length(); i++){
                //只要他的斜上方为true，就要判断当前是否为true
                if(match[m][i-1] == true){
                    if(p.charAt(m) == '*'){
                        //后面全部变为true,加上前面一个，而且n得减1，因为他可以匹配0个字符
                        for(int j=i-1; j <= s.length(); j++){
                            match[m+1][j] = true;
                        }
                        break;
                    }else if(p.charAt(m) == s.charAt(i-1) || p.charAt(m) == '?'){
                        match[m + 1][i] = match[m][i-1];
                        //n为第一个为true的值-1
                        
                    }else {
                        continue;
                    }
                }
            }
            
            for(int j=0; j <= s.length(); j++){
                if(match[m+1][j] == true){
                    n = j;
                    break;
                }
            }
            ++m;
        }
        //p未遍历完
        while (m < p.length()){
            if(p.charAt(m) == '*'){
                for(int i=0; i <= s.length(); i++){
                    match[m+1][i] = match[m][i];
                }
            }else if(p.charAt(m) == '?' || (s.length() > 0 && p.charAt(m) == s.charAt(s.length()-1))){
                for(int i=1; i <= s.length(); i++){
                    match[m+1][i] = match[m][i-1];
                }
            }else{
                
            }
            m++;
        }
        return match[p.length()][s.length()];
    }

    public static void main(String[] args) {
        IsMatch i = new IsMatch();
//        boolean res = i.isMatch1("abefcdgiescdfimde","ab*cd?i*de");
        boolean res = i.isMatch1("adceb","*a*b");
        System.out.println(res);
    }
}
