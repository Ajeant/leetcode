class Solution {
    public int firstMissingPositive(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i < nums.length; i++){
            list.add(i+1);
        }
        for(int i=0; i < nums.length; i++){
            if(nums[i] > 0 && nums[i] <= nums.length){
                list.set(nums[i]-1, 0);
            }
        }
        for(int i=0; i < nums.length; i++){
            if(list.get(i) != 0){
                return list.get(i);
            }
        }
        return nums.length+1;
    }
}