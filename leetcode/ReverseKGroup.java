package leecode;

import java.util.Arrays;

public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode judge = head;
        int judgeCount = 1;
        //如果链的节点数小于2*(k-1)
        for(int i=0; i < 2*(k-1)-1; i++){
            if(judge.next == null){
                if(i < k){
                    return head;
                }else {
                    break;
                }
            }else {
                judge = judge.next;
                judgeCount++;
            }
        }

        ListNode temp = head;
        //移动到结果链的头节点
        for(int i=0; i < k-1; i++){
            if(temp.next != null) {
                temp = temp.next;
            }else {
                break;
            }
        }
        //存储头节点
        ListNode res = temp;
        //temp后面K-1个节点都是res的节点,再接上前面被交换的节点
        for(int i=0; i < k-2; i++){
            if(temp.next != null) {
                temp = temp.next;
            }else {
                break;
            }
        }
        temp.next = head;
        //下一组的头节点
        ListNode nextHead = head;
        for(int i=0; i < 2*(k-1); i++){
            if(nextHead.next != null) {
                nextHead = nextHead.next;
            }else {
                nextHead = nextHead.next;
                break;
            }
        }
        //移动到本组最后，让其后节点为后一组结果链
        for(int i=0; i < k-2; i++){
            if(i == k-3){
                temp.next = reverseKGroup(nextHead,k);
            }else {
                temp = temp.next;
            }
        }
        return res;
    }
    public static ListNode reverseKGroup1(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode res = head;
        //找到需交换的位置
        ListNode temp = head;
        for(int i=0; i < k-1; i++){
            if(temp.next != null) {
                temp = temp.next;
            }else {
                return head;
            }
        }
        //分组交换，只存值就可以
        for(int i=0; i < k-1; i++){
            head.val = head.val + temp.val;
            temp.val = head.val - temp.val;
            head.val = head.val - temp.val;
            if(temp.next != null){
                head = head.next;
                temp = temp.next;
            }else {
                break;
            }
        }
        //递归
        temp.next = reverseKGroup1(temp, k);
        return res;
    }
    public static ListNode reverseKGroup2(ListNode head, int k) {
        if(head == null || head.next == null || k == 1){
            return head;
        }
        //找到第k个，与第一个交换
        ListNode temp = head;
        ListNode res = head;
        for(int i=0; i < k-1; i++){
            if(temp.next != null) {
                temp = temp.next;
            }else {
                return head;
            }
        }
        ListNode tail = temp;
        System.out.println(tail.val);
        if(temp.next != null){
            tail.next = reverseKGroup2(temp.next, k);
        }
        //交换，只存值就可以
        for(int i=0; i < k/2; i++){
            head.val = head.val + temp.val;
            temp.val = head.val - temp.val;
            head.val = head.val - temp.val;
            temp = tempPre(head, k-2*i-1);
            head = head.next;
        }
        return res;
    }
    static ListNode tempPre(ListNode head, int k){
        ListNode temp = head;
        for(int i=0; i < k-1; i++){
            temp = temp.next;
        }
        return temp;
    }
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        MergeSort.addData(head,2);
        MergeSort.addData(head,3);
        MergeSort.addData(head,4);
        MergeSort.addData(head,5);
        ListNode res = reverseKGroup2(head, 4);
        while (res != null){
            System.out.print(res.val+" ");
            res = res.next;
        }
    }
}
