public class RegularExpression {

	public static void main(String[] args) {
		// matches()
		String message = "13508252621";
		String regex = "1[358]\\d{9}";
		System.out.println(message.matches(regex));
		System.out.println("---------------------");
		// slides
		// specify the separator use regular expression
		String s1 = "zhangsan....xiaoqiang.....zhaoliu";
		regex = "\\.+";
		String[] names = s1.split(regex);
		for(String name : names)
		{
			System.out.println(name);
		}
	}

}