/*
*  interface Dictionary {
*    public Integer get(int index);
*  }
*/


/**
  1. get left & right boudary first
    use n = n*2 to test the right boundary of the array

*/
public class Solution {
  public int search(Dictionary dict, int target) {
    if(dict.get(0) == null)
      return -1;
    if(dict.get(1) == null)
      return dict.get(0).intValue() == target? 0: -1;
    // get the right boundary firstly
    int right = 1;
    while(dict.get(right)!=null && dict.get(right) <target)
      right *= 2;
    // test the right boundary
    if(dict.get(right) == null)
    {
       right /= 2;
      while(dict.get(right) != null)
        right++;
    }
    else{
      if(dict.get(right) == target)
        return right;
    }
    // normal binary search
    int left = right/2;
    while(left < right)
    {
      int mid=left+(right-left)/2;
      if(dict.get(mid) == target)
        return mid;
      else if(dict.get(mid) < target)
        left = mid+1;
      else
        right=mid;
    }
    return -1;
  }
}
