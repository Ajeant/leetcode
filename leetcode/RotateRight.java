/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
 public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null){
            return head;
        }
        // 数一下有几个节点
        int count = 0;
        ListNode countNode = head;
        while (countNode != null) {
            countNode = countNode.next;
            count++;
        }
        k %= count;
        if (k == 0){
            return head;
        }

        ListNode res = new ListNode(0);
        // 复制一条链表
        ListNode headTemp = copyListNode(head);
        // 找到倒数第k个
        ListNode fastTemp = headTemp;
        ListNode slowTemp = headTemp;
        ListNode fast = head;
        ListNode slow = head;

        // 移动k个位置
        for (int i = 0; i < k; i++){
            fast = fast.next;
            fastTemp = fastTemp.next;
        }
//        while (headTemp != null) {
//            System.out.print(headTemp.val + "\t");
//            headTemp = headTemp.next;
//        }
//        System.out.println();
//        while (fast != null) {
//            System.out.print(fast.val + "\t");
//            fast = fast.next;
//        }
        // 这样复制指针不行，只好复制对象
//        ListNode temp = fast;
//        ListNode temp = copyListNode(fast);
        while (fast != null) {
            // 快慢一起走
            if (fastTemp.next != null)
                slowTemp = slowTemp.next;
            fast = fast.next;
            fastTemp = fastTemp.next;
            slow = slow.next;
        }// 循环结束后，slow的位置就是需要前移到位置
        slowTemp.next = null;
//        while (slow != null) {
//            System.out.print(slow.val + "\t");
//            slow = slow.next;
//        }
        res.next = slow;
        while (slow.next != null) {
            slow = slow.next;
        }
//        while (res != null) {
//            System.out.print(res.val + "\t");
//            res = res.next;
//        }
        slow.next = headTemp;
        return res.next;
    }
    
    ListNode copyListNode(ListNode l) {
        ListNode res = new ListNode(0);
        ListNode add = res;
        while (l != null) {
            add.next = new ListNode(l.val);
            l = l.next;
            add = add.next;
        }
        return res.next;
    }
    public static void main(String[] args) {
        RotateRight r = new RotateRight();
        ListNode head = new ListNode(1);
        ListNode add = head;
        add.next = new ListNode(2);
        add = add.next;
        add.next = new ListNode(3);
        add = add.next;
        add.next = new ListNode(4);
        add = add.next;
        add.next = new ListNode(5);
        add = add.next;
//        while (head != null) {
//            System.out.print(head.val + "\t");
//            head = head.next;
//        }
        ListNode res = r.rotateRight(head, 6);
        while (res != null) {
            System.out.print(res.val + "\t");
            res = res.next;
        }
        
        // test copyListNode
//        ListNode res = r.copyListNode(head);
//        System.out.println(res.hashCode());
//        System.out.println(head.hashCode());
//        while (res != null) {
//            System.out.print(res.val + "\t");
//            res = res.next;
//        }
    }
}