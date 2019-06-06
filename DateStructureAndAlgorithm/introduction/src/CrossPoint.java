/**
 * Created by xiaoaxiao on 2019/6/6
 * Description:
 */
/**
 * Definition for singly-linked list.*/

public class CrossPoint {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = 0;
        int lengthB = 0;
        for(ListNode curA=headA;curA!=null;curA=curA.next){
            lengthA++;
        }
        for(ListNode curB=headB;curB!=null;curB=curB.next){
            lengthB++;
        }
        int differ = 0;
        ListNode newHeadA = headA;
        ListNode newHeadB = headB;

        if(lengthA>lengthB){
            differ = lengthA-lengthB;
            for(int i=0;i<differ;i++){
                newHeadA = newHeadA.next;
            }
        }else{
            differ = lengthB-lengthA;
            for(int i=0;i<differ;i++){
                newHeadB = newHeadB.next;
            }
        }

        while(true){
            if(newHeadA==newHeadB){
                break;
            }
            newHeadA = newHeadA.next;
            newHeadB = newHeadB.next;
        }
        return newHeadA;
    }


}
