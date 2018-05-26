package Sort;

import java.util.Random;

public class InsertionSort {
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
        // sort
        new InsertionSort().insertionSort(arr);

        // print out
        System.out.println();
        for(int i = 0 ; i < len ; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();

    }

    public  void insertionSort(int[] arr) {
        //
        if(arr.length < 2) return;
        // unsorted array: arr[1,length-1]
        for(int i = 1 ; i < arr.length ; i++){
            int j = i;
            // move forward to find right position of the current arr[i]
            while(j > 0 && arr[j] < arr[j-1]) {
                swap(arr, j, j-1);
                j--;
            }
        }
    }
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
