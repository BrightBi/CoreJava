package thinkjava.chapter13;

import java.util.Arrays;

/*
 * 对于 String 类型数据的每一次修改，都会创建一个全新的 String
 */
public class StringBasic {

	/*
	 * 不应过度依赖编译器引入 SpringBuilder。
	 * 比如在循环中，很容易在每次循环都引入一个 SpringBuilder，效能反而很低。
	 * 再比如，SpringBuilder.append(a + ":" + b)，会再次生成 SpringBuilder 来处理 a + ":" + b
	 * 
	 * String 涉及值改变的的方法都返回一个新结果集的引用，而不会去改变原字符串。
	 */
	public static void main(String[] args) {

		String s = "String";
		System.out.println(s); // String
		String ss = upper(s);
		System.out.println(ss); // STRING
		System.out.println(s); // String
		
		// 理论上内存中会生产 字符串 "a" "b" "c" 以及中间量 "ab" 和最终量 "abc"，实际上编译器会引入 SpringBuilder 来处理
		System.out.println("a" + "b" + "c");
		
		System.out.println(new ToString());
		
		// 正则表达式中 \d 表示数字，而 Java 中 \ 需要用 \\ 转译表示，所以正则表达式中的 \d 在 Java 中需要用 \\d 表示，
		// 同理 正则表达式中 \\ 表示 \，放在 Java 中就需要用 \\\\ 表示。
		System.out.println("-1234".matches("-?\\d+"));
		
		System.out.println(Arrays.toString("what is your name? Bright!".split("\\W+"))); // [what, is, your, name, Bright]
		System.out.println(Arrays.toString("what is your name? Bright!".split(" "))); // [what, is, your, name?, Bright!]
		System.out.println("what is your name? Bright!".replaceAll("\\W+", "_")); // what_is_your_name_Bright_
		System.out.println("aabbaabb".replaceAll("ab", "a"));  // aabaab
	}
	
	// 实参会拷贝一份引用传给形参 s
	public static String upper(String s) {
		return s.toUpperCase();
	}
}

class ToString {

	@Override
	public String toString() {
		// return "To " + this + " string"; 如果使用 this 会造成死循环。
		// 原因是 "To " + this 时，发现是字符串链接上一个 this，会尝试调用 this 的 toString 方法将其转为字符串。
		return "To " + super.toString() + " string";
	}
	
}