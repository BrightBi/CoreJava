package thinkjava.chapter5;

public class AboutThis {

	public static void main(String[] args) {
		Apple apple = new Apple();
		apple.addWeight(3).addWeight(3);
		System.out.println(apple.getWeight() + " | " + apple.isStandard());
	}
}

class Apple {
	private int weight;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Apple addWeight (int add) {
		this.setWeight(this.getWeight() + add);
		return this; // 此处返回调用方法的的那个 Apple 对象
	}
	
	public boolean isStandard () {
		// 将调用此方法的对象传递给其他处理流程
		return AppleTool.validate(this);
	}
}

class AppleTool {
	// static 方法更像是一个没有 this 的方法(即，不能在静态方法中使用 this)
	public static boolean validate (Apple apple) {
		return apple.getWeight() > 10;
	}
}
