package introspectorDemo;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.junit.Test;

public class IntrospectorDemo {
	@Test
	// get all the property descriptors
	public void test1() throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : propertyDescriptors) {
			System.out.println(pd.getName());
		}
	}
	@Test
	// operate specified method
	public void test2() throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Person p = new Person();
		PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);
		System.out.println(pd.getReadMethod().invoke(p, null));
		pd.getWriteMethod().invoke(p, 23);
		System.out.println(pd.getReadMethod().invoke(p, null));
	}
	@Test
	// get property type
	public void test3() throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Person p = new Person();
		PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);
		System.out.println(pd.getPropertyType());
	}

	@Test
	public void test4() throws IllegalAccessException, InvocationTargetException {
		Person p = new Person();
		BeanUtils.setProperty(p, "name", "XING");
		System.out.println(p.getName());
	}
	// BeanUtils framework
	@Test
	public void test5() throws IllegalAccessException, InvocationTargetException {
		String username = "xing";
		String password = "123";
		String age = "34";
		Person p = new Person();
		BeanUtils.setProperty(p, "name", username);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age);
		System.out.println(p.getName()+", "+p.getAge()+ ", " + p.getPassword());
	}
	@Test
	public void test6() throws IllegalAccessException, InvocationTargetException {
		String username = "xing";
		String password = "123";
		String age = "34";
		String birthday = "1994-03-28";

		ConvertUtils.register(new Converter() {

			@Override
			public <T> T convert(Class<T> type, Object value) {
				if (value == null) return null;
				if (! (value instanceof String)) {
					throw new ConversionException("value can't be instantialized as String");
				}
				String str = (String) value;
				str = str.trim();
				if ("".equals(str)) {
					return null;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return (T) df.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException("value string can't be converted to Date",e);
				}
			}

		}, Date.class);

		Person p = new Person();
		BeanUtils.setProperty(p, "name", username);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age);
		BeanUtils.setProperty(p, "birthday", birthday);
		System.out.println(p.getName()+", "+p.getAge()+ ", " + p.getPassword() + ", "+ p.getBirthday().toLocaleString());
	}

}
