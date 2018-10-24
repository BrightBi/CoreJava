package corejava.chapter5;

public class BoxAndMutableParam {

	public static void main(String[] args) {
		int i = Integer.parseInt("7"); // 字符串转换成数字
		Integer integer = new Integer(2);
		i = integer.intValue(); // 获取对象 int 值
		integer = Integer.valueOf("9"); // 字符串转换成 Integer
		// Integer 是 final 的，不能通过这种方式实现自增
		addNumber(integer);
		System.out.println(i + "|" + integer);

		System.out.println(sum("S", 1, 2, 3));
		System.out.println(sum("C", new int[] { 1, 2, 3 }));
		testVarargs(1, 2, 3);

		// 实参个数固定的版本优先
		testPriority(1);// 打印出A
		testPriority(1, 2);// 打印出B
		testPriority(1, 2, 3);// 打印出C
		testPriority(1, new int[] { 1, 2, 3 });// 打印出C

		// testOverloadingBoxType(1, 2, 3);//编译出错
		// testOverloading(1, 2);//编译出错
		// testOverloading(new Integer(1), new Integer(2));//编译出错

	}

	public static void addNumber(Integer i) {
		i = i + 1;
		System.out.println("i = " + i);
	}

	// 可变参数方法，可变部分最终会转换成一个对象数组。等价于 sum (String title, int [] paremeters)
	// 注意，只有最后一个形参才能被定义成可变。
	// 另外 sum (String title, int ... paremeters) 与 sum (String title, int []
	// paremeters) 是冲突的，不能同时定义
	// 如果可变部分是基础数据类型，会自动装箱，转换成对象数组
	public static String sum(String title, int... paremeters) {
		int sum = 0;
		for (int s : paremeters) {
			sum += s;
		}
		;
		return title + ":" + sum;
	}

	@SafeVarargs
	public static <T> void testVarargs(T... t) {
		System.out.println("t.length = " + t.length);
	}

	public static void testPriority(int i) {
		System.out.println("A");
	}

	public static void testPriority(int i, int j) {
		System.out.println("B");
	}

	public static void testPriority(int i, int... more) {
		System.out.println("C");
	}

	public static void testOverloadingBoxType(Object... args) {
	}

	public static void testOverloadingBoxType(Object o, Object... args) {
	}

	public static void testOverloading(int... args) {
	}

	public static void testOverloading(Integer... args) {
	}
}
