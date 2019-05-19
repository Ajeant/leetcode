import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        if ("".equals(s)) {
            return false;
        }
        // 匹配整数,不能0开头
        String reg1 = "[-+]?(0|([1-9][0-9]*))";
        // 匹配小数
        String reg2 = "[-+]?(0|([1-9][0-9]*))\\.[0-9]+";
        // 匹配科学计数，小数
        String reg3 = "[-+]?(0|([1-9][0-9]*))\\.[0-9]+(E|e)[-+]?[1-9][0-9]*";
        // 匹配科学计数，整数
        String reg4 = "[-+]?(0|([1-9][0-9]*))(E|e)[-+]?[1-9][0-9]*";
        Pattern pattern1 = Pattern.compile(reg1);
        Pattern pattern2 = Pattern.compile(reg2);
        Pattern pattern3 = Pattern.compile(reg3);
        Pattern pattern4 = Pattern.compile(reg4);
        Matcher matcher1 = pattern1.matcher(s);
        Matcher matcher2 = pattern2.matcher(s);
        Matcher matcher3 = pattern3.matcher(s);
        Matcher matcher4 = pattern4.matcher(s);
        System.out.println(matcher1.matches());
        if (matcher1.matches() || matcher2.matches() || matcher3.matches() || matcher4.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 题目比较猛，.3，等等不是数也应该匹配成功
     * @param s
     * @return
     */
    public boolean isNumber1(String s) {
        s = s.trim();
        if ("".equals(s)) {
            return false;
        }
        /*// 匹配整数,不能0开头
        String reg1 = "[-+]?([0-9]*)";
        // 匹配小数
        String reg2 = "[-+]?([0-9]*)\\.[0-9]+";
        String reg5 = "[-+]?([0-9]+)\\.[0-9]*";
        // 匹配科学计数，小数
        String reg3 = "[-+]?([0-9]*)\\.[0-9]+(E|e)[-+]?[0-9]+";
        String reg6 = "[-+]?([0-9]+)\\.[0-9]*(E|e)[-+]?[0-9]+";
        // 匹配科学计数，整数
        String reg4 = "[-+]?([0-9]+)(E|e)[-+]?[0-9]+";
        Pattern pattern1 = Pattern.compile(reg1);
        Pattern pattern2 = Pattern.compile(reg2);
        Pattern pattern3 = Pattern.compile(reg3);
        Pattern pattern4 = Pattern.compile(reg4);
        Pattern pattern5 = Pattern.compile(reg5);
        Pattern pattern6 = Pattern.compile(reg6);
        Matcher matcher1 = pattern1.matcher(s);
        Matcher matcher2 = pattern2.matcher(s);
        Matcher matcher3 = pattern3.matcher(s);
        Matcher matcher4 = pattern4.matcher(s);
        Matcher matcher5 = pattern5.matcher(s);
        Matcher matcher6 = pattern6.matcher(s);*/
        // 整合成一句
//        String reg = "([-+]?([0-9]*))|([-+]?([0-9]*)\\.[0-9]+)|([-+]?([0-9]+)\\.[0-9]*)|([-+]?([0-9]*)\\.[0-9]+(E|e)[-+]?" +
//                "[0-9]+)|([-+]?([0-9]+)\\.[0-9]*(E|e)[-+]?[0-9]+)|([-+]?([0-9]+)(E|e)[-+]?[0-9]+)";
        // 继续整合
        String reg = "[-+]?((([0-9]*))|([0-9]*\\.[0-9]+)|([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+(E|e)[-+]?" +
                "[0-9]+)|(([0-9]+)\\.[0-9]*(E|e)[-+]?[0-9]+)|(([0-9]+)(E|e)[-+]?[0-9]+))";
        if (Pattern.matches(reg, s)) {
            return true;
        }
//        if (matcher1.matches() || matcher2.matches() || matcher3.matches() || matcher4.matches() || matcher5.matches() || matcher6.matches()) {
//            return true;
//        }
        return false;
    }
    public static void main(String[] args) {
        IsNumber s = new IsNumber();
        boolean res = s.isNumber1("-1.");
        System.out.println(res);
    }
}
