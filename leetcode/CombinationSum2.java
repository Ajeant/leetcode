package leecode;

import java.util.*;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //先排序
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        int result = 0;
        List<Integer> list = new ArrayList<>();
        for(int i=0; i < candidates.length; i++){
            if(i > 0 && candidates[i] == candidates[i-1]){
                continue;
            }
            result += candidates[i];
            list.add(candidates[i]);
            if(result == target){
                res.add((List<Integer>) ((ArrayList<Integer>) list).clone());
                break;
            }
            if(result > target || (i == candidates.length-1 && candidates[i] < target)){
                break;
            }
            for(int j=i+1; j < candidates.length; j++){
                if(result + candidates[j] < target){
                    //如果是最后一个，则将当前最后一个删除，然后从其后面开始
                    if(j == candidates.length-1){
                        if(list.size() ==  0){
                            break;
                        }
                        result -= list.get(list.size() - 1);
                        for(int k=j-1; k > 0; k--){
                            if(candidates[k] == list.get(list.size()-1)){
                                j = k;
                                break;
                            }
                        }
                        list.remove(list.size() - 1);
                    }else {
                        result += candidates[j];
                        list.add(candidates[j]);
                    }
                }else if(result + candidates[j] > target){
                    if(list.size() == 1){
                        break;
                    }
                    //去掉最后一个，让其向后
                    result -= list.get(list.size() - 1);
                    for(int k=j-1; k > 0; k--){
                        if(candidates[k] == list.get(list.size()-1)){
                            j = k;
                            break;
                        }
                    }
                    list.remove(list.size() - 1);
                }else {
                    result += candidates[j];
                    list.add(candidates[j]);
                    res.add((List<Integer>) ((ArrayList<Integer>) list).clone());
                    if(list.size() == 2){
                        break;
                    }
                    //删除最后两个，然后退后
                    /*由于有重复，可能会不知道找到的是不是当前所用的，如1,2,2,2,5，会导致不知用的是哪个2，
                    但是可以肯定的是必定是利最后一个往前最近的，也就是离j最近的
                     */
                    result -= list.get(list.size() - 1);
                    list.remove(list.size() - 1);
                    result -= list.get(list.size() - 1);
                    for(int k=j-1; k > 0; k--){
                        if(candidates[k] == list.get(list.size()-1)){
                            j = k;
                            break;
                        }
                    }
                    list.remove(list.size() - 1);
                }
            }
            //跑出来了，需要清空
            result = 0;
            list.clear();
        }
        Set<List<Integer>> set = new HashSet<>();
        for(int i=0; i < res.size(); i++){
            set.add(res.get(i));
        }
        res.clear();
        for(List<Integer> l : set){
            res.add(l);
        }
        return res;
    }
    public static void main(String[] args){
        CombinationSum2 c = new CombinationSum2();
        List res = c.combinationSum2(new int[]{4,1,1,4,4,4,4,2,3,5}, 10);
//        List res = c.combinationSum2(new int[]{1,1,2,5,6,7,10}, 8);
        System.out.println(res);
    }
}
