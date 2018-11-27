package thinkjava.chapter9;
/*
 * Interface 前可以使用 public 关键字修饰（跟类是一样的）
 * 使用 public 修饰以后 接口名必须要和文件名相同
 * 
 * 不使用 public 修饰也可以，此时接口名可以跟文件名不同，
 * 且只能在包内使用该接口
 */
public interface AboutInterface {

	public static final boolean flag = true; // 接口中的域都是 public static final
	int age = 1; // 不指定的情况下，默认也是 public static final
	
	public boolean getFlag (); // 接口中方法都是 public
	boolean getAge (); // 不指定的情况下，默认也是 public
}
/*
 * 可以实现多个接口，但只能继承一个类
 */