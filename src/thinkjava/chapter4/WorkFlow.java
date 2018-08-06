package thinkjava.chapter4;

public class WorkFlow {

	public static void main(String[] args) {
		double r = Math.random(); // Math.random() 生成一个零到壹之间的 double 值，含零不含壹
		System.out.println(r);

		for (int i = 1, j = i + 3; i < j; i = i + 3, j++) {
			System.out.println("i=" + i + " | j=" + j);
		}

		int [] arr = new int [] {1, 3, 5, 7, 9};
		for (int value : arr) {
			System.out.println(value);
		}

		// Java 中使用带标签 break／continue 唯一理由就是处理循环嵌套
	}
}
