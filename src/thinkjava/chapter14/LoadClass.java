package thinkjava.chapter14;
/**
 * 所有类都是在对其第一次引用时候加载到 JVM 中的。
 * 所谓的第一次引用是指引用该类的静态成员（方法／成员变量）或者使用 new 创建对象。
 */
public class LoadClass {

	public static void main(String[] args) throws ClassNotFoundException {
		String name = First.name;
		Second.info();
		new Third();
		Class.forName("thinkjava.chapter14.Four");
	}
}
class First {
	public static String name = "First";
	static {System.out.println("First");}
}
class Second {
	static {System.out.println("Second");}
	public static void info () {System.out.println("Second info");}
}
class Third {
	static {System.out.println("Third");}
}
class Four {
	static {System.out.println("Four");}
}