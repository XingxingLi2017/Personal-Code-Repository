import java.util.regex.*;
public class RegularExpression {

	public static void main(String[] args) {
        // matches
        String message = "13508252621";
        String regex = "1[358]\\d{9}";
        System.out.println(message.matches(regex));
        System.out.println("---------------------");

        // split
        // specify the separator use regular expression
        String s1 = "zhangsan....xiaoqiang.....zhaoliu";
        regex = "\\.+";
        String[] names = s1.split(regex);
        for(String name : names)
        {
            System.out.println(name);
        }
        System.out.println("---------------------");

        // usage of ()\\group number: mark group
        // the found group will stored in $ and can be accessed by $group number
        String s2 = "zhangsanttttxiaoqiangmmmmmmmmzhaoliu";
        regex = "(.)\\1+";
        names = s2.split(regex);
        for(String name : names) {
            System.out.println(name);
        }
        s2 = s2.replaceAll(regex, "$1");
        System.out.println(s2);
        System.out.println("---------------------");

        // replace
        String s3 = "15800001111";
        System.out.println(s3);
        regex = "(\\d{3})(\\d{4})(\\d{4})";
        s3 = s3.replaceAll(regex,"$1****$3");
        System.out.println(s3);
        System.out.println("---------------------");

        // get
        String s4 = "da jia hao, ming tian bu fang jia!";
        System.out.println(s4);
        regex = "\\b[a-z]{3}\\b";
        // 1. use Pattern to encapulate regular expression
        Pattern p = Pattern.compile(regex);
        // 2. get the pattern's matcher
        Matcher m = p.matcher(s4);
        
        // 3. perform find/look at/matches to match the regex
        while(m.find())
        {
        	// star() and end() can get the previous match boundary
            System.out.println(m.start()+": "+m.group()+": "+m.end());
        }   
    }
}
