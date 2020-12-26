package com.test.study.util.reflect;

import com.test.study.util.entity.Person;
import com.test.study.util.enumtest.EnumTest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * ReflectTest
 *
 * @author wlm
 */


public class ReflectTest {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, NoSuchFieldException {
		Class c1 = Class.forName("com.test.study.util.entity.Person");

		int[] a = {1, 2, 3};
		int length = 4;
//		System.out.println(Arrays.toString((int[]) copyArray(a,1)));
//		System.out.println(Arrays.toString((int[]) copyArray(a,length)));
		setAndGetField();
	}

	public static void test() {
		Class<EnumTest> class1 = EnumTest.class;
		String name = class1.getName();
		System.out.println(name);
	}

	public static void testClass() {
		Person person = new Person();
		System.out.println(person.getClass());
		System.out.println(Person.class);
		System.out.println(Arrays.toString(Person.class.getConstructors()));
	}

	public static void testConstructor() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class c1 = Class.forName("com.test.study.util.entity.Person");
//      Person person=(Person) c1.getConstructor().newInstance();
		Person person = (Person) c1.getConstructor(String.class, int.class).newInstance("wlm", 11);
		String name = person.getName();
		System.out.println(name);
		System.out.println(person.getOld());
		person.printName();

	}

	
	public static void testMethod() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class c1 = Class.forName("com.test.study.util.entity.Person");
		Object person = c1.getConstructor().newInstance();

		Method printYear = c1.getMethod("printYear", String.class);
		printYear.invoke(person, "2020");

		//For a static method, the first parameter is ignored, you can set it to null
		Method printLike = c1.getMethod("printLike");
		printLike.invoke(null);
	}

	public static void ResourceTest() throws ClassNotFoundException, IOException {
		Class c1 = Class.forName("com.test.study.util.entity.Person");
		//create 1.txt under Person.class fold for test
		System.out.println(c1.getResource("1.txt"));
		InputStream inputStream = c1.getResourceAsStream("1.txt");
		StringBuffer stringBuffer = new StringBuffer();
		byte[] buf = new byte[1024 * 10];
		int temp;
		while ((temp = inputStream.read(buf)) > 0) {
			stringBuffer.append(new String(buf, 0, temp));
		}
		System.out.println(stringBuffer);

	}

	public static void printField(Class c1) {
		Field[] fields = c1.getDeclaredFields();
		for (Field field : fields) {
			String modifier = Modifier.toString(c1.getModifiers());
			if (modifier.length() > 0) {
				System.out.print(modifier + "   ");
			}
			System.out.println(field.getType().getName() + "   " + field.getName());
		}
	}

	public static void setAndGetField() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		Class c1 = Class.forName("com.test.study.util.entity.Person");
		var harry = (Person) c1.getConstructor(String.class, int.class, String.class).newInstance("Harry", 14, "1");

		//change the value of "sex", 1->2
		Field sex = c1.getDeclaredField("sex");
		sex.set(harry, "2");

		//change the value of "name" , Harry->potter
		Field name = c1.getDeclaredField("name");
		//private field ,need setAccessible(true)
		name.setAccessible(true);
		name.set(harry, "potter");

		//static field  set xxx.class or object
		Field like =c1.getDeclaredField("like");
		like.set(Person.class,"kde");
		like.set(harry,"unix");

		/**
		 * output:
		 * potter
		 * 14
		 * 2
		 */
		Field[] fields = c1.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object s = field.get(harry);
			System.out.println(s);
		}

	}


	public static Object copyArray(Object a, int newLength) {
		Class c1 = a.getClass();
		if (!c1.isArray()) {
			return null;
		}
		Class type = c1.getComponentType();
		int length = Array.getLength(a);
		Object newArray = Array.newInstance(type, newLength);
		System.arraycopy(a, 0, newArray, 0, Math.min(newLength, length));
		return newArray;
	}

}