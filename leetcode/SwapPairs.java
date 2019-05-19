public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        //递归结束判断
        if(head == null){
            return null;
        }else if(head.next == null){
            return head;
        }else {
            //交换每两个
            ListNode second = head.next;
            //移动指针到第三个
            ListNode third = second.next;
            second.next = head;
            //递归
            head.next = swapPairs(third);
            return second;
        }
    }
}

