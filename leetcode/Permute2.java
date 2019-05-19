class Permute2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res = permute(nums, 0, nums.length-1, res);
        Set<List<Integer>> s = new HashSet<>();
        for(List<Integer> l : res){
            s.add(l);
        }
        res.clear();
        for(List<Integer> l : s){
            res.add(l);
        }
        return res;
    }
    public List<List<Integer>> permute(int[] nums, int start, int end, List<List<Integer>> res) {
        if(start == end){
            //将当前nums加入res
            res.add(addToRes(nums));
        }
        //每交换一次就将交换部分重新进行全排序
        for(int i=start; i <= end; i++){
            // permute(nums, start+1, end, res);
            //start要与后面的每一个进行交换然后进行全排序，相等就不必交换了
            if(nums[start] == nums[i] && start != i){
                continue;
            }
            swap(nums, start, i);
            permute(nums, start+1, end, res);
            swap(nums, start, i);
        }
        return res;
    }

    /**
     * 数组转为list
     * @param nums
     * @return
     */
    public List<Integer> addToRes(int[] nums){
        List<Integer> res = new ArrayList<>();
        for(int n : nums){
            res.add(n);
        }
        return res;
    }

    /**
     * 交换数组内容
     * @param nums
     * @param l
     * @param r
     */
    public void swap(int[] nums, int l, int r){
        if(l == r){
            return;
        }
        nums[l] = nums[l] + nums[r];
        nums[r] = nums[l] - nums[r];
        nums[l] = nums[l] - nums[r];
    }
}