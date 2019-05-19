class Solution {
    public String addBinary(String a, String b) {
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            // 无进位
            if (!flag) {
                // 同为0，加0
                if (a.charAt(i) == '0' && b.charAt(i) == '0') {
                    sb.append('0');
                    flag = false;
                } else if ((a.charAt(i) == '0' && b.charAt(i) == '1') || (a.charAt(i) == '1' && b.charAt(i) == '0')) {
                    sb.append('1');
                    flag = false;
                } else {
                    sb.append('0');
                    flag = true;
                }
            } else {
                // 同为0，加0
                if (a.charAt(i) == '0' && b.charAt(i) == '0') {
                    sb.append('1');
                    flag = false;
                } else if ((a.charAt(i) == '0' && b.charAt(i) == '1') || (a.charAt(i) == '1' && b.charAt(i) == '0')) {
                    sb.append('0');
                    flag = true;
                } else {
                    sb.append('1');
                    flag = true;
                }
            }
        }
        // 将剩余的加上
        if (a.length() > b.length()) {
            for (int i = b.length(); i < a.length(); i++) {
                if (flag) {
                    if (a.charAt(i) == '0') {
                        flag = false;
                        sb.append('1');
                    } else {
                        sb.append('0');
                        flag = true;
                    }
                } else {
                    sb.append(a.charAt(i));
                }
            }
        } else {
            for (int i = a.length(); i < b.length(); i++) {
                if (flag) {
                    if (b.charAt(i) == '0') {
                        flag = false;
                        sb.append('1');
                    } else {
                        sb.append('0');
                        flag = true;
                    }
                } else {
                    sb.append(b.charAt(i));
                }
            }
        }
        if (flag) {
            sb.append('1');
        }
        return String.valueOf(sb.reverse());
    }
}