import java.util.*;
class Demo
{
	public static void main(String[] args)
	{
		System.out.println("----------------------");
		int[] arr={12,124,534,-234,23,-47,234,7842,-9,1,0,0,-45789,1,3,-4};
		int[] arr2 = new int[100];
		// generate the array with random numbers
		for(int i = 0 ; i < arr2.length ;i++)
		{
			arr2[i] = randomNumber(-100,300);
		}
		// int max = arr[0];
		// for(int i = 0 ; i < arr.length; i++)
		// {
		// 	if(arr[i] > max)
		// 	{
		// 		max = arr[i];
		// 	}
		// }
		// System.out.println(max);
		System.out.println("----------------------");
		// System.out.println("Integer Max="+Integer.MAX_VALUE);
		printArray(arr2);
		quickSort(arr2,0,arr2.length-1);
		printArray(arr2);

	}

	public static int randomNumber(int min, int max)
	{
		if(min > max)
		{
			int temp = min;
			min = max;
			max = temp;
		}
		return min+(int)(Math.random() * (max - min + 1));
	}

	//select sort algorithm
	//select the right element for the index
	public static void selectSort(int[] num)
	{
		for(int i = 0; i<num.length-1;i++)//the ith smallest element
		{
			for(int j = i+1 ; j<num.length ; j++)
			{
				if(num[i] > num[j])
					switchPosition(num, i , j);
			}
			// System.out.println("--------------------------");
			// printArray(num);
			// System.out.println("--------------------------");
		}
	}

	//bubble sort algorithm
	//find the current maximum in the rest array in every iteration
	public static void bubbleSort(int[] num)
	{
		for(int i= num.length-1; i>0; i--)//the ith greatest element
		{
			for(int j = 0 ; j < i;j++)//find the maximum
			{
				if(num[j] >num[j+1])
					switchPosition(num, j, j+1);
			}
			// System.out.println("--------------------------");
			// printArray(num);
			// System.out.println("--------------------------");
		}
	}

	//partition and recursion
	public static void quickSort(int num[], int start, int end)
	{
		if(start < end)
		{
			int wall = partition(num,start, end);
			quickSort(num, start , wall-1);
			quickSort(num, wall , end);
		}

	}

	//divide an array into two parts and return the pivot
	//the left part includes all elements which are less  than the pivot
	//the right part includes all elements which are greater or equal than the pivot
	public static int partition(int num[], int start , int end)
	{
		int pivot  = num[start];
		int wall = start+1;					//use the num[start] as the pivot
		for(int i = start+1 ; i <= end ; i++)//so traverse starts from start+1
		{
			if(num[i] < pivot)
			{
				switchPosition(num, i ,wall);
				wall++;
			}
		}
		//don't know the comparison between pivot and num[wall]
		//compare the pivot with the wall
		//always ensure pivot element will move to the end of the left part
		if(wall <= end && num[wall] < pivot)
			switchPosition(num, start , wall);
		else
			switchPosition(num, start,wall-1);
		// System.out.println("==============================");
		// System.out.println("pivot is:"+ pivot+"");
		// System.out.println("array after partition is:");
		// printArray(num, start, end);
		// System.out.println("==============================");

		return wall;
	}

	public static void switchPosition(int[] num, int index1, int index2)
	{
		int temp=num[index1];
		num[index1]=num[index2];
		num[index2]=temp;
	}

	public static void printArray(int[] num)
	{
		System.out.print("[");
		for(int i=0 ; i < num.length ; i++)
		{
			if(i != num.length-1)
				System.out.print(num[i]+", ");
			else
				System.out.print(num[i]+"]");
		}
		System.out.println("");
	}

	public static void printArray(int[] num, int start, int end)
	{
		System.out.print("[");
		for(int i=start ; i <= end ; i++)
		{
			if(i != end)
				System.out.print(num[i]+", ");
			else
				System.out.print(num[i]+"]");
		}
		System.out.println("");
	}
}
