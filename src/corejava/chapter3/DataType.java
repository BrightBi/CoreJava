package corejava.chapter3;

public class DataType {

	public static void main(String[] args) {
		// 使用 F/f 是等同的
		float f1 = 3.7f;
		float f2 = 3.7F;
		long l1 = 3l;
		long l2 = 7L;
		
		System.out.println(f1 + f2 + l1 + l2);
		System.out.println(Double.POSITIVE_INFINITY + " | " + Double.NEGATIVE_INFINITY + " | " + Double.NaN);
		System.out.println(Double.NaN == Double.NaN); // 永远不要使用 '==' 判断两个NaN
		System.out.println(Double.isNaN(f1)); // 使用 Double.isNaN() 来判断 NaN
		
		System.out.println(Character.isJavaIdentifierStart('7'));
		System.out.println(Character.isJavaIdentifierPart('7'));
		
		System.out.println((7 % 3) + " | " + (7.2 % 3) + " | " + (7 % 2.2));
		
		short s = 1;
		// 位移操作是会摸除 32 的(long 会摸除64)
		System.out.println((1 << 34) + " | " + (1 << 2) + " | " + (s << 34) + " | " + (s << 2));
		
		// 使用 Math 类中的方法会快速运行出结果，
		// 使用 StrictMath 类中方法可以在不同平台上得到相同结果
		
		String s1 = "abc";
		String s2 = "abc";
		String s3 = new String("abc");
		System.out.println((s1 == s2) + " | " + (s1 == s3) + " | " + (s1.equals(s2)) + " | " + (s1.equals(s3)));

	}
	
	// 使用 strictfp 修饰一个方法，方法内部将严格按照浮点计算
	// 杜绝80位寄存器得到60位结果这种不同平台精度不同问题，可能产生溢出
	public static strictfp void useStrictfp(double f, double s) {
		double d = f + s;
		System.out.println(d);
	}

}
