package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums.length == 0 || nums.length == 1){
            return;
        }
        //存储顺序的位置
        int temp = 0;
        boolean b = false;
        //从后向前找，找到顺序的那个数
        A:for(int i=nums.length-1; i > 0; i--){
            if(nums[i] > nums[i-1]){
                //找到了，他为i-1
                temp = i-1;
                for (int j = nums.length-1; j > i-1; j--){
                    if(nums[temp] < nums[j]){
                        //交换
                        b = true;
                        nums[temp] = nums[temp] + nums[j];
                        nums[j] = nums[temp] - nums[j];
                        nums[temp] = nums[temp] - nums[j];
                        break A;
                    }
                }
            }
        }
        //有交换就从temp开始，否则从0开始
        if(b == false){
            reverse(nums, temp);
        }else {
            reverse(nums, temp + 1);
        }
    }
    void reverse(int[] nums, int s){
        int n = nums.length;
        for(int i=s; i < (nums.length+s)/2; i++){
            nums[i] = nums[i] + nums[n-1];
            nums[n-1] = nums[i] - nums[n-1];
            nums[i] = nums[i] - nums[n-1];
            n--;
        }
    }
    public static void main(String[] args){
        NextPermutation n = new NextPermutation();
//        int[] nums = {1,2,3,4,5};
//        int[] nums = {2,3,1};
        int[] nums = {1,1,5};
        n.nextPermutation(nums);
        for(int num : nums){
            System.out.print(num+"  ");
        }
//        System.out.println(Arrays.toString(nums));
    }
}
