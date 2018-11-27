package thinkjava.chapter8;

@SuppressWarnings("unused")
public class MainInitialize {
	private static SS ss = new SS();
	private TT tt = new TT();
	static {
		System.out.println("Test static");
	}
	{
		System.out.println("Not static");
	}
	public MainInitialize () {System.out.println("MainInitialize"); }
	public static void main(String[] args) {
		System.out.println("Begin");
		new MainInitialize();
		System.out.println("End");
	}
}
class TT { public TT() { System.out.println("TT"); } }
class SS { public SS() { System.out.println("SS"); } }