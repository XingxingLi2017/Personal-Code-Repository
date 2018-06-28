public class Solution {
  public int[] mergeSort(int[] array) {
    // Write your solution here
    if(array == null || array.length <= 1)
      return array;
   return divideNMerge(array,0,array.length-1);

  }
  private int[] divideNMerge(int[] arr, int start, int end)
  {
    if(start >= end){
      int[] res = new int[1];
      res[0] = arr[start];
      return res;
    }
    // divide
    int mid = start+(end-start)/2;
    int[] left = divideNMerge(arr,start,mid);
    int[] right = divideNMerge(arr,mid+1,end);
    // merge
    return mergeArrays(left,right);
  }
  private int[] mergeArrays(int[] arr1, int[] arr2)
  {
    int[] res = new int[arr1.length+arr2.length];
    int p0 =0;int p1 = 0;int p2 = 0;
    while(p1 < arr1.length && p2 < arr2.length)
    {
      if(arr1[p1] <= arr2[p2])
      {
        res[p0++] = arr1[p1++];
      }else{
        res[p0++] = arr2[p2++];
      }
    }
    for(;p1<arr1.length;p1++)
      res[p0++] = arr1[p1];
    for(;p2<arr2.length ; p2++)
      res[p0++] = arr2[p2];
    return res;
  }
}
