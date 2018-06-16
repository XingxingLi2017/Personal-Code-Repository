package RegularExpression;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * Web Crawler: get data from internet which follows some rules
 * 
 * get email address
 * */
public class Crawler {

	public static void main(String[] args) {

	}
	
	public static List<String> getMails() throws IOException{
		//1. read source files
		URL url = new URL("https://en.wikipedia.org/wiki/Web_crawler");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		//2. match files with rules
		List<String> list = new ArrayList<>();
		String mail_regex = "\\w+@\\w+(\\.\\w+)+";
		Pattern p = Pattern.compile(mail_regex);
		String line = null;
		while((line = br.readLine())!= null)
		{
			Matcher m = p.matcher(line);
			//3. store data into collection
			while(m.find())
			{
				list.add(m.group());
			}
		}
		return list;
	}

}
