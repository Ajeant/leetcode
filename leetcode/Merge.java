import java.util.*;

class Solution {
    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new IntervalComparator());

        LinkedList<Interval> merged = new LinkedList<Interval>();
        for (Interval interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }

        return merged;
    }

    public int[][] merge(int[][] intervals) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        //将intervals排序，比较每一行的第一列
        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = i + 1; j < intervals.length; j++){
                if(intervals[i][0] > intervals[j][0]){
                    swap(intervals, i, j);
                }
            }
        }
        //现在已经按照start排序
        //如果后一个的start <= end（前一个）,合并
        int start = 0;
        int end;
        boolean flag = false;
        for(int i = 0; i < intervals.length; i++){
            end = intervals[i][1];
            while(i < intervals.length-1 && intervals[i+1][0] <= end){
                if(!flag) {
                    start = intervals[i][0];
                }
                flag = true;
                if(end < intervals[i+1][1]) {
                    end = intervals[i + 1][1];
                }
                i++;
            }
            if(flag) {
                list.add(Arrays.asList(start, end));
                flag = false;
            }else {
                list.add(Arrays.asList(intervals[i][0], intervals[i][1]));
            }
        }
        int[][] res = new int[list.size()][2];
        System.out.println(list);
        for(int i=0; i < list.size(); i++){
            res[i][0] = list.get(i).get(0);
            res[i][1] = list.get(i).get(1);
        }
        return res;
    }
    void swap(int[][] intervals, int i, int j){
        if(i == j){
            return;
        }else {
            intervals[i][1] = intervals[i][1] + intervals[j][1];
            intervals[j][1] = intervals[i][1] - intervals[j][1];
            intervals[i][1] = intervals[i][1] - intervals[j][1];

            intervals[i][0] = intervals[i][0] + intervals[j][0];
            intervals[j][0] = intervals[i][0] - intervals[j][0];
            intervals[i][0] = intervals[i][0] - intervals[j][0];
        }
    }
    public static void main(String[] args){
        Solution s = new Solution();
//        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
//        int[][] intervals = {{1, 4}, {0, 2}, {3, 5}};
        int[][] intervals = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        int[][] res = s.merge(intervals);
        for(int[] a : res){
            for(int b : a){
                System.out.print(b);
            }
            System.out.println();
        }
    }
}
class Interval{
    int start;
    int end;
}