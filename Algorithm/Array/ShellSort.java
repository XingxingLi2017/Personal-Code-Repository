package Sort;

import java.util.Random;

public class ShellSort {
    public static void main(String[] args)
    {
        Random rand = new Random();
        int len = 30;
        int[] arr = new int[len];
        // generate random numbers
        for(int i = 0 ; i < len ; i++){
            arr[i] = rand.nextInt(21);
        }

        for(int i = 0 ; i < len ; i++){
            System.out.print(arr[i]+", ");
        }

        // sort
        new ShellSort().shellSort(arr);

        // print out
        System.out.println();
        for(int i = 0 ; i < len ; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();

    }

    public void shellSort(int[] arr){
        if(arr.length < 2)
            return ;
        // reduce the gap to 0
        for(int gap = arr.length/2 ; gap > 0 ; gap /= 2){
            // insertion sort based on gap
            for(int j = gap; j < arr.length ; j++){
                // first element of unsorted part
                int k = j;
                while(k < arr.length && k >= gap && arr[k] < arr[k-gap] ){
                    swap(arr, k, k-gap);
                    k -= gap;
                }
            }
        }
    }
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
