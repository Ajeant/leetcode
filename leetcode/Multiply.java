package leecode;

import java.math.BigDecimal;

public class Multiply {
    public String multiply(String num1, String num2) {
        if(num1.length() + num2.length() < 9){
            return String.valueOf(Integer.parseInt(num1)*Integer.parseInt(num2));
        }else if(num1.length() + num2.length() < 19){
            return String.valueOf(Long.parseLong(num1)*Long.parseLong(num2));
        }else {
            StringBuilder res = new StringBuilder();
            //两数相乘最大不超过他们之和的位数
            int[] result = new int[num1.length()+num2.length()];
            //result的最后一个位置开始写
            int j = result.length - 1;
            //暂放结果
            int a = 0;
            //将num2拆成n个1位数与num1相乘
            for(int i=num2.length()-1; i >= 0; i--){
                String temp = mul(num1, num2.charAt(i)-'0');
                System.out.println(temp);
                int k = temp.length() - 1;
                while (k >= 0){
                    a = result[j-(temp.length()-k-1)] + (temp.charAt(k)-'0');
                    result[j-(temp.length()-k-1)] = a % 10;
                    if(a / 10 != 0) {
                        result[j - (temp.length() - k - 1) - 1] += a / 10;
                    }
                    k--;
                }
                j--;
            }
            for(int i : result){
                if(res.length() == 0 && i == 0){
                    continue;
                }else {
                    res.append((i));
                }
            }
            return res.toString();
        }
    }

    /**
     * 得到的是大数与与1位数相乘的结果，其中num2只有1位
     * @param num1
     * @param num2
     * @return
     */
    public String mul(String num1, int num2){
//        if(num2 == 0){
//            return "0";
//        }
        StringBuilder res = new StringBuilder();
        StringBuilder num = new StringBuilder();
        num.append(num1);
        StringBuilder temp = new StringBuilder();
        int jinwei = 0;
        //每次截取8个，这样就不会溢出
        while(num.length() > 7){
            //求出当前8位与num2的结果
            int i = Integer.parseInt(num.subSequence(num.length() - 8, num.length()).toString())
                    * num2 + jinwei;
            //求进位
            jinwei = (int)(i / Math.pow(10, 8));
            //已经计算的删除
            num.delete(num.length() - 8, num.length());
            BigDecimal b1 = new BigDecimal(i % Math.pow(10, 8));
            temp.append(b1.toPlainString()).reverse();
            //不足8位用0补上
            while(temp.length() != 8){
                temp.append(0);
            }
            res.append(temp);
            temp.delete(0, temp.length());
        }
        if(num.length() > 0){
            temp.append(Integer.parseInt(num.toString()) * num2 + jinwei).reverse();
            //这里添加后变成科学计数法。。。怎么回事
            res.append(temp);
        }else {
            res.append(jinwei);
        }
        BigDecimal b = new BigDecimal(res.reverse().toString());
        return b.toPlainString();
    }
    public static void main(String[] args){
        Multiply m = new Multiply();
//        String res = m.multiply("9333852702227987", "85731737104263");
        String res = m.mul("9333852702227987", 3);
        System.out.println(res);
        System.out.println((Long)(9333852702227987l*3));
    }
}
