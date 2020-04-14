package thinkjava.chapter12;
/*
 * 异常都是用 new 在堆上创建的对象，垃圾回收器会处理这些对象的回收。
 * Error 和 Exception 继承自 Throwable，开发需要关注 Exception。
 * Java 异常处理的根本目标是将异常的发生处跟异常的处理处分离。
 */
public class JavaException {

	public static void main(String[] args) {
		try {
			test (0);
		} catch (MyException myException) {// catch 捕获到对应的异常类型以后就不在继续后续的 catch。所以要将细粒度的异常放在前面捕获
			myException.printStackTrace(System.out);
			// e.getStackTrace() 可以得到异常的栈轨迹
			StackTraceElement [] ste = myException.getStackTrace();
			for (int i = 0; i< ste.length; i++) {
				System.out.println(ste[i].getMethodName());
			}
		} catch (Exception exception) {
			exception.printStackTrace(System.out);
		}
		try {
			testNormal ();
		} catch (MyException me) {
			me.printStackTrace(System.out);
		}
		try {
			testFillIn ();
		} catch (MyException e) {
			e.printStackTrace(System.out);
		}
		try {
			testSelfWrap ();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static boolean test (int x) throws MyException {
		if (x > 0) {
			return true;
		} else {
			throw new MyException ("x <= 0");
		}
	}
	
	public static void testNormal () throws MyException {
		try {
			test (0);
		} catch (MyException e) {
			// 此处将异常向外抛出，异常的栈轨迹跟发生异常处的栈轨迹是一致的
			throw e;
		}
	}
	
	public static void testFillIn () throws MyException {
		try {
			test (0);
		} catch (MyException e) {
			// 此处将异常向外抛出，由于使用了 fillInStackTrace()，异常的栈轨迹跟发生异常时的栈轨迹是不一样的。
			// 异常的栈轨迹会以此处为发生异常的起始位置。
			throw (MyException) e.fillInStackTrace();
		}
	}
	
	public static void testSelfWrap () {
		try {
			test (0);
		} catch (MyException e) {
			// 此处将异常向外抛出，由于此处自己包装了异常，异常的栈轨迹跟发生异常时的栈轨迹是不一样的。
			// 异常的栈轨迹会以此处为发生异常的起始位置。
			throw new NullPointerException();
		}
	}
}

class MyException extends Exception {
	private static final long serialVersionUID = 8951020290327917099L;
	public MyException (String message) {
		super(message);
	}
}
