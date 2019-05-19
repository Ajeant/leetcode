import java.util.ArrayList;

public class GetPermutation1 {
    /**
     * 在n个数的全排序中，求出第k个序列
     * @param n
     * @param k
     * @return
     */
    public String getPermutation1(int n, int k) {
        StringBuilder res = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        //添加到list
        for (int i = 0; i <= n - 1; i++) {
            list.add(i + 1);
        }
        if (k == 1){
            for(int i=0; i < list.size(); i++){
                res.append(list.get(i));
            }
            return String.valueOf(res);
        }
        if (k == factorial(n)){
            for(int i=list.size()-1; i >= 0; i--){
                res.append(list.get(i));
            }
            return String.valueOf(res);
        }
        while (factorial(list.size() - 1) > k){
            //加到结果集
            res.append(list.get(0));
            list.remove(0);
        }
        while (true) {
            int change = k / factorial(list.size() - 1);
            if (k % factorial(list.size() - 1) == 0){
                //res添加第change-1
                res.append(list.get(change-1));
                list.remove(change - 1);
                for(int i=list.size()-1; i >= 0; i--){
                    res.append(list.get(i));
                }
                break;
            }
            //res添加第change
            res.append(list.get(change));
            list.remove(change);
            k -= change * factorial(list.size());
            if(k == 1) {
                for (int i=0; i < list.size(); i++){
                    res.append(list.get(i));
                }
                break;
            }
        }
        return String.valueOf(res);
    }
    public String getPermutation(int n, int k) {
        if (n == 1) {
            return "1";
        }
        ArrayList<Integer> list = new ArrayList<>();
        //添加到list
        for (int i = 0; i <= n - 1; i++) {
            list.add(i + 1);
        }
        //存储结果集
        StringBuilder stringBuilder = new StringBuilder();
        int contain = k;
        int quotient;
        while (contain != 0) {
            quotient = contain / factorial(n - 1);
            contain = contain % factorial(n - 1);
            quotient = contain == 0 ? quotient : quotient + 1;
            stringBuilder.append(list.get(quotient - 1));
            list.remove(quotient - 1);
            n--;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuilder.append(list.get(i));
        }
        return String.valueOf(stringBuilder);
    }

    /**
     * num的阶乘
     *
     * @param num
     * @return
     */
    public int factorial(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }

    public static void main(String[] args) {
        GetPermutation1 s = new GetPermutation1();
        String res = s.getPermutation1(4, 8);
//        String res = s.getPermutation(6, 12);
        System.out.println(res);
    }
}