package corejava.chapter5;

public class Person {
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	protected Person getSelf(String name) {
		Person person = new Person();
		person.setName(name);
		return person;
	}

	final public int getId() {
		return this.name.length();
	}
}

class Student extends Person {
	private int age = 7;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/* 子类重写父类方法要求方法名跟参数必须一致
	 * 而对于返回类型，子类可以返回原返回类型的子类，即可协变的返回类型
	 * 子类重写父类方法，子类中方法的可见性不能低于父类的
	 * 子类重写父类方法，子类方法抛出异常范围不可大于父类方法的异常范围
	 * （比如 父类抛出 IOException，子类不可以抛出 Exception。而父类抛出 Exception，子类可以抛出 IOException）
	 * 父类方法不抛出异常，子类方法也不应抛出异常。父类方法抛出异常，子类方法可以不抛出异常
	 */
	@Override
	public Student getSelf(String name) {
		Student student = new Student();
		student.setName(name);
		return student;
	}
	
	/*
	// final 方法在子类中不能被重写
	@Override
	public int getId() {
		return this.name.length();
	}
	*/
}

// final 类不能被继承扩展，final 类中所有方法自动变成 final 方法，但是域并不会自动变成 final 域
final class MyFinal {
	
	public static String S = "S";
	public final static String FINAL_S = "FINAL_S";
	private String p = "p";
	private final String finalp = "finalp";
	
	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getFinalp() {
		return finalp;
	}
	
	public String detail() {
		return S + " | " + FINAL_S + " | " + p + " | " + finalp;
	}
}