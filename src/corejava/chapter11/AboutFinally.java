package corejava.chapter11;

public class AboutFinally {

	public static void main(String[] args) {
		System.out.println("testFinally (3) : " + testFinally (1, true)); // return 3
		System.out.println("testFinally (1) : " + testFinally (1, false)); // return 1
		System.out.println("testFinally (0) : " + testFinally (0, true)); // return 3
		System.out.println("testFinally (0) : " + testFinally (0, false)); // return 2
	}
	
	public static int testFinally (int x, boolean finalReturn) {
		try {
			System.out.println("00");
			int a = 3 / x;
			System.out.println(a);
			return 1;
		} catch (Exception e) {
			System.out.println("02");
			return 2;
		} finally {
			// 这一句永远都会被执行，不管是否有异常，不管 try 或者 catch 中是否 return
			System.out.println("04");
			if (finalReturn) {
				// finally 中的 return 值会取代 try 和 catch 中的返回值
				return 3;
			}
		}
	}

}