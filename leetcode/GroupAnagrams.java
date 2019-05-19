package leecode;

import java.util.*;

public class GroupAnagrams {
    /**
     * 归类字符串
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0)
            return null;
        Map<String, List> ans = new HashMap<>();
        for (String s : strs) {
            //将字符串转为字符数组
            char[] ca = s.toCharArray();
            //排序
            Arrays.sort(ca);
            //转回字符串
            String key = String.valueOf(ca);
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
    public static void main(String[] args){
        GroupAnagrams g = new GroupAnagrams();
        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(g.groupAnagrams(s));
    }
}
