package corejava.chapter11;

public class AboutFinally {

	public static void main(String[] args) {
		System.out.println(testFinally (3));
		System.out.println(testFinally (0));
	}
	
	public static int testFinally (int x) {
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
			if (x == 3) {
				// finally 中的 return 值会取代 try 和 catch 中的返回值
				return 3;
			}
		}
	}

}