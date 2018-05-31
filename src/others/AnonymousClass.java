package others;

public class AnonymousClass {
	public static void main(String[] args) {
		// 匿名类可以是一个实现接口的
		Inter inter = new Inter() {
			public void f() {
				System.out.println("Inter");
			}
		};
		inter.f();
		
		// 匿名类可以是一个实现具体类的
		Cla cla = new Cla() {
			public void f() {
				System.out.println("Class test");
			};
		};
		cla.f();
		
		Outer outer = new Outer();
		outer.f(1, 2);
	}
}

interface Inter{ void f(); }

class Cla{
	public void f() {
		System.out.println("Class");
	};
}

class Outer {
	class Inner {
		public int id;
		// public static int agen; // 非静态内部类中不能有静态的成员变量
		public static final String name = "Inner";// 非静态内部类中可以有 final 静态的成员变量
	}
	
	public void f (int a, final int b) {
		final int c = 3;
		int d = 7;
		class InnerI {
			public void f() {
				System.out.println(a);
				System.out.println(b);
				System.out.println(c);
				System.out.println(d);
			}
		}
		InnerI innerI = new InnerI();
		innerI.f();
	}
}

