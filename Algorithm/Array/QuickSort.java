public class Solution {
  public int[] quickSort(int[] array) {
    // Write your solution here
    divide(array,0,array.length-1);
    return array;

  }
  private void divide(int[] array, int left, int right)
  {
    if(left >= right)
      return ;
    // partition
    int pivot = partition(array, left, right);
    // divide
    divide(array,left,pivot);
    divide(array,pivot+1, right);
    return ;
  }

  private int partition(int[] arr, int left, int right)
  {
    int mid = left+(right-left)/2;
    int wall = left;
    swap(arr, mid, right);
    for(int i = left ; i <= right ; i++ )
    {
      if(arr[i] < arr[right])
      {
        swap(arr, wall++, i);
      }
    }
    // pay attention to the wall
    if(arr[wall] < arr[right])
       swap(arr, ++wall, right);
    else
      swap(arr, wall, right);
    return wall;
  }

  private void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
