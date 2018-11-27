package thinkjava.chapter8;

public class ConstructorWithFunction {
	public static void main(String[] args) {
		new Cat(2);
	}
}

class Animal {
	public Animal() {
		System.out.println("Animal construct begin");
		/*
		 * 如果调用构造器内部的一个动态绑定方法（也就是子类重写了的方法）
		 * 如果构造的对象是当前对象，那么就会调用当前对象的方法
		 * 如果构造的对象是子类对象，那么会调用子类重写后的方法，然而此时子类还尚未调用子类自己的构造方法，也没有完成初始化。
		 * 
		 * 对于构造器的调用，如果构造的对象所属的类是该构造器所属的类的子类，若该构造器调用了动态绑定方法，
		 * 那么这个方法在该构造器执行期间是没有初始化的（因为先初始化父类，后初始化子类，
		 * 初始化父类时候调用了父类的动态绑定方法，这个动态绑定是绑定到子类中的重写过的方法，
		 * 于是构造器中实际上调用的是子类中重写后的方法），
		 * 此时就出现了动态绑定方法向外延伸到子类重写过的方法
		 */
		info ();
		System.out.println("Animal construct end");
	}
	
	public void info () {
		System.out.println("Info : animal");
	}
}

class Cat extends Animal {
	private int age = 1;
	public Cat(int age) {
		System.out.println("Cat first age is " + this.age);
		this.age = age;
		System.out.println("Cat last age is " + this.age);
	}
	
	public void info () {
		System.out.println("Info : Cat age is " + this.age);
	}
}
