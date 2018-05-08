package corejava.chapter5;

// 类中即使不含抽象方法也可以声明为抽象类
public abstract class Animal {

	public String getName() {
		return "";
	}
}

class Cat extends Animal {

	@Override
	public String getName() {
		return "Cat";
	}
}
