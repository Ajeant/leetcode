package leecode;

public class SearchRange {
    //0ms,44.1M
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if(nums.length == 0){
            return res;
        }
        //二分查找
        res[0] = binarySearch(nums, 0, nums.length-1, target);
        if(res[0] == -1){
            return res;
        }else {
            //找到之后向前向后比较
            res[1] = res[0];
            while (res[0] > 0 && nums[res[0]] == nums[res[0]-1]){
                res[0] = res[0]-1;
            }
            while (res[1] < nums.length-1 && nums[res[1]] == nums[res[1]+1]){
                res[1] = res[1]+1;
            }
            return res;
        }
    }
    int binarySearch(int[] nums, int left, int right, int target){
        while (left <= right){
            if(nums[(left+right)/2] < target){
                left = (left+right)/2+1;
            }else if(nums[(left+right)/2] > target){
                right = (left+right)/2-1;
            }else {
                return (left+right)/2;
            }
        }
        return -1;
    }
}
