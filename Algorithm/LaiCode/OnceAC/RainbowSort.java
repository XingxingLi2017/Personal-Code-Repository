public class Solution {
  public int[] rainbowSort(int[] array) {
    // Write your solution here
    if(array.length <= 1 || array == null)
      return array;
    int p0 = 0 ;
    int p1 = 0 ;
    int p2 = array.length-1 ;
    while(p1 <= p2)
    {
      if(array[p1] == -1)
        // from the definition of p0 and p1
        // the element from p0 must be 0
        // so we can use p1++
        swap(array,p0++,p1++);
      else if(array[p1] == 0 )
        p1++;
      else
        // the element from p2 is unexplored
        // so we can't use p1++
        swap(array,p1,p2--);
    }
    return array;
  }
  private void swap(int[] arr, int i, int j)
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
