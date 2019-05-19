package leecode;

public class Jump {
    /**
     * 最短跳转步数
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int res = 0;
        //找到第一个可到达最后一个的位置，递归
        int n = nums.length - 1;
        while (n > 0){
            n = search1(nums, n);
            res++;
        }
        return res;
    }
    int search1(int[] nums, int n){
        for(int i=0; i < n; i++){
            if(i + nums[i] >= n || i + 1 >= n){
                return i;
            }
        }
        //到达位置为0或与当前位置一致
        return 0;
    }

    /**
     * 这是不可跳过头的解
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        int res = 0;
        //找到第一个可到达最后一个的位置，递归
        int n = nums.length - 1;
        while (n > 0){
            n = search(nums, n);
            res++;
        }
        return res;
    }

    /**
     * 给定数组和需要到达的位置
     * @param nums
     * @param n
     * @return
     */
    int search(int[] nums, int n){
        for(int i=0; i < n; i++){
            if(i + nums[i] == n || i + 1 == n){
                return i;
            }
        }
        //到达位置为0或与当前位置一致
        return 0;
    }
    public static void main(String[] args){
        Jump j = new Jump();
//        int[] nums = {2, 3, 1, 3, 4, 1, 1};
        int[] nums = {4,1,1,3,1,1,1};
        int res = j.jump(nums);
        System.out.println(res);
    }
}
