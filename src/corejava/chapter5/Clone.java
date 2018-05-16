package corejava.chapter5;

import java.util.Date;

public class Clone {

	public static void main(String[] args) throws CloneNotSupportedException {
		Dog d = new Dog();
		Dog c = d.clone();
		c.setName("Wang");
	}
}

/*
 * clone() 方法是Object 类中的 protected 方法
 * 默认是用的浅拷贝，通过浅拷贝得到的对象，它的基本类型会拷贝，对象类型依然指向同一个对象
 * 所以需要重写这个 clone() 方法，进行深拷贝，并扩展访问控制为 public
 * 一般情况都会让具有拷贝功能的类实现 Cloneable 接口
 */
class Dog implements Cloneable{
	private String name = "dog";
	private Date birthday = new Date();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public Dog clone () throws CloneNotSupportedException{
		Dog cloned = (Dog) super.clone();
		cloned.birthday = (Date)this.birthday.clone();
		return cloned;
	}
}