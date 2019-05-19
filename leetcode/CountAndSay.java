package leecode;

public class CountAndSay {
    /**
     * 一道连题目都看不懂的鬼题
     * 搞了十几分钟发现了，要获取2必须先得到一的值
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if(n == 0){
            return "";
        }
        if(n == 1){
            return "1";
        }
        StringBuilder res = new StringBuilder();
        //要求n，就要求n-1，可用递归
        String temp = "";
        if (n > 1){
            temp = countAndSay(n-1);
        }
        int count = 1;
        for(int i=0; i < temp.length()-1; i++){
            if(temp.length() == 1){
                return "11";
            }else {
                if(temp.charAt(i) == temp.charAt(i+1)){
                    count++;
                }else {
                    res.append(count);
                    res.append(temp.charAt(i));
                    //奇怪，就多了句输出，居然把1ms变成170
//                    System.out.println(res.toString());
                    count = 1;
                }
            }
        }
        res.append(count);
        res.append(temp.charAt(temp.length()-1));
        return res.toString();
    }
    public static void main(String[] args){
        CountAndSay c = new CountAndSay();
        String res = c.countAndSay(7);
        System.out.println(res);
    }
}
