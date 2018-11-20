package thinkjava.chapter5;

/*
 * 对于成员变量不管是否指定了初始值，Java 在初始化对象的时候都会先将成员变量值置为默认值然后再初始化成类中定义的初始值
 * 比如 Initial 的 age 在初始化时候会经历 默认值 0 然后初始化成初始值 1.
 * 
 * 可以理解成 Java 在初始化一个对象时候先去开辟一块存储空间，
 * 开辟空间时候将整个空间清零，于是所有值都被置成初始值（数值型为 0，引用型为 null，char型为 '\u0000'，布尔型为 false）
 * 然后将类中定义的初始值赋值进去
 * 
 */
public class InitialValue {

	public static void main(String[] args) {
		System.out.println(new Initial().getInfo()); // Id is 7. null|1
	}
}

class Initial {
	private int age = 1;
	// 可以调用方法来初始化值
	private String info = getInfo (age);
	// private String idInfor = getInfo(id); // id 的引用不能在其定义之前
	private int id = 7;
	
	public String getInfo (int value) {
		return this.info + "|" + value;
	}
	
	public String getInfo () {
		return "Id is " + this.id + ". " + this.info;
	}
}