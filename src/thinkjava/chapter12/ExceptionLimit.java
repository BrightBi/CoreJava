package thinkjava.chapter12;

public class ExceptionLimit {

	public static void main(String[] args) throws Exception {
		new Me().dance();
	}
}

interface Play {
	void dance () throws HerException;
	void sing () throws HerException;
}

class Dancer {
	
	public Dancer() throws HisException {
	}

	public void dance () throws HisException {
		System.out.println("Dancer dance");
	}
}

class Me extends Dancer implements Play {

	/*
	 * 构造函数抛出的异常类型不受父类抛构造函数抛出的异常限制，这与重写方法抛出异常类型受限于父类方法不同。
	 * 但是父类构造函数的异常需要包含在子类构造函数抛出异常的列表中。
	 */
	public Me() throws HerException, HisException {
	}

	/*
	 * Play 接口中 sing () 方法抛出 HerException，
	 * 实现类中可以抛出 HerException 的子类异常
	 */
	@Override
	public void sing() throws ItsException {
		System.out.println("Me sing");
	}
	
	/*
	 * 由于继承了 Dancer，并实现了 Play。dance () 这个方法在接口和实现类中抛出的异常有冲突，
	 * 此处重写的方法不能向外抛出异常，否则编译不通过
	 */
	@Override
	public void dance () {
		System.out.println("Me dance");
	}
}

class HisException extends Exception {
	private static final long serialVersionUID = 3226982610575638759L;
}
class HerException extends Exception {
	private static final long serialVersionUID = 3226982610575638757L;
}
class ItsException extends HerException {
	private static final long serialVersionUID = 3226982610575638757L;
}