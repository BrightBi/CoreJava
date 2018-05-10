package corejava.chapter5;

import java.util.Arrays;

public class ObjectEqual {

	public static void main(String[] args) {
		String s = "Bright";
		String t = new String("Bright");
		StringBuilder sb = new StringBuilder(s);
		StringBuilder sc = new StringBuilder(s);
		// String 类型重写了 hashCode 方法，hashCode 只与具体的字符串内容有关
		System.out.println(s.hashCode() + "|" + t.hashCode());
		// StringBuilder 没有重写 hashCode 方法，hashCode 取的是存储地址
		System.out.println(sb.hashCode() + "|" + sc.hashCode());
		
		String [] str = {"s1", "s2"};
		// Arrays.hashCode()，能够根据数组元素的散列码计算出数组的散列码
		// Arrays.toString()，能够将数组转换成一个字符串，多维数组可以使用 Arrays.deepToString()
		System.out.println(Arrays.hashCode(str));
		System.out.println(Arrays.toString(str));
	}
}

class A {
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (null == o) {
			return false;
		}
		// 根据实际情况 使用 getClass() 或者 instanceof 判断
		// 一般情况下，如果所有子类都有统一标准，那么就用 instanceof；如果每个子类都有各自标准，则用 getClass()
		if (this.getClass() != o.getClass()) {
			return false;
		}
		A a = (A) o;
		return this.name.equals(a.getName());
	}
	// 重写了 equals 方法一定要重写 hashCode 方法
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}

class B extends A {
	private int age = 7;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public boolean equals(Object o) {
		// super.equals 会用 getClass() 检查 this 和 o 是否是同一 class 
		if (!super.equals(o)) {
			return false;
		}
		B a = (B) o;
		return this.age == a.age;
	}
	// 重写了 equals 方法一定要重写 hashCode 方法
	@Override
	public int hashCode() {
		return super.hashCode() + 17 * new Integer(this.age).hashCode();
	}
}