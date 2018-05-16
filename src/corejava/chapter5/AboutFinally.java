package corejava.chapter5;

public class AboutFinally {

	public static void main(String[] args) {
		try {
			System.out.println("00");
			int a = 3/3;
			System.out.println(a);
			return;
		} catch (Exception e) {
			System.out.println("02");
			return;
		} finally {
			// 这一句永远都会被执行，不管是否有异常，不管 try 或者 catch 中是否 return
			System.out.println("04");
		}
	}

}