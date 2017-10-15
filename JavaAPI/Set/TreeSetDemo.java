package Set;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo {
	public static void main(String[] args)
	{
		// TreeSet and TreeMap require the elements can be compared
		// 1. elements have implemented Comparable interface
		// 2. initialize the collection with comparator
		// 3. comparator's priority is greater than comparable's
		@SuppressWarnings("unchecked")
		TreeSet<Person> treeSet = new TreeSet<>(new Comparator(){
			public int compare(Object obj1, Object obj2)
			{
				if(obj1 instanceof Person && obj2 instanceof Person)
				{
					Person p1 = (Person)obj1;
					Person p2 = (Person)obj2;
					int flag = p1.a.compareTo(p2.a);
					return flag == 0? p1.b-p2.b :flag;
				}
				else
				{
					throw new ClassCastException();
				}
			}
		});
		
		treeSet.add(new Person("xiaoming", 23));
		treeSet.add(new Person("xiaohong", 22));
		treeSet.add(new Person("anPei",78));
		for(Person temp: treeSet)
		{
			System.out.println(temp);
		}
	}
}

class Person implements Comparable
{
	public String a;
	public int b;
	public Person(String a, int b)
	{
		this.a = a;
		this.b = b;
	}
	
	
	public boolean equals(Object a)
	{
		if(a instanceof Person)
		{
			Person b = (Person)a;
			if(this.a.equals(b.a) && this.b == b.b)
				return true;
			else
				return false;
			
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public int hashCode()
	{
		return a.hashCode();
	}
	
	public String toString()
	{
		return "["+a+", "+b+"]";
	}


	@Override
	public int compareTo(Object o) {
		try
		{
			Person p = (Person)o;
			int flag = a.compareTo(p.a);
			return flag == 0? -(b-p.b) :-flag;
		}
		catch(ClassCastException e)
		{
			throw e;
		}
	}
	
	
}