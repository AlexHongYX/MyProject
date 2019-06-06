/**
 * Created by xiaoaxiao on 2019/6/6
 * Description:判断回文列表
 */
public class PalindromeList {

    public boolean chkPalindrome(ListNode A) {
        // write code here
        int[] arr = new int[1000];
        int len = 0;
        for(ListNode cur=A;cur!=null;cur=cur.next){
            arr[len++] = cur.val;
        }
        int ret = 0;
        for(int i=0;i<len;i++){
            if(arr[i]!=arr[len-i-1]){
                ret = 1;
                break;
            }
        }
        if(ret==0){
            return true;
        }
        return false;
    }
}
