package thinkjava.chapter12;

public class ExceptionFinally {

	public static void main(String[] args) {
		nestFinally (10, 0);
		nestFinallyReturn (10, 0);
		try {
			exceptionChain (null);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		exceptionFinal (10);
	}
	
	public static void exceptionFinal (Integer x) {
		try {
			System.out.println(100 / x);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			System.out.println("exceptionFinal finally");
		}
	}
	
	public static void exceptionChain (Integer x) throws Exception {
		if (null == x) {
			Exception e = new Exception();
			// 使用 initCause 将异常链起来
			e.initCause(new NullPointerException());
			throw e;
		}
	}
	
	public static void nestFinally (int x, int y) {
		try {
			System.out.println(100 / x);
			try {
				System.out.println(100 / y);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			} finally {
				System.out.println("Finally inner");
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			System.out.println("Finally outer");
		}
	}
	
	@SuppressWarnings("finally")
	public static void nestFinallyReturn (int x, int y) {
		try {
			System.out.println(100 / x);
			try {
				System.out.println(100 / y);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			} finally {
				System.out.println("Finally inner");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			System.out.println("Finally outer");
		}
	}
}
