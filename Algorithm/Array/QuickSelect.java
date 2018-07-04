/*
    1. partition -> get the pivot which [left,pivot]<=arr[pivot]
    2. select
        pivot-left+1 == k -> return arr[pivot];
        pivot-left+1 > k ->return the left part;
        pivot-left+1 < k -> return the right part, k=k-(pivot-left+1)
    Time complexity = O(n)
      => n+n/2+n/4+....+1 <= 2n => O(n)
 */
 class Solution {
 	  public static int qs(int[] arr, int left, int right, int k)
 	  {
      // k is the length of the curernt arr, so k<=right-left+1
 	    if(k>0 && k<=right-left+1){
 	      int pivot = partition(arr, left, right);
 	      if(pivot-left+1 == k ) return arr[pivot];
 	      else if(pivot-left+1 > k) return qs(arr,left,pivot-1,k);
 	      else return qs(arr,pivot+1,right, k-(pivot-left+1));
 	    }
 	    return -1;
 	  }
    // return the pivot;
 	  private static int partition(int[] arr, int left, int right){
 	    int base = arr[right], j=left;
 	    for(int i = left; i < right; i++)
 	      if(arr[i] <= base)
 	        swap(arr,i,j++);
 	    swap(arr,j,right);
 	    return j;
 	  }
 	  private static void swap(int[] nums, int i, int j)
 	  {
 	      int temp = nums[i];
 	      nums[i] = nums[j];
 	      nums[j] = temp;
 	  }
 	}
