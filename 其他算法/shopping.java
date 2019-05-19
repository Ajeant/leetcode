import java.util.Scanner;

public class Main {
    /*
    题目描述
    你要买n件物品，其中有一些是凳子。
    商场正在举行促销活动，如果购物车中有至少一个凳子，那么你可以半价购买这个购物车中最贵的一个物品。
    你有m辆购物车，请最小化你的花费。
    输入描述:
    第一行一个整数t表示数据组数 (1 ≤ t ≤ 100)。
    每组数据第一行两个整数n,m (1 ≤ n,m ≤ 1000)，接下来n行每行两个整数ai,bi，分别表示第i件物品的价格以及它是否是凳子 (1 ≤ ai ≤ 105, 0 ≤ bi ≤ 1)。
    输出描述:
    每组数据输出一行一个实数表示最小花费，保留一位小数。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // t组数
        int t = sc.nextInt();
        double[] res = new double[t];
        for (int i = 0; i < t; i++) {
            // 输入商品数和购物车数
            int m = sc.nextInt();
            int n = sc.nextInt();
            // 计算椅子数量
            int count = 0;
            // 存储最大的购物车个数
            int[] max = new int[n];
            for (int j = 0; j < m; j++) {
                // 输入商品价格
                int temp = sc.nextInt();
                res[i] += temp;
                if (temp > max[0]) {
                    max[0] = temp;
                    sort(max);
                }
                if (sc.nextInt() == 1) {
                    // 若为椅子，椅子数加1
                    count++;
                }
            }
            if (n <= count) {
                // 购物车数量小于等于椅子
                for (int j = 0; j < max.length; j++) {
                    res[i] -= max[j] / 2.0;
                }
            } else {
                for (int j = max.length - 1; count != 0; count--) {
                    res[i] -= max[j] / 2.0;
                    j--;
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            // 可怕，居然还要、必须按规定保留一位输出
            // 而在编译器上。。。它本来就是一位输出，搞得我以为我的想法不对
//            System.out.println(res[i]);
            System.out.println(String.format("%.1f", res[i]));
        }
    }

    /**
     * 调整序列为从小到大
     * @param a
     */
    private static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i+1]) {
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
    }
}