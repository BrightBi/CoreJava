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
		System.out.println("Return Missing Exception:" + finallyReturnMissingException (10, 0));
		System.out.println("Return value:" + doubleFinallyReturn (10, 0));
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
	
	@SuppressWarnings("finally")
	public static int finallyReturnMissingException (int x, int y) {
		try {
			System.out.println(x / y);
			return 2;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			// Finally 中带有 return 会使得本来需要抛出的异常丢失。
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public static int doubleFinallyReturn (int x, int y) {
		try {
			System.out.println(100 / x);
			try {
				System.out.println(100 / y);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			} finally {
				System.out.println("Finally inner");
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			System.out.println("Finally outer");
			return 2;
		}
	}
}
