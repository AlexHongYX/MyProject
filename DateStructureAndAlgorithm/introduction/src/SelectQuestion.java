/**
 * Created by xiaoaxiao on 2019/5/31
 * Description:一组N个数中确定其中第k个最大者（目前令k=N/2）
 */
public class SelectQuestion {

    public static void main(String[] args){
        int[] arr = {1,2,3,6,2,4,5,7,7,2,11,6,8,9,7,9,45,4,47,94,949,4};

        int k = arr.length/2;

        SelectQuestion selectQuestion = new SelectQuestion();

        int kMax = selectQuestion.selectK(arr,k);

        System.out.println("kMax = "+kMax);
    }
    //先将前k个元素读入数据并以递减的顺序排序，将剩下的元素诸葛读入，当新元素比第k个元素大就将其让在数组正确位置上
//    public int selectK(int[] arr,int k){
//        int[] tmp = new int[k];
//
//        for(int i=0;i<k;i++){
//            tmp[i] = arr[i];
//        }
//
//        bubbleSort(tmp);
//
//        for(int i=k;i<arr.length;i++){
//            if(arr[i]>tmp[k-1]){
//                tmp[k-1] = arr[i];
//                bubbleSort(tmp);
//            }
//        }
//
//        return tmp[k-1];
//    }
//
    public void bubbleSort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-i-1;j++){
                if(array[j]<array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }
    public int selectK(int[] arr,int k){
        bubbleSort(arr);
        return arr[k-1];
    }
}
