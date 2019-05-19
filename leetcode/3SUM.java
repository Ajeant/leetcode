class 3SUM {
	public static void main(String args[]){
		
	}
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //先排序
        Arrays.sort(nums);
        int temp = 0;
        //先取一个数，用其余两数相加
        for(int i=0; i < nums.length-2; i++){
            //判断当前值与上一个是否相同，相同将可能导致结果集相同
            while(i != 0 && i < nums.length-2 && nums[i-1] == nums[i]){
                i++;
            }
            for(int j=i+1; j < nums.length-1; j++){
                //判断当前值与上一个是否相同，相同将可能导致结果集相同
                while(j != i+1 && j < nums.length-1 && nums[j-1] == nums[j]){
                    j++;
                }
                for(int k=j+1; k < nums.length; k++){
                    //排序后一旦前两个数与后面一个数相加大于0，结束本次循环
                    if(nums[i]+nums[j]+nums[k] == 0){
                        //将答案为0的装入结果数组
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        break;
                    }else if(nums[i]+nums[j]+nums[k] > 0){
                        break;
                    }else {
                        continue;
                    }
                }
            }
        }
        return res;
    }
}