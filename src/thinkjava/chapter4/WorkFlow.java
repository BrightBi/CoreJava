package thinkjava.chapter4;

import java.util.Random;

public class WorkFlow {

	public static void main(String[] args) {

		// switchCase("s");
		testRandom();
		// Java 中使用带标签 break／continue 唯一理由就是处理循环嵌套
	}
	
	public static void testRandom() {
		double d = Math.random(); // Math.random() 生成一个零到壹之间的 double 值，含零不含壹
		System.out.println(d);
		
		/*
		 *  随机数生成器对于特定种子，总是产生相同的随机数序列
		 *  使用 47 作为种子来创建一个随机数生成器
		 */
		Random random = new Random(47); // 
		for (int i = 0; i < 10; i++) {
			System.out.println(random.nextInt(5)); // 随机生成一个 0 到 5 之间的一个整数，含 0 不含 5.
		}
		
	}

	/*
	 * 每一个方法都要有return。如果方法的返回值是 void，那么方法末尾可以省略 return，会默认加一个 return，类似默认构造函数
	 * 如果方法返回值不是 void 那么就需要保证每个分枝最终都要 return 对应的返回值
	 */
	public static void testReturn() {
		for (int i = 1; i < 10; i = i + 3) {
			if (i == 7) {
				return; // 此处 return 直接退出方法，for 循环也就此终止
			}
			System.out.println("i=" + i);
		}
	}

	public static void testFor() {

		for (int i = 1, j = i + 3; i < j; i = i + 3, j++) {
			System.out.println("i=" + i + " | j=" + j);
		}

		int[] arr = new int[] { 1, 3, 5, 7, 9 };
		for (int value : arr) {
			System.out.println(value);
		}
	}

	public static void switchCase(String c) {
		// case 标签必须是整数或枚举常量（现在String也可以）
		// case 语句中可以使用 break 来中断顺次执行
		switch (c) {
		case "s":
			System.out.println("s");
		default:
			System.out.println("default");
		case "t":
			System.out.println("t");
		}
		/*
		 * switchCase("uuu");
		 * default
		 * t
		 * 
		 * switchCase("t");
		 * t
		 * 
		 * switchCase("s");
		 * s
		 * default
		 * t
		 */
	}
}
