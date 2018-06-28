public class Solution {
  public int[] moveZero(int[] array) {
    if(array.length <=1 || array == null)
      return array;
    int wall = array.length-1;
    for(int i = 0 ; i <= wall ;)
    {
      if(array[i] == 0)
      {
        // based on the definition of wall
        // we need to observer the wall before we swap
        if(array[wall] == 0)
          wall--;
        else
          swap(array, i++,wall--);
      }
      else
        i++;
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
