/*
  	Quick Selection Algorithm:
  	QS(arr, start, end, k)
		pivot = arr[(start+end)/2];
		pointer=0;
	// Partition, find left wall
		int leftWall=start;
		int pointer=start;
		while(pointer < end)
			if(arr[pointer] > pivot)
				swap(pointer, leftWall)
				leftWall++
	// find right wall
		pointer=leftWall
		int rightWall=leftWall
		while(pointer < end)
			if(arr[pointer] == pivot)
				swap(pointer, rightWall)
				rightWall++
	//Comparison and Select
		if(k <= (leftWall-start))
			QS(arr, start, leftWall,k)
		else if( k <= (rightWall - start))
			return pivot;
		else
			QS(arr, rightWall, end, k-(rightWall- start))

 */
class Solution {
  static public Random rand = new Random();
  public int findKthLargest(int[] nums, int k) {
    ArrayList<Integer> array = new ArrayList<>();
		for(int i: nums) array.add(i);
		return qs(k, array);

  }


  private static int qs(int k, ArrayList<Integer> list){

    ArrayList<Integer> left = new ArrayList<>();
  	ArrayList<Integer> mid = new ArrayList<>();
  	ArrayList<Integer> right = new ArrayList<>();
  	Integer pivot = list.get(rand.nextInt(list.size()));
  	for(Integer t: list)
  	{
  		if(t.intValue() == pivot.intValue())
  		{
  			mid.add(t);
  		}
  		else if(t.intValue() > pivot.intValue())
  		{
  			left.add(t);
  		}
  		else
  		{
  			right.add(t);
  		}
  	}
  	if(k<= left.size())
  	{
  		return qs(k,left);
  	}
  	else if(k>left.size() && k <= (left.size()+mid.size()))
  	{
  		return pivot.intValue();
  	}
  	else
  	{
  		return qs(k-(left.size()+mid.size()),right);
  	}
  }
// just use int[]
  private static int quickSelect(int[] nums,int start, int end ,int k)
  {
      int pivot = nums[(start+end)/2];

      //partition
      int left = start;
      int pointer = start;
      //first wall, the left part
      while(pointer < end)
      {
          if(nums[pointer] > pivot)
          {
              swap(nums,left,pointer);
              left++;
          }
          pointer++;
      }
      //second wall, the mid part
      pointer = left;
      int right = left;
      while(pointer < end)
      {
          if(nums[pointer] == pivot)
          {
              swap(nums,right,pointer);
              right++;
          }
          pointer++;
      }
      //selection
      if(k <= (left-start))
      {
          return quickSelect(nums,start,left,k);
      }
      else if(k > (left-start) && k <= (right-start))
      {
          return pivot;
      }
      else
      {
          return quickSelect(nums,right,end, k-(right-start));
      }
  }

  private static void swap(int[] nums, int i, int j)
  {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
}
