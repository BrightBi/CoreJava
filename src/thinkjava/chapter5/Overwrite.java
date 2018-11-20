package thinkjava.chapter5;

public class Overwrite {

	public static void main(String[] args) {
		char c = 1; byte b = 1; short s = 1;
		System.out.println("========== char ==========");
		f1(c); f2(c); f3(c); f4(c); f5(c);
		System.out.println("========== byte ==========");
		f1(b); f2(b); f3(b); f4(b); f5(b);
		System.out.println("========== short ==========");
		f1(s); f2(s); f3(s); f4(s); f5(s);
	}
	
	public static void f1 (char c) { System.out.println("char"); }
	public static void f1 (byte c) { System.out.println("byte"); }
	public static void f1 (short c) { System.out.println("short"); }
	public static void f1 (int c) { System.out.println("int"); }
	public static void f1 (long c) { System.out.println("long"); }
	
	public static void f2 (byte c) { System.out.println("byte"); }
	public static void f2 (short c) { System.out.println("short"); }
	public static void f2 (int c) { System.out.println("int"); }
	public static void f2 (long c) { System.out.println("long"); }
	
	public static void f3 (short c) { System.out.println("short"); }
	public static void f3 (int c) { System.out.println("int"); }
	public static void f3 (long c) { System.out.println("long"); }
	
	public static void f4 (int c) { System.out.println("int"); }
	public static void f4 (long c) { System.out.println("long"); }
	
	public static void f5 (long c) { System.out.println("long"); }
}