/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;
public class Solution {
    /**
    * DFS方法解答
    * @param root
    * @return 
    */
    /*
    public int run(TreeNode root) {
        if(root == null) {
            return 0;
        } else if(root.left == null && root.right == null) {
            return 1;
        } else if(root.left == null || root.right == null) {
            return Math.max(run(root.left),run(root.right)) + 1;
        } else {
            return  Math.min(run(root.left),run(root.right))+1;
        }
    }
    */
    public int run(TreeNode root) {
        int res = 0;
        if(root == null) {
            return res;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        res++;
        while(true) {
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).left == null && list.get(i).right == null) {
                    return res;
                }
            }
            res++;
            ArrayList<TreeNode> list1 = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).left != null) {
                    list1.add(list.get(i).left);
                }
                if(list.get(i).right != null) {
                    list1.add(list.get(i).right);
                }
                
            }
            list.clear();
            list = (ArrayList)list1.clone();
            list1.clear();
        }
        
    }
}