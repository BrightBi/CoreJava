package corejava.chapter5;

public class BoxAndMutableParam {

	public static void main(String[] args) {
		int i = Integer.parseInt("7"); // 字符串转换成数字
		Integer integer = new Integer(2);
		i = integer.intValue(); // 获取对象 int 值
		integer = Integer.valueOf("9"); // 字符串转换成 Integer
		// Integer 是 final 的，不能通过这种方式实现自增
		addNumber (integer);
		System.out.println(i + "|" + integer);
		
		System.out.println(sum ("S", 1, 2, 3));
		System.out.println(sum ("C", new int[] {1, 2, 3}));

	}
	public static void addNumber (Integer i) {
		i = i + 1;
	}
	
	// 可变参数方法，可变部分最终会转换成一个对象数组。等价于 sum (String title, int [] paremeters)
	// 如果可变部分是基础数据类型，会自动装箱，转换成对象数组
	public static String sum (String title, int ... paremeters) {
		int sum = 0;
		for (int s : paremeters) {
			sum += s;
		};
		return title + ":" + sum;
	}
}
