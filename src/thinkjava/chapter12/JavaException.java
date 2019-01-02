package thinkjava.chapter12;

public class JavaException {

	public static void main(String[] args) {
		try {
			test (0);
		} catch (MyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean test (int x) throws MyException {
		if (x > 0) {
			return true;
		} else {
			throw new MyException ("x <= 0");
		}
	}
}

class MyException extends Exception {
	private static final long serialVersionUID = 8951020290327917099L;
	public MyException (String message) {
		super(message);
	}
}
