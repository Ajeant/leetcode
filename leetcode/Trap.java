package com;

public class Trap {
    public int trap(int[] height) {
        if(height.length == 0 || height.length == 1){
            return 0;
        }
        int res = 0;
        //从左到右，发现右边大于左边，可以盛水了，并从右边继续开始
        int left = height[0];
        int leftLocation = 0;
        //计算第一个左边的值
        for(int i=1; i < height.length; i++){
            if(left < 1){
                left = height[i];
                leftLocation = i+1;
            }else {
                break;
            }
        }
        if(left == 0 || leftLocation == height.length){
            return 0;
        }
        //计算最大值的位置
        int maxLocation = 0;
        for(int i=1; i < height.length; i++){
            if(height[maxLocation] < height[i]){
                maxLocation = i;
            }
        }
        int right = 0;
        for(int i=leftLocation; i <= maxLocation; i++){
            if(height[i] >= left){
                right = height[i];
                //有了left和right，可以计算了
                for(int j=leftLocation; j < i; j++){
                    res = res + (left - height[j]);
                }
                //left的值置为right
                left = right;
                leftLocation = i+1;
            }
        }
        //最大的左边的已计算完毕，开始计算右边
        right = height[height.length - 1];
        int rightLocation = height.length-1;
        for(int i=height.length-1; i > maxLocation; i--){
            if(right < 1){
                right = height[i];
                rightLocation = i-1;
            }else {
                break;
            }
        }
        for(int i=rightLocation; i >= maxLocation; i--){
            if(height[i] >= right){
                left = height[i];
                //有了left和right，可以计算了
                for(int j=rightLocation; j > i; j--){
                    res = res + (right - height[j]);
                }
                //left的值置为right
                right = left;
                rightLocation = i-1;
            }
        }
        return res;
    }
    public static void main(String[] args){
        Trap t = new Trap();
//        int res = t.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        int res = t.trap(new int[]{4,4,4,7,1,0});
        System.out.println(res);
    }
}
