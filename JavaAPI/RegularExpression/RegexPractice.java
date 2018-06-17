package RegularExpression;
import java.util.TreeSet;
import java.util.regex.*;
public class RegexPractice {

	public static void main(String[] args) {
		/*
		 * delete duplicate words
		 * */
		String str = "I.I...I.I...I...want.......want..want....want..to..to.....to...learn..learn.learn...it...it......it..it.";
		// 1. replace '.' with ' ' 
		String regex = "\\.+";
		str = str.replaceAll(regex, " ");
		System.out.println(str);
		
		// 2. replace reduplicated words
		regex = "(\\w+\\s)\\1+";		
		// use the () to match the reduplicated character groups and select member we want
		str = str.replaceAll(regex, "$1");
		System.out.println(str);
		
		System.out.println("------------------------------");
		
		/*
		 * sort the ip address
		 * */
		
		String ips = "192.168.10.34 127.0.0.1   3.7.2.1";
		// 1. ensure all the numbers have 3 digits
		ips = ips.replaceAll("(\\d+)","00$1");
		// 2. delete redundant digits
		ips = ips.replaceAll("0*(\\d{3})","$1");
		// 3. slide the IPs 
		String[] ipAddrs = ips.split(" +");
		for(String temp:ipAddrs){
			System.out.println(temp);
		}
		// 4. sort objects -> use TreeSet in Collection
		TreeSet<String> set = new TreeSet<>();
		for(String temp : ipAddrs){
			set.add(temp);
		}
		// 5. deal with redundant 0s
		for(String temp: set)
		{
			temp = temp.replaceAll("0*(\\d+)","$1");
			System.out.print(temp+" ");
		}
	}

}