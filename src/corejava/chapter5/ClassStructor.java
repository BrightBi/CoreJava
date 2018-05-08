package corejava.chapter5;

public class ClassStructor {
	
	private String name = "";
	int age = 7;
	
	public ClassStructor (String name) {
		this.name = name;
	}
	
	public ClassStructor (int age) {
		this.age = age;
	}
	
	public ClassStructor (String name, int age) {
		this(name);   // 调用其他构造函数必须位于第一句
		// this(age);   // 不能在同一个构造函数里多次调用其他构造函数
		this.age = age;
	}

	public void hi() {
		System.out.println(this.name + this.age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

/*
 * 子类构造函数中只能调用一次自身的其他构造函数或者父类构造函数，
 * 且这个调用要位于构造函数的第一句
 */
class SubClassStructor extends ClassStructor {
	
	private String color = "";
	private int weigth = 50;
	
	public SubClassStructor (String color) {
		// 如果子类构造函数没有显示调用父类构造函数，那么会调用父类默认构造函数（ClassStructor ()）
		super("subName");   // 由于父类没有定义默认都构造器（ClassStructor ()）此处必须显示调用父类一个构造器
		// super(3);   // 不能在同一个构造函数里多次调用父类构造函数
		this.color = color;
	}
	
	public SubClassStructor (int weigth) {
		super("subName");
		// this(color);   // 显示调用父类构造函数以后不能再调用自己的构造函数
		this.weigth = weigth;
	}
	
	public SubClassStructor (String color, int weigth) {
		// super("subName");   // 显示调用自身构造函数以后不能再调用父类的构造函数
		this(color);
		this.weigth = weigth;
	}

	public void hi() {
		// String name = super.name;   // 子类无法访问父类的私有域
		String name = super.getName();   // 使用 super 调用父类方法
		int age = super.age;
		// ClassStructor classStructor = super; // super 不是对象引用，不能赋值给对象变量
		SubClassStructor subClassStructor = this; // this 是对象引用，能赋值给对象变量
		System.out.println(name + age + subClassStructor.color + subClassStructor.weigth);
	}
}
