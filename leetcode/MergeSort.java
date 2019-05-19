import java.util.Arrays;

/**
 * @author Jeanye
 * 天啊，试验了一下午，这才搞懂链表
 */
public class MergeSort {
    public static ListNode mergeKLists(ListNode[] lists) {
        //倘若lists没有元素,先把空的去掉
        for(int i=0; i < lists.length; i++){
            if(lists[i] == null){
                for(int j=i; j < lists.length-1; j++){
                    lists[j] = lists[j+1];
                }
                lists = Arrays.copyOf(lists, lists.length-1);
                i=-1;
            }
        }
        if(lists.length == 0){
            return null;
        }
        //存储结果链
        ListNode res = null;
        //由于不能让头节点为空，先将头节点确定
        int min = lists[0].val;
        for(int i=1; i < lists.length; i++){
            if(lists[i].val < min){
                min = lists[i].val;
            }
        }
        res = new ListNode(min);
        //将已取出的去掉
        for(int i=0; i < lists.length; i++){
            if(lists[i].val == min){
                //如果此链为空，则去掉
                if(lists[i].next == null){
                    for(int j=i; j < lists.length-1; j++){
                        lists[j] = lists[j+1];
                    }
                    lists = Arrays.copyOf(lists, lists.length-1);
                }else {
                    lists[i] = lists[i].next;
                }
                break;
            }
        }
        //从第二个开始与其余比较
        ListNode temp = res;
        min(lists, temp);
        return res;
    }
    static void min(ListNode[] lists, ListNode temp){
        if(lists.length == 0){
            return;
        }
        int min = lists[0].val;
        for(int i=1; i < lists.length; i++){
            if(lists[i].val < min){
                min = lists[i].val;
            }
        }
        temp.next = new ListNode(min);
        temp = temp.next;
        deleteMin(lists, min, temp);
    }
    static void deleteMin(ListNode[] lists, int min, ListNode temp){
        //将min找到，并将其去掉
        for(int i=0; i < lists.length; i++){
            if(lists[i].val == min){
                //如果此链为空，则去掉
                if(lists[i].next == null){
                    for(int j=i; j < lists.length-1; j++){
                        lists[j] = lists[j+1];
                    }
                    //数组缩容
                    lists = Arrays.copyOf(lists, lists.length-1);
                }else {
                    lists[i] = lists[i].next;
                }
                break;
            }
        }
        min(lists, temp);
    }
    public static void main(String[] args){
//        ListNode l1 = new ListNode(0);
//        addData(l1,1);
//        addData(l1,2);
//        addData(l1,3);
//        delete(l1);
//        while (l1 != null){
//            System.out.println(l1.val);
//            l1 = l1.next;
//        }
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1);
        addData(lists[0],4);
        addData(lists[0],5);
        lists[1] = new ListNode(1);
        addData(lists[1],3);
        addData(lists[1],4);
        lists[2] = new ListNode(2);
        addData(lists[2],6);
        addData(lists[2],8);
//        for(int j=1; j < lists.length-1; j++){
//            lists[j] = lists[j+1];
//        }
        //数组缩容
//        lists = Arrays.copyOf(lists, lists.length-1);
//        for(int i=0; i < lists.length; i++){
//            System.out.println(lists[i].val);
//        }
//        addData(lists[0],5);
        ListNode res = mergeKLists(lists);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
        ListNode t[] = new ListNode[3];
        for(int i=0; i < t.length; i++){
            System.out.println("hello"+i);
            if(t[i] == null){
                for(int j=i; j < t.length-1; j++){
                    t[j] = t[j+1];
                }
                t = Arrays.copyOf(t, t.length-1);
                i=-1;
            }
        }
        System.out.println(t.length);
    }
    public static void delete(ListNode l1) {
        ListNode temp = l1;
        temp = temp.next;
        temp.next = temp.next.next;
    }
    public static void addData(ListNode l1, int value) {
        //临时节点
        ListNode newNode = new ListNode(value);
        //创建临时节点
        ListNode temp = l1;
        //找到尾指针
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
