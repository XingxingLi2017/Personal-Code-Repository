import java.util.Random;

public class MergeSort {
    public static void main(String[] args)
    {
        Random rand = new Random();
        int len = 20;
        int[] arr = new int[len];
        // generate random numbers
        for(int i = 0 ; i < len ; i++){
            arr[i] = rand.nextInt(21);
        }

        for(int i = 0 ; i < len ; i++){
            System.out.print(arr[i]+", ");
        }

        mergeSort(arr);

        // print out
        System.out.println();
        for(int i = 0 ; i < len ; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();

    }

    public static void mergeSort(int[] arr){
        int[] newArr = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, newArr);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] newArr)
    {
        // recursion end condition
        if(left >= right)
            return ;

        // divide
        int median = (left+right)/2;
        mergeSort(arr, left, median, newArr);
        mergeSort(arr, median+1, right, newArr);

        // merge two parts into new arr
        for(int i = left, j = median+1, k =left ; k <= right ; k++) {
            if (j > right) {
                newArr[k] = arr[i++];
                continue;
            }
            if (i > median) {
                newArr[k] = arr[j++];
                continue;

            }
            if (arr[i] <= arr[j])
                newArr[k] = arr[i++];
            else
                newArr[k] = arr[j++];
        }
        // update old arr
        for(int i = left ; i <= right ; i++){
            arr[i] = newArr[i];
        }
    }
}
