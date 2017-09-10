class RandomNumber
{
	public static void main(String[] args)
	{
		int randomNumber = randomWay_1();
		System.out.println("using System.currentTimeMillis, number is "+randomNumber);
		randomNumber = randomWay_2(0,100);
		System.out.println("using Math.random(), number is "+randomNumber);
		randomNumber = randomWay_3(70);
		System.out.println("using Random Class, number is "+randomNumber);
	}

	//first way to get a random number
	//use the System.currentTimeMillis() function
	public static int randomWay_1()
	{
		long time = java.lang.System.currentTimeMillis();
	//	System.out.println("Before & time is "+Long.toBinaryString(time));
		int random =(int)(time&0x00000000ffffffff);
	//	System.out.println("after & time is "+Integer.toBinaryString(random));
		return random;
	}

	//using Math.random() function, return a random double which is included in [0,1)
	public static int randomWay_2(int min, int max)
	{
		if(min > max)
		{
			System.out.println("Idiots, don't input a min that is greater than max");
			return -1;
		}
		int range = max - min + 1; //since the Math.random range is [0, 1)
		int random = min +(int)(Math.random() * range); 
		return random;
	}

	//using Random class
	//different seeds will produce different results
	public static int randomWay_3(int seed)
	{
		// java.util.Random random_1 = new java.util.Random();
		// java.util.Random random_2 = new java.util.Random();
		// random_1.setSeed(500L);
		// random_2.setSeed(500L);
		// System.out.println("Same seed of random_1 and random_2");
		// for (int i = 0; i < 10 ; i++)
		// {
		// 	System.out.print(random_1.nextInt() + ", ");
		// 	System.out.println(random_2.nextInt());
		// }
		// System.out.println("different seed of random_1 and random_2");
		// random_2.setSeed(501L);
		// for (int i = 0; i < 10 ; i++)
		// {
		// 	System.out.print(random_1.nextInt() + ", ");
		// 	System.out.println(random_2.nextInt());
		// }
		// System.out.println("----------------end random_3----------------");

		java.util.Random random_3 = new java.util.Random();
		return random_3.nextInt();
	}
}