package javaeight;

interface NewInterface {

	String normal();

	// JDK 8 以后，允许在接口中定义静态方法, 接口中所有方法都是 public 的，所以可以省略
	// 接口中的静态方法不会被子接口或者实现类继承
	static String staticMethod (String description) {
		return "StaticMethod: " + description;
	}

	// JDK 8 以后，允许在接口中定义默认方法（方法签名前面需要关键字 default 标识）, 同上省略了 public，实现类中可以重写默认方法，也可以不重写
	// 不重写的话就使用接口中默认的实现方式
	default String defaultMethod(String info) {
		return "Default: " + info;
	}
}

interface NormalExtendInterface extends NewInterface {
	// 使用这种方式继承，会继承父接口的默认方法，实现类可以重写默认方法，也可以不重写
	String normalExtends();

}

interface AbstractExtendInterface extends NewInterface {
	// 使用这种方式继承，会将父接口的默认方法重置成抽象方法，实现类必须重写默认方法
	@Override
	String defaultMethod(String info);

}

interface OverrideExtendInterface extends NewInterface {
	// 使用这种方式继承，会将父接口的默认方法重写，实现类可以重写默认方法，也可以不重写
	// 不重写的话就使用当前接口中重写了的默认的实现方式
	@Override
	default String defaultMethod(String info) {
		return "Override Default: " + info;
	}
}

interface AnotherNewInterface {

	String anotherNormal();
	default String defaultMethod(String info) {
		return "Another Default: " + info;
	}
}

interface MultipleExtendInterface extends NewInterface, AnotherNewInterface {
	// 同时继承两个接口，且这两个接口中有相同的默认方法，那么子接口必须重写这个有冲突的默认方法
	@Override
	default String defaultMethod(String info) {
		return "Override Multiple Default: " + NewInterface.super.defaultMethod("use super") + info;
		// 如果需要用到某个父接口的默认方法可以这样调用父接口方法 NewInterface.super.defaultMethod("use super")
	}
}
/*
 * 1.当继承的父类和实现的接口中有相同签名的方法时，优先使用父类的方法。
 * 2.当接口的父接口中也有同样的默认方法时，就近原则调用子接口的方法。
 * 3.当实现的多个接口中有相同签名的方法时，必须在实现类中通过重写方法解决冲突问题，否者无法通过编译，在重写的方法中可以通过 接口名.super.方法名(); 的方式显示调用需要的方法。
 */
class SuperClass {
	public String defaultMethod(String info) {
		return "SuperClass Default: " + info;
	}
}

class ClassAndInterface extends SuperClass implements NewInterface {
	@Override
	public String normal() {
		return "Test";
	}
}
class MultilayerInterface implements OverrideExtendInterface {
	@Override
	public String normal() {
		return "Test";
	}
}

class ManyInterface implements NewInterface, AnotherNewInterface {

	@Override
	public String defaultMethod(String info) {
		return "ManyInterface Default: " + AnotherNewInterface.super.defaultMethod("") + info;
	}

	@Override
	public String normal() {
		return "Test";
	}

	@Override
	public String anotherNormal() {
		return "Another Normal";
	}
}

public class InterfaceForJavaEight {

	public static void main(String [] args) {
		System.out.println(new ClassAndInterface().defaultMethod("test"));// SuperClass Default: test
		System.out.println(new MultilayerInterface().defaultMethod("test"));// Override Default: test
		System.out.println(new ManyInterface().defaultMethod("test"));// ManyInterface Default: Another Default: test
	}
}