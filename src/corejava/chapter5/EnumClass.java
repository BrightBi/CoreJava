package corejava.chapter5;

import java.util.Arrays;

public class EnumClass {

	public static void main(String[] args) {
		Size s = Size.SMALL;
		Size m = Size.MEDIUM;
		Size o = Size.SMALL;
		if (s == o) { // 枚举类型直接使用 == 比较
			System.out.println("s == o");
		}
		// toString() 获取枚举常量名
		System.out.println(m.toString() + "|" + Size.MEDIUM.toString());
		o = Enum.valueOf(Size.class, "SMALL");
		// Size.values() 得到所有枚举类型数组
		System.out.println(Arrays.toString(Size.values()));
		// ordinal() 得到当前类型在枚举中的位置，从 0 开始
		System.out.println(s.ordinal());
		// getAbbreviation() 自定义方法，获取缩写
		System.out.println(s.getAbbreviation());
		// compareTo(other) 当前枚举类型如果出现在 other 之前，返回负值；相等，返回 0；之后，返回正值。
		System.out.println(s.compareTo(m));
	}
}

// 所有 枚举类型都是 Enum 类的子类
enum Size {
	SMALL("S"), MEDIUM("S"), LAGE("S"); // 每一个都是 Size 的一个实例
	private Size(String abbreviation) { this.abbreviation = abbreviation; };
	public String getAbbreviation() { return this.abbreviation; }
	private String abbreviation;
}
