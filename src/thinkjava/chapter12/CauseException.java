package thinkjava.chapter12;
/**
 * 将检查异常转换成非检查异常。
 * 使用 new MyRuntimeException(e); 将检查异常封装到非检查异常中。
 * 使用 e.getCause(); 将检查异常从非检查异常中取出。
 */
public class CauseException {

	public static void main(String[] args) {
		ExceptionOrRuntimeException e = new ExceptionOrRuntimeException();
		
		try {
			e.exception();// 此处抛出的是检查异常，必须 try catch 处理，或者向上抛出
		} catch (MyCauseException me) {
			me.printStackTrace();
		}
		try {
			e.unwrapException();// 此处抛出的是检查异常，必须 try catch 处理，或者向上抛出
		} catch (MyCauseException me) {
			me.printStackTrace();
		}
		try {
			e.runtimeException();// 此处抛出的是非检查异常，可以不处理
		} catch (MyRuntimeException mre) {
			mre.printStackTrace();
		}
		try {
			e.wrapException();// 此处抛出的是非检查异常，可以不处理
		} catch (MyRuntimeException mre) {
			mre.printStackTrace();
		}
	}
}
class ExceptionOrRuntimeException {
	public void exception () throws MyCauseException {
		throw new MyCauseException("ExceptionOrRuntimeException exception");
	}
	public void runtimeException () {
		throw new MyRuntimeException("ExceptionOrRuntimeException runtimeException");
	}
	public void wrapException () {
		try {
			exception();
		} catch (MyCauseException e) {
			throw new MyRuntimeException(e);
		}
	}
	public void unwrapException () throws MyCauseException {
		try {
			wrapException();
		} catch (MyRuntimeException e) {
			throw (MyCauseException) e.getCause();
		}
	}
}

class MyRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 3952699427831561066L;
	public MyRuntimeException(String s) {
		super(s);
	}
	public MyRuntimeException(Exception e) {
		super(e);
	}
}
class MyCauseException extends Exception {
	private static final long serialVersionUID = 1217604105687619047L;
	public MyCauseException(String s) {
		super(s);
	}
}