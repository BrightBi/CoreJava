package others;
/*
 * Java 的参数传递，传递出去的是实参的一份拷贝给形参
 * 对形参的改变不会影响实参
 * 如果实参是对象引用，那么对形参引用的对象的改变，会影响实参引用的对象
 * 注意包装器类型和 String 常量字符串，他们比较特殊，处理方式类似基本类型数据
 */
public class ParameterPassing {
	public static void main(String[] args) {
		
		boolean testBasic = true;
		change(testBasic);
		System.out.println(testBasic);
		
		Integer testInteger = 9;
		change(testInteger);
		System.out.println(testInteger);
		
		// String类似基本类型，值传递，不会改变实际参数的值
		String testString = "Hello";
		change(testString);
		System.out.println(testString);
		
		// String类似基本类型，值传递，不会改变实际参数的值
		String testString1 = new String("Good");
		change(testString1);
		System.out.println(testString1);

		// StringBuffer和StringBuilder等是引用传递
		StringBuffer testStringBuffer = new StringBuffer("Hello");
		change(testStringBuffer);
		System.out.println(testStringBuffer.toString());
	}
	
	public static void change(boolean b) {
		b = !b;
		System.out.println(b);
	}
	
	public static void change(Integer i) {
		i = i + 1;
		System.out.println(i);
	}

	public static void change(String s) {
		s = s.concat(" world");
		System.out.println(s);
	}

	public static void change(StringBuffer sb) {
		sb.append(" world");
		System.out.println(sb);
	}
}
