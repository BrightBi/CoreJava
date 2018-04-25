package other;

/*
 * 从 main 开始，用到哪个类就将哪个类加载到内存，有继承关系的先加载父类。
 * 加载到内存时候，先加载静态成员变量，然后加载静态块
 * 创建实例对象时候，先处理实例成员变量，然后处理实例块
 */
public class ClassLoadSequence {
	static Person me = new Person("me");
	Person she = new Person("she");
	static {
		System.out.println("test static");
	}
	{
		System.out.println("test instance");
	}
	public ClassLoadSequence() {
		System.out.println("test constructor");
	}
	public static void main(String[] args) {
		new SubClassLoadSequence();
	}
}
class Person {
	static {
		System.out.println("person static");
	}
	public Person(String str) {
		System.out.println("person " + str);
	}
}
class SubClassLoadSequence extends ClassLoadSequence {
	Person we = new Person("we");
	static {
		System.out.println("SubClassLoadSequence static");
	}
	public SubClassLoadSequence() {
		System.out.println("SubClassLoadSequence constructor");
	}
}