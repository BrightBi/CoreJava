package corejava.chapter12;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/*
 * 范型是为了能够让编写的代码被不同的类型使用，并避免类型转换
 * 比如，作为容器类，我们并不想为 String 或者 Integer 单独编写对应的 List
 * 使用泛型的时候加上的类型参数，会被编译器在编译的时候去掉，JVM 没有范型概念

 * 类型察除引起的问题
 * 泛型类并没有自己独有的Class类对象。
   比如并不存在List<String>.class或是List<Integer>.class，而只有List.class。

 * 静态变量是被泛型类的所有实例所共享的。对于声明为MyClass<T>的类，访问其中的静态变量的方法仍然是 MyClass.myStaticVar。
   不管是通过new MyClass<String>还是new MyClass<Integer>创建的对象，都是共享一个静态变量。
 
 * 泛型的类型参数不能用在Java异常处理的catch语句中。因为异常处理是由JVM在运行时刻来进行的。
   由于类型信息被擦除，JVM是无法区分两个异常类型MyException<String>和MyException<Integer>的。
   对于JVM来说，它们都是 MyException类型的。也就无法执行与异常对应的catch语句。
 */
public class Generic {

	public static void main(String[] args) {
		Pair<String> p = new Pair<String>();
		p.setFirst("first");
		// 不能用基础类型实例化类型参数，原因在于类型察除以后 Object 没法代替 int 等基本数据类型
		// Pair<int> p = new Pair<>();
		Pair<Integer> t = new Pair<Integer>();
		// 运行时的类型查询只产生原始类型
		System.out.println("(p instanceof Pair<?>) | (p instanceof Pair) : " + (p instanceof Pair<?>) + " | " + (p instanceof Pair));
		System.out.println("p.getClass() == t.getClass() : " + (p.getClass() == t.getClass()));
		// 不能初始化参数化类型数组。即，泛型数组初始化时不能声明泛型类型
		// Pair<String> [] pss = new Pair<String>[2];
		/*
		由于泛型存在擦除机制，所以 Pair<String> [] pss = new Pair<String>[2]; 会变成 Pair [] pss = new Pair[2];
		然后问题就来了，你可以将 pss 转成 Object[] 类型，然后往 pss 里面任意塞东西（object类型嘛），如 pss[0] = new Integer(0)。
		显然这样的赋值是要报错的，ArrayStoreException。因为，虽然有擦除，但是有“补偿”(就是数组会记住自己实际的存储类型，这里就是 String 类型)。
		所以你存入 Integer 类型的那句话就出错了，但从理论上往 Object 类型的数组中存 Integer 类型又是合法的。
		故为了避免这种矛盾，不允许创建参数化类型的数组
		*/
		Pair<?> [] ps = new Pair[2];
		ps[0] = new Pair<String>();
		ps[1] = new Pair<MyColor>();
		System.out.println(ps[0] + "" + ps[1]);
		
		Object o = null;
		String s = null;
		List<?> list = new ArrayList<String>();
		list.add(null);
		// list.add(s); // 报错
		// list.add(o); // 报错
		// 带通配符的泛型类没法使用 add 方法，应为不知道具体类型所以没法 add。除非 add(null)
		// s = list.get(0); // 报错
		o = list.get(0);
		// 带通配符的泛型类使用 get 方法只能 get Object
		System.out.println("s:" + s + "| o:" + o);
		
		String [] ss = new String [] {"1001", "1002", "1003"};
		String m = GenericMethod.getMiddle(ss); // 此处可以写成 GenericMethod.<String>getMiddle(s);
		System.out.println(m);
		MyColor [] ms = new MyColor [] {new MyColor(1), new MyColor(2), new MyColor(3)};
		System.out.println(GenericMethod.getMaxInputNumber(ms, new MyColor(5)));
		System.out.println(GenericMethod.getTemp(new Pair<Color>(new Color(1), new Color(2))).getId());
		GenericMethod.setTemp(new Pair<Color>());
		
		// 类型关系
		List<String> listString = new ArrayList<>();
		List<Object> listObject = new ArrayList<>();
		List<? extends Object> listExtendsObject = new ArrayList<>();
		Collection<String> collectionString = new HashSet<>();
		Collection<Object> collectionObject = new HashSet<>();
		Collection<? extends Object> collectionExtendsObject = new HashSet<>();

		// listObject = listString; // error

		listExtendsObject = listString;
		listExtendsObject = listObject;

		collectionString = listString;
		// collectionString = listObject; // error
		// collectionString = listExtendsObject; // error

		// collectionObject = listString; // error
		collectionObject = listObject;
		// collectionObject = listExtendsObject;
		// collectionObject = collectionString; // error

		collectionExtendsObject = listString;
		collectionExtendsObject = listObject;
		collectionExtendsObject = listExtendsObject;
		collectionExtendsObject = collectionString;
		collectionExtendsObject = collectionObject;

		System.out.println("Done:" + collectionExtendsObject);
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
		t.getFirst();
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
	// 不能在静态域和静态方法中使用类型变量
	// public static T test;
	/*
	public static T test(T last) {
		return last;
	}
	*/
	
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