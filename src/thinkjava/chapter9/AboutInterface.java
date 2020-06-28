package thinkjava.chapter9;

/*
 * Interface 前可以使用 public 关键字修饰（跟类是一样的）
 * 使用 public 修饰以后 接口名必须要和文件名相同
 * 
 * 不使用 public 修饰也可以，此时接口名可以跟文件名不同，
 * 且只能在包内使用该接口
 */
public interface AboutInterface {

	public static final boolean flag = true; // 接口中的域都是 public static final
	int age = 1; // 不指定的情况下，默认也是 public static final
	
	public boolean getFlag (); // 接口中方法必须是 public/default/abstract/static
	int getAge (); // 不指定的情况下，默认也是 public abstract

	/*
	 * 接口中可以嵌套接口。
	 * 由于接口的特性，接口中所有元素都是 public 的，
	 * 所以此处的 public interface InnerPublicInterface 可以直接省去 public
	 * 直接使用 interface InnerPublicInterface
	 */
	public interface InnerPublicInterface {
		void f();
	}
	
	interface InnerInterface {
		void f();
	}
}

@SuppressWarnings("unused")
class A {

	interface Ia { void f(); }
	public interface Ib { void f(); }
	private interface Ic { void f(); }

	public class IaImpPublic implements Ia {
		@Override
		public void f() { System.out.println("IaImpPublic"); }
	}

	private class IaImpPrivate implements Ia {
		@Override
		public void f() { System.out.println("IaImpPrivate"); }
	}

	public class IbImpPublic implements Ib {
		@Override
		public void f() { System.out.println("IbImpPublic"); }
	}
	private class IbImpPrivate implements Ib {
		@Override
		public void f() { System.out.println("IbImpPrivate"); }
	}

	public class IcImpPublic implements Ic {
		@Override
		public void f() { System.out.println("IcImpPublic"); }
	}
	private class IcImpPrivate implements Ic {
		@Override
		public void f() { System.out.println("IcImpPrivate"); }
	}
	
	public Ic getIc () { return new IcImpPrivate(); }
	private Ic ic;
	public void recivedIc (Ic ic) {
		this.ic = ic;
		this.ic.f();
	}
}

class TestInterface {
	public class AIaImpPublic implements A.Ia {
		public void f() { System.out.println("AIaImpPublic"); }
	}
	public class AIbImpPublic implements A.Ib {
		public void f() { System.out.println("AIbImpPublic"); }
	}
	// 不能实现 private 的内部接口
	// public class AIcImpPublic implements A.Ic { public void f() { System.out.println("AIcImpPublic"); } }
	public class AboutInterfaceImp implements AboutInterface {
		@Override
		public boolean getFlag() { return false; }
		@Override
		public int getAge() { return 9; }
	}
	
	public class AboutInterfaceInnerInterface implements AboutInterface.InnerInterface {
		@Override
		public void f() { System.out.println("AboutInterfaceInnerInterface"); }
	}
	
	public static void main(String[] args) {
		A a = new A();
		a.getIc();
		// A.Ic = a.getIc(); // 无法访问 A.Ic
		// a.getIc().f(); 无法使用 Ic
		new A().recivedIc(a.getIc());
	}
}

