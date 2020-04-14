package thinkjava.chapter10;
/*
 * 普通内部类（非 static 的内部类）隐式的持有一个自己外部类的指向，能找到自己的外部类，所以普通内部类不能直接创建，需要其外部类创建
 * 静态内部类（也叫嵌套内部类）可以不依赖外部类来创建自身，但是静态内部类不能访问外部类的非静态对象，静态内部类并不隐式持有外部类的指向
 * 内部类可以嵌套多层，里层类总能访问外层类的域、方法等属性
 * 为什么要使用内部类：每个内部类都能独立的继承或实现接口或者类，而无需考虑外部类（变向的多重继承啊）
 */
public class InnerClass {

	public static void main(String[] args) {
		ClassWithInnerClass c1 = new ClassWithInnerClass("one");
		// 需要使用外部类的实例对象去创建一个内部类（静态内部类除外）
		ClassWithInnerClass.Inner c1inner = c1.new Inner();
		ClassWithInnerClass c2 = new ClassWithInnerClass("two");
		ClassWithInnerClass.Inner c2inner = c2.new Inner();
		Show show1 = c1.getInnerShow();
		Show show2 = c2.getInnerShow();
		
		c1.getInner().outer().info();
		c2.getInner().outer().info();
		
		c1inner.outer().info();
		c1.getInner().selfInfo();
		c2inner.outer().info();
		c2.getInner().selfInfo();
		
		show1.show();
		show2.show();
		
		ClassWithInnerClass.Inner extendInner = new ExtendInnerClass(c1);
		extendInner.selfInfo();
		c1.getInner().selfInfo();
		
		ClassWithInnerClass extendClassWithInnerClass = new ExtendClassWithInnerClass("ExtendClassWithInnerClass");
		extendClassWithInnerClass.getInner().selfInfo();
	}
}

class ClassWithInnerClass {
	private String info = "";
	public ClassWithInnerClass (String info) {
		this.info = info;
	}
	/*
	 * 通过私有内部类实现接口，对外隐藏了接口具体实现
	 * 然后通过方法将私有的内部实现类以接口类型暴露出去
	 */
	private class InnerShow implements Show {
		@Override
		public void show() {
			System.out.println("ClassWithInnerClass InnerShow " + info);
		}
	}
	public class Inner {
		// 普通内部类不能包含 static 对象(静态域 静态方法 静态类...)，但是可以是 static final，且需要定义时就初始化。
		// 非静态内部类中不能使用 静态构造块 static {...}
		// static int count = 1;
		static final int count = 1;
		// public static void f() {}
		public ClassWithInnerClass outer () {
			// 内部对象生成对外部对象的引用 
			return ClassWithInnerClass.this;
		}
		public void selfInfo () {
			System.out.println("Inner " + info);
		}
	}
	public static class StaticInner {
		private static int count = 1; // 静态内部类可以包含 static 对象(静态域 静态方法 静态类...)
		public static void f () {}
		private int total = 0;
		public ClassWithInnerClass outer () {
			// 静态内部类不持有外部对象指向，所以这里不能用 ClassWithInnerClass.this
			return new ClassWithInnerClass("" + count + total);
		}
	}
	public Show getInnerShow () {
		return new InnerShow();
	}
	public Inner getInner () {
		return new Inner();
	}
	/*
	 * 局部内部类实现接口，对外隐藏了接口具体实现
	 * 局部内部类不能加权限访问控制符，即 加 private public 等 是不合法的
	 * 局部内部类不能在作用域外部使用
	 */
	public Show getShow (boolean flag) {
		class FunctionClass implements Show {
			@Override
			public void show() {
				System.out.println("ClassWithInnerClass AnotherShow " + info);
			}
		}
		if (flag) {
			class ConditionClass implements Show {
				@Override
				public void show() {
					System.out.println("ClassWithInnerClass AnotherShow " + info);
				}
			}
			return new ConditionClass();
		} else {
			return new FunctionClass();
		}
	}
	/*
	 * 匿名内部类以实现接口方式使用，
	 * 匿名内部类以实现类的方式使用
	 */
	public Show getAnonymousShow (boolean flag) {
		if (flag) {
			return new Show () {
				{
					System.out.println("ClassWithInnerClass AnotherShow instance ");
				}
				@Override
				public void show() {
					System.out.println("ClassWithInnerClass AnotherShow " + info + " " + flag);
				}
			};
		} else {
			return new DefaultShow ("getAnonymousShow") {
				@Override
				public void show() {
					System.out.println("ClassWithInnerClass AnotherShow " + info);
				}
			};
		}
		
	}
	public void info () {
		System.out.println("ClassWithInnerClass " + info);
	}
}

interface Show {
	void show ();
	// 在接口中放静态内部类
	static class ShowStatic implements Show {
		@Override
		public void show() {
			System.out.println("Show ShowStatic");
		}
	}
}

class DefaultShow implements Show {
	private String defaultInfo = "";
	public DefaultShow (String defaultInfo) {
		this.defaultInfo = defaultInfo;
	}
	@Override
	public void show() {
		System.out.println("DefaultShow " + defaultInfo);
	}
}

class ExtendInnerClass extends ClassWithInnerClass.Inner {
	public ExtendInnerClass (ClassWithInnerClass classWithInnerClass) {
		classWithInnerClass.super();
	}
	@Override
	public void selfInfo () {
		System.out.println("ExtendInnerClass");
	}
}

class ExtendClassWithInnerClass extends ClassWithInnerClass {
	public ExtendClassWithInnerClass (String info) {
		super(info);
	}
	// 此处的 inner class 不会覆盖 ClassWithInnerClass 的 inner class
	public class Inner {
		public ClassWithInnerClass outer () {
			return ExtendClassWithInnerClass.this;
		}
		public void selfInfo () {
			System.out.println("ExtendClassWithInnerClass Inner");
		}
	}
}