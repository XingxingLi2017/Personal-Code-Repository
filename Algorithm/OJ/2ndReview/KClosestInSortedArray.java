public class Solution {
  public int[] kClosest(int[] arr, int t, int k) {
    if(arr == null || arr.length == 0)
      return arr;
    int[] res = new int[k];
    int left = 0;
    // use window to replace single comparing number
    // right&left indicate the range of the left boundary of the possible best window
    int right = arr.length-k;
    while(left < right)
    {
      int mid=left+(right-left)/2;
      // mid is better than mid+k to join in the window
      // window move left, right=mid
      if(Math.abs(arr[mid]-t) <= Math.abs(arr[mid+k]-t))
        right = mid;
      else
        left = mid+1;
    }

    for(int itr = 0 ; itr < k ; itr++)
    {
      int minIndex=left;
      for(int i = left ; i< left+k ;i++)
      {
        if(Math.abs(arr[i]-t) < Math.abs(arr[minIndex]-t))
        {
          minIndex = i;
        }
      }
      res[itr] = arr[minIndex];
      arr[minIndex] = Integer.MAX_VALUE;
    }
    return res;
  }
}
