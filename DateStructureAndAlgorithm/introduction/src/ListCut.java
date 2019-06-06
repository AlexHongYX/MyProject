/**
 * Created by xiaoaxiao on 2019/6/5
 * Description:链表分割（分割完顺序不能变）——插入前半部分最后一个+插入后半部分最后一个
 */
import java.util.*;


public class ListCut {
    public static ListNode partition(ListNode pHead, int x) {
        // write code here
        if(pHead==null){
            return null;
        }
        ListNode newHead1 = null;
        ListNode newHead2 = null;
        ListNode lastNode1 = null;
        ListNode lastNode2 = null;
        ListNode next = null;
        for(ListNode cur=pHead;cur!=null;cur=next){
            next = cur.next;
            if(cur.val<x){
                if(newHead1==null){
                    newHead1 = cur;
                    lastNode1 = newHead1;
                }else {
                    lastNode1.next = cur;
                    lastNode1 = lastNode1.next;
                }
            }else if(cur.val>x){
                if(newHead2==null){
                    newHead2 = cur;
                    lastNode2 = newHead2;
                }else {
                    lastNode2.next = cur;
                    lastNode2 = lastNode2.next;
                }
            }
        }
        if(lastNode1==null){
            return newHead2;
        }
        if(lastNode2!=null){
            lastNode2.next=null;
        }
        lastNode1.next=newHead2;
        return newHead1;
    }

    public static void main(String[] args){
        ListNode a = new ListNode(6);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(8);
        int x = 9;
        a.next = b;
        b.next = c;

        ListNode ret = partition(a,x);

        for(ListNode cur = ret;cur!=null;cur=cur.next){
            System.out.println(cur.val);
        }
    }
}
