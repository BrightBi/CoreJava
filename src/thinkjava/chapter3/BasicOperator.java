package thinkjava.chapter3;

import java.util.Random;

public class BasicOperator {

	public static void main(String[] args) {
		Random random1 = new Random(7); 
		Random random2 = new Random(7); 
		System.out.println(random1.nextInt(99) + "<=>" + random2.nextInt(99)); // 79<=>79
		
		byte t = 7;
		// short s = +t; // 加号只是为了与减号对应，唯一作用仅是将较小类型操作数提升为 int
		System.out.println(t + ":" + (++t)); // 先执行 ++ 运算，再生成值 7:8
		System.out.println(t + ":" + (t++)); // 先生成值，再执行 ++ 运算 8:8
		System.out.println(t); // 9
		
		int i = 0;
		// 与(&)、或(|)、异或(^)、取反(~)
		i &= t;
		i |= t;
		i ^= t;
		i = ~t; // i ~= t; 由于 ~ 是一元操作符，不能与 = 联用
		System.out.println(i);
		
		boolean b = false;
		boolean c = false; // boolean 类型能做的操作有限 &, |, ^, !, &&, ||, ==, !=
		b = b & b;
		b = b | b;
		b = b ^ b;
		// b = ~b; // 位运算的 ~ 不能作用于布尔类型
		b = !b;
		b = b && c;
		b = b || c;
		b = b == c;
		b = b != c;

		// 移位操作符只能用来处理整数类型，char byte short 在做位移操作会先转换成 int 再做位移操作，结果也是 int 类型

		// Java 允许把任意基本类型转换成其他基本类型，布尔类型除外。布尔类型禁止转换成其他基本类型
		// double 和 float 不能做 位移操作(>>, >>>, <<) 位操作(&, |, ^, ～) 布尔操作(!, &&, ||)
	}
}
