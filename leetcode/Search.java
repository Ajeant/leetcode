package leecode;

public class Search {
    static int searchPivot(int[] nums){
        int left = 0;
        int right = nums.length-1;
        int pivor = 0;
        while (left <= right){
            if(nums[(left+right)/2] < nums[0]){
                pivor = (left+right)/2;
                right = (left+right)/2-1;
            }else {
                left = (left+right)/2+1;
            }

        }
        if(pivor == 0 && nums[0] > nums[nums.length-1]){
            pivor = nums.length-1;
        }
        if(pivor > 0 && nums[pivor-1] < nums[pivor]){
            pivor -= 1;
        }
        return pivor;
    }
    static int search(int[] nums, int target){
        if(nums.length == 0){
            return -1;
        }
        //先找到枢纽吧
        int pivot = searchPivot(nums);
        //0ms,36.8M
        if(pivot == 0){
            return binarySearch(nums, 0, nums.length-1, target);
        }
        if(target < nums[pivot] || target > nums[pivot-1]){
            return -1;
        }else if(target >= nums[pivot] && target <= nums[nums.length-1]){
            return binarySearch(nums, pivot, nums.length-1, target);
        }else {
            return binarySearch(nums, 0, pivot-1, target);
        }
        //1ms,37.7M
//        int res = -1;
       /* res = binarySearch(nums, 0, pivot-1, target);
        if(res == -1){
            res = binarySearch(nums, pivot, nums.length-1, target);
        }
        return res;*/
    }
    static int binarySearch(int[] nums, int left, int right, int target){
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
    public static void main(String[] args){
        int[] nums = {9,10,11,12,13,14,15,1,2,3,4,5,6,7,8};
        int res = search(nums, 10);
        System.out.println(res);
//        int[] nums1 = {4,5,6,7,0,1,2};
        int[] nums1 = {3,4,5,1,2};
        System.out.println(searchPivot(nums1));
    }
}
