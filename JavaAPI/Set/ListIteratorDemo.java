package Set;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class ListIteratorDemo {
	public static void main(String[] args)
	{
		List<Integer> ls = new LinkedList<>();
		Random random = new Random();
		int length = 20;
		for(int i = 0 ; i < length ; i++)
		{
			ls.add(random.nextInt(100));
		}
		
		//we need to modify the underlying collection so we use ListIterator
		ListIterator<Integer> li = ls.listIterator();
		while(li.hasNext())
		{
			Integer temp = li.next();
			System.out.print("["+temp+"] ");
			if(temp.intValue() < 50)
			{
				li.set(-1);
			}
		}
		System.out.println("");
		while(li.hasPrevious())
		{
			Integer temp = li.previous();
			System.out.print("["+temp+"] ");
		}
	}
}
