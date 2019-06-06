/**
 * Created by xiaoaxiao on 2019/6/6
 * Description:
 */
public class MainTest {

    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(3);
        ListNode e = new ListNode(2);
        ListNode f = new ListNode(1);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

        PalindromeList palindromeList = new PalindromeList();
        if(palindromeList.chkPalindrome(a)){
            System.out.println("是回文");
        }else{
            System.out.println("不是回文");
        }
    }
}
