package corejava.chapter4;
/*
 * 从 main 开始，用到哪个类就将哪个类加载到内存，有继承关系的先加载父类。
 * 加载到内存时候，先按静态变量和静态块的定义顺序依次加载静态变量或静态块
 * 创建实例对象时候，先实例化父类，再实例化自己。
 * 先按实例变量和实例块定义顺序依次初始化实例变量或实例块
 * 最后调用构造函数
 * 
 * 另一个思路：
 * 父类静态块/父类静态变量(谁在前先执行谁)--子类静态块/子类静态变量(谁在前先执行谁)
 * --父类构造块和实例变量/构造方法(先构造块和实例变量)--子类构造块和实例变量/构造方法(先构造块和实例变量)
 * 
 * 静态变量和静态块只在加载到内存时候执行一次
 * 实例变量和实例块在每次实例化对象时候都会被调用
 */
public class ClassLoadSequence {
	public static void main(String[] args) {
		System.out.println("Strat load ... ");
		new ChildClass();
		System.out.println("End load ... ");
	}
}
class Person {
	static { System.out.println("Person static"); }
	{ System.out.println("Person instance"); }
	public Person(String str) {
		System.out.println("Person " + str);
	}
}
class SuperClass {
	static Person me = new Person("me");
	static { System.out.println("SuperClass static1"); }
	static Person us = new Person("us");
	{ System.out.println("SuperClass instance1"); }
	Person she = new Person("she");
	static { System.out.println("SuperClass static2"); }
	{ System.out.println("SuperClass instance2"); }
	public SuperClass() {
		System.out.println("SuperClass constructor");
	}
	Person later = new Person("later");
}
class ChildClass extends SuperClass {
	Person we = new Person("we");
	static { System.out.println("ChildClass static"); }
	public ChildClass() {
		System.out.println("ChildClass constructor");
	}
}