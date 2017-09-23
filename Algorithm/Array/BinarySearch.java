import java.util.*;
class BinarySearch
{
	/*
		Arrays.binarySearch(int[] array, int target);
		if element doesn't exist
			return value is -low-1. // if low  = 0,can return -1
									//ensure negetive when doesn't exist
	*/
	public static void main(String[] args)
	{
		final int size = 20;
		int[] array = new int[size];
		for(int i = 0 ; i < size ; i++)
		{
			array[i] = (int)(1+Math.random()*100);
		}
		Arrays.sort(array);
		for(int i = 0 ; i < size ; i++)
		{
			System.out.print("["+array[i]+"] ");
		}
		System.out.println("");


		
		// Scanner in = new Scanner(System.in);
		// int target = in.nextInt();
		int target = (int)(1+Math.random()*100);
		System.out.println("input target = "+ target);
		System.out.println("index of target = "+getIndex_BS(array,target));

		ArrayList<Integer> arrays = new ArrayList<>();
		for (int i = 0 ; i < size; i++ ) 
		{
			int element = (int)(100+Math.random()*100);
			insert_BS(arrays, new Integer(element));
		}

		System.out.println("the random binary search insert array is:");
		for (int i = 0 ; i < size; i++ ) 
		{
			System.out.print("["+arrays.get(i).toString()+"] ");
		}

	}

	//basic binary search 
	public static int getIndex_BS(int[] a, int b)
	{	
		int low = 0 ; 
		int high = a.length-1;

		while(low <= high)
		{
			int mid = (high+low)/2;
			if(a[mid] == b)
				return mid;
			else if(a[mid] > b)
			{
				high = mid-1;	//notice the relationship between high,low and mid
			}
			else
			{
				low = mid+1;
			}
		}
		return -1;
	}

	//use ArrayList to insert a new element in array but don't change the order
	//Binary Search insert
	public static void insert_BS(ArrayList<Integer> array, Integer target)
	{
		int len = array.size();
		int low = 0;
		int high = len-1;
		while(low <= high)
		{
			int mid = (low+high)/2;
			if(array.get(mid).intValue() == target.intValue() )
			{
				array.add(mid,target);
				return;
			}
			else if(array.get(mid).intValue() > target.intValue())
			{
				high = mid-1;
			}
			else
			{
				low = mid+1;
			}
		}
		//use low index to ensure there is no index out of bounds exception
		//add(int index, int target), index can't greater than size
		//
		array.add(low,target);
	}
}