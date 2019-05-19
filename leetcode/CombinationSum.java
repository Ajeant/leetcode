package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //先排序
        Arrays.sort(candidates);
        //从第一个开始加，直到加上后大于target结束
        //需采用试探法
        List<Integer> list = new ArrayList<>();
        int result = 0;
        A:for(int i=0; i < candidates.length; i++){
            list.add(candidates[i]);
            result += candidates[i];
            if(result == target){
                res.add(list);
                break;
            }
            if(result > target){
                break;
            }
            B:for(int j=i; j < candidates.length; j++){
                if(result+candidates[j] < target){
                    //可行，留在当前
                    list.add(candidates[j]);
                    result += candidates[j];
                    j -= 1;
                    continue;
                }else if(result+candidates[j] > target){
                    if(list.size() == 1){
                        break;
                    }
                    while(list.get(list.size()-1) == candidates[candidates.length-1]){
                        result -= list.get(list.size() - 1);
                        list.remove(list.size() - 1);
                        if(list.size() == 1){
                            break B;
                        }
                    }
                    //找到list最后一个元素的位置，等会从此位置后一位开始
                    for(int k=0; k < candidates.length; k++){
                        if(candidates[k] == list.get(list.size()-1)){
                            j = k;
                            result -= list.get(list.size() - 1);
                            list.remove(list.size() - 1);
                            break;
                        }
                    }
                }else {
                    list.add(candidates[j]);
                    result += candidates[j];
                    res.add((List<Integer>) ((ArrayList<Integer>) list).clone());
                    //如果list恰好两个，此后不会有适合的
                    if(list.size() == 2){
                        break;
                    }
//                    result = result - list.get(list.size() - 2);
                    boolean flag = false;
                    while(list.get(list.size()-1) == candidates[candidates.length-1]){
                        flag = true;
                        result -= list.get(list.size() - 1);
                        list.remove(list.size() - 1);
                        if(list.size() == 1){
                            break B;
                        }
                    }
                    //找到与倒数第二个相等的j值，从j+1开始
                    for(int k=0; k < candidates.length; k++){
                        if(flag && candidates[k] == list.get(list.size()-1)){
                            result -= list.get(list.size()-1);
                            list.remove(list.size() - 1);
                            j = k;
                            break;
                        }
                        if(!flag && candidates[k] == list.get(list.size()-2)){
                            j = k;
                            result -= list.get(list.size()-1);
                            list.remove(list.size() - 1);
                            result -= list.get(list.size()-1);
                            list.remove(list.size() - 1);
                            break;
                        }
                    }
                    continue;
                }
            }
            //跑出来了，说明得所有清空
            result = 0;
            list.clear();
        }
        return res;
    }
    public static void main(String[] args){
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        List<List<Integer>> res = new ArrayList<>();
//        res.add((List<Integer>) ((ArrayList<Integer>) list).clone());
//        list.remove(list.size() - 1);
//        System.out.println(res);
//        System.out.println(list);
        CombinationSum c = new CombinationSum();
//        List res = c.combinationSum(new int[]{2,3,7}, 18);
        List res = c.combinationSum(new int[]{2,3,6,7}, 7);
        System.out.println(res);
        A:for(int i=0; i < 5; i++){
            for(int j=0; j < 5; j++){
                System.out.print("i="+i+"j="+j);
                if(j == 3){
                    break A;
                }
            }
            System.out.println();
        }
    }
}
