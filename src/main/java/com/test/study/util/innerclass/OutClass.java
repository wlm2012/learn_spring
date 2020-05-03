package com.test.study.util.innerclass;


/**
 * @author lenovo2
 */
public class OutClass {

	public static void main(String[] args) {

	}


	// Only inner classes can be private.
	// Regular classes always have either package or public access
	private class InnerClass {
		public InnerClass() {
			System.out.println("init InnerClass construct");
		}
	}

	public class Inner {
		public Inner() {
			System.out.println("init inner construct");
			//private class cannot construct outside
			InnerClass innerClass=new InnerClass();
		}

		//An inner class cannot have static methods
/*		public static void in() {

		}*/


	}


}
