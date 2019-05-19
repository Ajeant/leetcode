import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        //存放最大字串
        List<Character> substring = new ArrayList<>();
        //记录最大字串大小
        int sizeMax = 0;
        int size = 0;
        //记录位置
        int a;
        for(int i=0; i<c.length; i++){
            if(!substring.contains(c[i])){
                substring.add(c[i]);
                //增加一个字符，size加一
                ++size;
            }else{
                //取出众多size中的最大值
                if(size > sizeMax){
                    sizeMax = size;
                }
                //将size置为截断之后的长度
                size -= substring.indexOf(c[i])+1;
                a = substring.indexOf(c[i]);
                for(int j=0; j<=a; j++){
                    substring.remove(substring.get(0));
                }
                System.out.println(substring.size());
                substring.add(c[i]);
                size++;
            }
        }
        if(size > sizeMax){
            sizeMax = size;
        }
        return sizeMax;
    }
    public static void main(String args[]){
//        String s = "ggububgvfk";
//        System.out.println(lengthOfLongestSubstring(s));
//        int[] nums1 = {
//                1,2,4
//        };
//        int[] nums2 = {
//                3
//        };
//        System.out.println(findMedianSortedArrays(nums1, nums2));
//        String s = "abbafd";
//        System.out.println(longestPalindrome(s));
//        String s = "PAYPALISHIRING";
//        System.out.println(convert(s, 2));
//        String s = "42";
//        System.out.println(myAtoi(s));
//        System.out.println(isMatch("mississippi", "mis*is*p*."));
//        System.out.println(maxArea(new int[]{1,2,3,4,5,6,7,8,9,10}));
        System.out.println(intToRoman(1994));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //中位数的位置
        int medium;
        //中位数的大小
        double result;
        int local=0;
        int index=0;
        //num1为空
        if(nums1.length == 0){
            if(nums2.length % 2 == 0){
                result = (nums2[nums2.length/2] + nums2[nums2.length/2 - 1]) / 2.0;
                return result;
            }else {
                if(nums2.length == 1){
                    result = nums2[0];
                    return result;
                }
                result = nums2[nums2.length/2];
                return result;
            }
        }
        //num2为空
        if(nums2.length == 0){
            if(nums1.length % 2 == 0){
                result = (nums1[nums1.length/2] + nums1[nums1.length/2 - 1]) / 2.0;
                return result;
            }else {
                if(nums1.length == 1){
                    result = nums1[0];
                    return result;
                }
                result = nums1[nums1.length/2];
                return result;
            }
        }
        //以短的为移动比较
        if(nums2.length < nums1.length){
            //判断奇数偶数
            if((nums1.length + nums2.length) % 2 == 0){
                medium = (nums1.length + nums2.length) / 2;
                for(int i=0; i<nums2.length; i++){
                    if(nums2[i] < nums1[index]){
                        ++local;
                        if(local == medium){
                            //已经遍历完了
                            if(i+1 == nums2.length){
                                result = (nums2[i] + nums1[index]) / 2.0;
                                return result;
                            }
                            //没遍历完，比较
                            if(nums2[i+1] > nums1[index]){
                                result = (nums2[i] + nums1[index]) / 2.0;
                                return result;
                            }else{
                                result = (nums2[i] + nums2[i+1]) / 2.0;
                                return result;
                            }
                        }

                    }else{
                        ++local;
                        if(local == medium){
                            //没遍历完了
                            if(index+1 < nums1.length){
                                if(nums1[index+1]<nums2[i]){
                                    result = (nums1[index]+nums1[index+1])/2.0;
                                    return result;
                                }else {
                                    result = (nums1[index]+nums2[i])/2.0;
                                    return result;
                                }
                            }
                            result = (nums1[index]+nums2[i])/2.0;
                            return result;
                        }else{
                            ++index;
                            //遍历完了
                            if(index == nums1.length){
                                result = (nums1[index-1]+nums2[i-1]) / 2.0;
                                return result;
                            }
//                            if(i != 0){
//                                --i;
//                            }
                            --i;
                        }
                    }
                }
                result = (nums1[medium-nums2.length-1]+nums1[medium-nums2.length]) / 2.0;
            }else{
                medium = (nums1.length + nums2.length) / 2 + 1;
                for(int i=0; i<nums2.length; i++){
                    if(nums2[i] < nums1[index]){
                        ++local;
                        if(local == medium){
                            result = nums2[i];
                            return result;
                        }

                    }else{
                        ++local;
                        if(local == medium){
                            result = nums1[index];
                            return result;
                        }else{
                            ++index;
//                            if(i != 0){
//                                --i;
//                            }
                            --i;
                        }
                    }
                }
                result = nums1[medium-nums2.length-1];
                return result;
            }
        }else{
            //判断奇偶
            if((nums1.length + nums2.length) % 2 == 0){
                medium = (nums1.length + nums2.length) / 2;
                for(int i=0; i<nums1.length; i++){
                    if(nums1[i] < nums2[index]){
                        //位置加一
                        ++local;
                        if(local == medium){
                            if(i+1 == nums1.length){
                                result = (nums1[i] + nums2[index]) / 2.0;
                                return result;
                            }
                            if(nums1[i+1] > nums2[index]){
                                result = (nums1[i] + nums2[index]) / 2.0;
                                return result;
                            }else{
                                result = (nums1[i] + nums1[i+1]) / 2.0;
                                return result;
                            }
                        }
                    }else{
                        ++local;
                        if(local == medium){
                            if(index+1 < nums2.length){
                                if(nums2[index+1]<nums1[i]){
                                    result = (nums2[index]+nums2[index+1])/2.0;
                                    return result;
                                }else {
                                    result = (nums2[index]+nums1[i])/2.0;
                                    return result;
                                }
                            }
                            result = (nums2[index]+nums1[i])/2.0;
                            return result;
                        }
//                        if(index == nums1.length){
//                            result = (nums1[index-1]+nums2[i-1]) / 2.0;
//                            return result;
//                        }
//                        if(i != 0){
//                            --i;
//                        }
                        //位置不需动
                        --i;
                        ++index;

                    }
                }
                result = (nums2[medium-nums1.length-1]+nums2[medium-nums1.length]) / 2.0;
                return result;
            }else{
                medium = (nums1.length + nums2.length) / 2 + 1;
                for(int i=0; i<nums1.length; i++){
                    if(nums1[i] < nums2[index]){
                        ++local;
                        if(local == medium){
                            result = nums1[i];
                            return result;
                        }
                    }else{
                        ++local;
                        if(local == medium){
                            result = nums2[index];
                            return result;
                        }
                        ++index;
//                        if(i != 0){
//                            --i;
//                        }
                        --i;

                    }
                }
                result = nums2[medium-nums1.length-1];
                return result;
            }
        }

        return result;
    }
    public static String longestPalindrome(String s) {
        char[] c = s.toCharArray();
        if(c.length == 0 || s == null){
            return "";
        }
        //判断当前是否为最大
        int max1 = 1;
        int max2 = 1;
        //开始、结束位置
        int start = 0;
        int end = 1;
        //遍历所有元素,判断palindrome为奇数时
        for(int i=0; i<c.length; i++){
            //判断是否为回文
            //如果i-j>=0,i+j<c.length可以判断
            for(int j=1; i-j >= 0 && i+j < c.length; j++){
                if(c[i-j] == c[i+j]){
                    max1 += 2;
                }else{
                    break;
                }
            }
            if(max1 >= max2){
                max2 = max1;
                start = i - max2/2;
                end = i + max2/2 + 1;
            }
            //将max1重新赋值为1
            max1 = 1;
        }
        //遍历所有元素,判断palindrome为偶数时
        for(int i=0; i<c.length-1; i++){
            //判断是否为回文
            //如果相邻两个数相同，继续判断i-j,i+j+1
            if(c[i] == c[i+1]){
                //max1每次初始为2
                max1 = 2;
                //如果奇数时最大为一，置其为2
                if(max1 > max2){
                    start = i;
                    end = i+2;
                    max2 = max1;
                }
                for(int j=1; i-j >= 0 && i+j+1 < c.length; j++){
                    if(c[i-j] == c[i+1+j]){
                        max1 += 2;
                        System.out.println(max1);
                    }else {
                        break;
                    }
                }
                if(max1 > max2){
                    start = i -max1/2+1;
                    end = i +max1/2+1;
                    max2 = max1;
                }
            }

        }
        //获取需要的字符串
        char[] result = new char[end-start];
        int index = 0;
        for(int k=start; k<end; k++){
            result[index++] = c[k];
        }

        return String.valueOf(result);
    }
    public static String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        char[] c = s.toCharArray();
        //存储结果数组
        List<Character> list = new ArrayList<>();
        //index 为行数，共有numRows行
        int index = 0;
        //记录行数
        int temp = index;
        while (temp < numRows) {
            while (index < c.length) {
                //每个循环的第一个列第一个数和最后元素都只有一个，中间有两个
                if (temp == 0 || temp == numRows - 1) {
                    list.add(c[index]);
                    index += 2 * numRows - 2;
                } else {
                    list.add(c[index]);
                    if(index + 2*numRows - 2 - 2*temp < c.length) {
                        list.add(c[index + 2*numRows - 2 - 2*temp]);
                    }
                    index += 2 * numRows - 2;
                }
            }
            ++temp;
            index = temp;
        }
        return list.toString();
    }
    public int reverse(int x) {
        int result = 0;
        while(x != 0){
            int pop = x % 10;
            x /= 10;
            //解决上限问题
            if(result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE/10 ||
                    (result == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;
            }
            result = result * 10 + pop;
        }
        return result;
    }
    public static int myAtoi(String str) {
        //清除空格
        str = str.trim();
        String pattern = "[+-]?\\d{1,10}";
        boolean isMatch = Pattern.matches(pattern, str);

        if("".equals(str)){
            return 0;
        }else if(isMatch == false){
            return 0;
        }
        char[] c = str.toCharArray();
        int length = c.length - 2;
        int result = 0;
        //判断第一个符号，是正是负，或者没有
        if(c[0] == '-'){
            //从最后一个到第一个
            for(int i=1; i < c.length; i++){
                if(c[i] != 0){
                    result += (c[i]-48)*Math.pow(10, length--);
                }
            }
            result = -result;
            return result;
        }else if(c[0] == '+'){
            //从最后一个到第一个
            for(int i=1; i < c.length; i++){
                if(c[i] != 0){
                    result += (c[i]-48)*Math.pow(10, length--);
                }
            }
            return result;
        }else{
            length += 1;
            //从最后一个到第一个
            for(int i=0; i < c.length; i++){
                if(c[i] != 0){
                    result += (c[i]-48)*Math.pow(10, length--);
                }
            }
            return result;
        }
    }
    
    public static int maxArea(int[] height) {
        int result = 0;
        int start = 0;
        int end = height.length-1;
        //只要左边小于右边，表示可以盛水
        while(start < end){
            if(height[start] > height[end]){
                if((end-start) * height[end] > result){
                    result = (end-start) * height[end];
                }
                //将小的前移一位
                end--;
            }else{
                if((end-start) * height[end] > result){
                    if((end-start) * height[start] > result) {
                        result = (end - start) * height[start];
                    }
                }
                //将小的前移一位
                start++;
            }
        }
        return result;
    }
    public static String intToRoman(int num) {
        List<String> result = new ArrayList<>();
        //送来一个数，看他与哪个靠近，如果大于一千，直接上M
        while (num >= 1000){
            result.add("M");
            num -= 1000;
        }
        int hundred = num / 100;
        num %= 100;
        switch (hundred){
            case 0:
                break;
            case 1:
                result.add("C");
                break;
            case 2:
                result.add("CC");
                break;
            case 3:
                result.add("CCC");
                break;
            case 4:
                result.add("CD");
                break;
            case 5:
                result.add("D");
                break;
            case 6:
                result.add("DC");
                break;
            case 7:
                result.add("DCC");
                break;
            case 8:
                result.add("DCCC");
                break;
            case 9:
                result.add("CM");
                break;
        }
        int ten = num / 10;
        num %= 10;
        switch (ten){
            case 0:
                break;
            case 1:
                result.add("X");
                break;
            case 2:
                result.add("XX");
                break;
            case 3:
                result.add("XXX");
                break;
            case 4:
                result.add("XL");
                break;
            case 5:
                result.add("L");
                break;
            case 6:
                result.add("LX");
                break;
            case 7:
                result.add("LXX");
                break;
            case 8:
                result.add("LXXX");
                break;
            case 9:
                result.add("XC");
                break;
        }
        switch (num){
            case 0:
                break;
            case 1:
                result.add("I");
                break;
            case 2:
                result.add("II");
                break;
            case 3:
                result.add("III");
                break;
            case 4:
                result.add("IV");
                break;
            case 5:
                result.add("V");
                break;
            case 6:
                result.add("VI");
                break;
            case 7:
                result.add("VII");
                break;
            case 8:
                result.add("VIII");
                break;
            case 9:
                result.add("IX");
                break;
        }
        return String.join("",result);
    }
}