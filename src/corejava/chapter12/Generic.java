package corejava.chapter12;

import java.io.Serializable;

/*
 * 范型是为了能够让编写的代码被不同的类型使用，并避免类型转换
 * 比如，作为容器类，我们并不想为 String 或者 Integer 单独编写对应的 List
 */
public class Generic {

	public static void main(String[] args) {
		Pair<String> p = new Pair<>();
		p.setFirst("first");
		// 不能用基础类型实例化类型参数
		// Pair<int> p = new Pair<>();
		Pair<Integer> t = new Pair<>();
		// 运行时的类型查询只产生原始类型
		System.out.println((p instanceof Pair<?>) + " | " + (p instanceof Pair));
		if (p.getClass() == t.getClass()) {
			System.out.println("p.getClass() == t.getClass()");
		}
		// 不能初始化参数化类型数组
		// Pair<String> [] ps = new Pair<String>[2];
		Pair<?> [] ps = new Pair[2];
		System.out.println(ps.length);
		String [] s = new String [] {"1001", "1002", "1003"};
		String m = GenericMethod.getMiddle(s); // 此处可以写成 GenericMethod.<String>getMiddle(s);
		System.out.println(m);
		MyColor [] ms = new MyColor [] {new MyColor(1), new MyColor(2), new MyColor(3)};
		System.out.println(GenericMethod.getMaxInputNumber(ms, new MyColor(5)));
		System.out.println(GenericMethod.getTemp(new Pair<Color>(new Color(1), new Color(2))).getId());
		GenericMethod.setTemp(new Pair<Color>());
	}

}

class Color {
	private int id = 0;
	
	public Color() {
		this.id = 1;
	}
	
	public Color(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

class MyColor extends Color implements Comparable<MyColor>, Serializable{

	private static final long serialVersionUID = -3984420061563615026L;
	
	public MyColor(int id) {
		this.setId(id);
	}

	@Override
	public int compareTo(MyColor o) {
		return super.getId() - o.getId();
	}
	
}

/*
 * 虚拟机是没有范型的，会自动提供一个原始类型。将范型的类型变量替换为对应的限定类型(多个限定类型，用第一个)，没有限定类型的用 Object 代替
 * 比如 class Pair<T> 中的 public Pair (T first, T last) {} 最终形态是 public Pair (Object first, Object last) {}
 * class GenericMethod 中的 public static <T extends Color & Comparable<T> & Serializable, U extends Comparable<T>> int getMaxInputNumber (T [] t, U u) {}
 * 最终形态 public static int getMaxInputNumber (Color [] t, Comparable u) {}
 */
class GenericMethod {
	
	// 问号是通配符，? extends Color 此处表示可变参数需要是 Color 或其子类
	public static Color getTemp (Pair<? extends Color> t) {
		return t.getFirst();
	}
	
	// 问号是通配符，? super Color 此处表示可变参数是 Color 或者入参的父类是 Color
	public static void setTemp (Pair<? super Color> t) {
		Object o = t.getFirst();
		o.getClass();
	}
	
	public static <T> T getMiddle (T [] t) {
		return t[t.length / 2];
	}
	
	public static <T extends Throwable> void aboutException (T t) throws T{
		if (t.getMessage().length() > 0) {
			throw t;
		}
	}
	
	public static <T> Pair<T> aboutGenericClass (Class<T> c) {
		try {
			return new Pair<T>(c.newInstance(), c.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 可以为范型绑定类型，也就是说范型必须是绑定类型或其子类型
	 * 范型和其绑定的类型，可以是类，也可以是接口；使用 extends 建立绑定关系(绑定接口也是用的 extends)
	 * 多个绑定之间使用 & 分割，多个范型之间用逗号分割
	 * 范型的绑定类型最多只能有一个类，可以有多个接口(与类的继承相似)，其中类必须写在第一个
	 * 为了效率，一般会将标签接口放在绑定类型最后位置，比如这里的 Serializable
	 */
	public static <T extends Color & Comparable<T> & Serializable, U extends Comparable<T>> int getMaxInputNumber (T [] t, U u) {
		int number = 0;
		for (T temp : t) {
			if (u.compareTo(temp) > 0) {
				number ++;
			}
		}
		return number;
	}
}
/*
 * 对于范型类，通常 C 是 S 的子类，而 Pair<C> 与 Pair<S> 并不具有父子关系
 */
class Pair<T> {
	private T first;
	private T last;
	// 不能在静态域中使用类型变量
	// public static T test;
	
	public Pair () {
		this.first = null;
		// 不能实例化类型变量
		// this.first = new T();
		this.last = null;
	}
	
	public Pair (T first, T last) {
		this.first = first;
		this.last = last;
	}
	
	// 不能在静态方法中使用类型变量
	/*
	public static T test(T last) {
		return last;
	}
	*/
	
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public T getLast() {
		return last;
	}
	public void setLast(T last) {
		this.last = last;
	}
}