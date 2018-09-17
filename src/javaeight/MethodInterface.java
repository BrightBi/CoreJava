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
		// 如果使用方法引用或者 lambda 表达式来创建一个函数式接口实例，那这个 被引用的方法或者 lambda 表达式的入参和返回
		// 必须符合这个函数式接口中唯一的抽象方法的定义
		String outStr = stringOp(MyStringOps::upper, inStr);
		System.out.println(outStr);
	}
}
