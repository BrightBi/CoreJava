package thinkjava.chapter7;
/*
 * 类的构造方法实际上是 static 的，只是没有显示定义出来
 * 所以类的加载时机可以认为是 类的任意 static 成员被访问时（static 成员：static 域/static 方法/构造函数）
 */
public class AboutFinal {
	public static void main(String[] args) {
		ForFinal testFinal = new ForFinal("test");
		System.out.println(testFinal);

		// 局部 final 变量可以先定义，再初始化，再使用
		final String s;
		s = null;
		System.out.println(s);
	}
}
/*
 * 对于 非 static 的 final 域，必须在定义处或者构造块或构造函数中进行初始化
 * 对于 static 的 final 域，必须在定义处或者静态构造块中进行初始化
 */
class ForFinal {
	private final String r;
	private final String s;
	private final String t = "t";
	public static final String ps;
	public static final String pt = "pt";
	
	static { ps = "static ps"; }
	
	{ this.r = "r"; }

	public ForFinal(String s) {
		this.s = s;
	}
	
	/*
	 *  对于 private 方法，都被隐式的指定为 final，
	 *  所以将 private 方法定义为 private final String contact () 没有意义
	 */
	private String contact () {
		return "ps=" + ps + ", pt=" + pt + ", s=" + s + ", t=" + t + ", r=" + r;
	}

	@Override
	public String toString() {
		return contact ();
	}
}

//final 类不能被继承扩展，final 类中所有方法自动变成 final 方法，但是域并不会自动变成 final 域
final class FinalClass {
	private int f = 9;
	private final int g = 7;
	
	// updateF (int f) 没有必要指定为 final
	public final void updateF (int f) {
		this.f = f;
	}
	
	public int getTotal () {
		return f + g;
	}
}