package Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

public class HashMapDemo {
	public static void main(String[] args)
	{
		HashMap<Integer, String> hashmap = new HashMap<>();
		Random rand = new Random();
		int size = 20;		//size of hash map
		
		//generate random key and random value
		for(int i = 0 ; i < size ; i++)
		{
			Integer key = rand.nextInt(300);
			StringBuffer sb = new StringBuffer();
			int bufferLen = 1+rand.nextInt(10);	
			for(int j = 0 ; j < bufferLen; j++)
			{
				int value = 65;
				int increment = rand.nextInt(58);	//65-90, 97-122
				for(;value+increment > 90 && value+increment < 97;)
					increment = rand.nextInt(58);
				sb.append(""+(char)(value+increment));
			}
			// for map, add is put(key, value)
			hashmap.put(key, sb.toString());
		}
		
		// traverse hash map
		// based on keySet()
		System.out.println(hashmap);
		Set<Integer> keyset = hashmap.keySet();
		for(Iterator<Integer> itr = keyset.iterator();itr.hasNext(); )
		{
			Integer temp = itr.next();
			System.out.print(temp+"="+hashmap.get(temp)+" ");
		}
		System.out.println();
		
		// based on entrySet()
		Set<Entry<Integer, String>> entrySet = hashmap.entrySet();
		for(Map.Entry<Integer, String> temp : entrySet)
		{
			System.out.print(temp.getKey()+"="+temp.getValue()+" ");
		}
	}
}
