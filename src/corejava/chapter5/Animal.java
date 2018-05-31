package corejava.chapter5;

// 类中即使不含抽象方法也可以声明为抽象类
public abstract class Animal {

	public abstract String getCategory();
	
	// 因为抽象方法需要被子类继承实现，所以抽象类中的抽象方法不能定义成 static 或者 private
	// public static abstract String getAge();
	
	public String getName() {
		return "";
	}
}

class Cat extends Animal {
	
	@Override
	public String getCategory() {
		return Cat.class.getName();
	}

	@Override
	public String getName() {
		return "Cat";
	}
}
