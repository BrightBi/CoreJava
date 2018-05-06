package chapter4;

/*
 * 从 main 开始，用到哪个类就将哪个类加载到内存，有继承关系的先加载父类。
 * 加载到内存时候，先按静态变量和静态块的定义顺序依次加载静态变量和静态块
 * 创建实例对象时候，先实例化父类，再实例化自己。
 * 先按实例变量和实例块定义顺序依次初始化实例变量和实例块
 * 
 * 按静态变量和静态块只在加载到内存时候执行一次
 * 实例变量和实例块在每次实例化对象时候都会被调用
 */
public class ClassLoadSequence {
	static Person me = new Person("me");
	static { System.out.println("ClassLoadSequence static1"); }
	static Person us = new Person("us");
	{ System.out.println("ClassLoadSequence instance1"); }
	Person she = new Person("she");
	static { System.out.println("ClassLoadSequence static2"); }
	{ System.out.println("ClassLoadSequence instance2"); }
	public ClassLoadSequence() {
		System.out.println("ClassLoadSequence constructor");
	}
	public static void main(String[] args) {
		new SubClassLoadSequence();
	}
}
class Person {
	static { System.out.println("Person static"); }
	{ System.out.println("Person instance"); }
	public Person(String str) {
		System.out.println("Person " + str);
	}
}
class SubClassLoadSequence extends ClassLoadSequence {
	Person we = new Person("we");
	static { System.out.println("SubClassLoadSequence static"); }
	public SubClassLoadSequence() {
		System.out.println("SubClassLoadSequence constructor");
	}
}