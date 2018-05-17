package corejava.chapter11;

public class MyException {

	
	public static void main(String[] args) throws Exception {
		try {
			test();
		} catch (SelfException e) {
			e.printStackTrace();
			// 可以在 catch 中再次抛出异常
			Exception exception = new Exception("Change exception type from SelfException to Exception");
			exception.initCause(e); // 将引发异常的原始异常设置到新异常中，后续可以通过 exception.getCause() 得到原始异常
			throw exception;
		} catch (Exception e) { // Exception 范围大于 SelfException，需要写在后面
			e.printStackTrace();
		}
	}
	
	public static void test () throws SelfException {
		throw new SelfException("SelfException");
	}

}

// 自定义异常
class SelfException extends Exception {
	
	private static final long serialVersionUID = -6145987631880445724L;

	public SelfException() {};
	public SelfException(String m) { super(m); };
}
