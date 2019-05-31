/**
 * Created by xiaoaxiao on 2019/5/31
 * Description:递归求解数N的二进制表示中的1的个数（若N为偶数，1的个数和N/2的个数相同，若N为奇数，则1的个数等于N/2的1的个数+1)
 */
public class CountBinary {

    public static void main(String[] args) {

        CountBinary countBinary = new CountBinary();

        int count = countBinary.getOneCount(10);

        System.out.println(count);
    }

    private int getOneCount(int n) {
        if (n % 2 == 0) {     //N为偶数，1的个数和N/2的个数相同
            if (n == 2) {
                return 1;
            }
            return getOneCount(n / 2);
        } else {    //N为奇数，则1的个数等于N/2的1的个数+1
            if (n == 1) {
                return 1;
            }
            return getOneCount(n / 2) + 1;
        }
    }
}
