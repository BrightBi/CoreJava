package javaeight;

interface StringFunction {
	String func(String n);
}

class MyStringOps {
	public static String upper(String str) {
		return str.toUpperCase();
	}
}

public class MethodInterface {

	public static String stringOp(StringFunction sf, String s) {
		return sf.func(s);
	}

	public static void main(String[] args) {
		String inStr = "Java";
		// MyStringOps::upper 相当于实现了接口方法 func()，并在接口方法func()中作了MyStringOps.upper() 操作
		String outStr = stringOp(MyStringOps::upper, inStr);
		System.out.println(outStr);
	}
}
