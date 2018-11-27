package thinkjava.chapter8;
/*
 * Java 继承和组合中能使用组合尽量使用组合
 * 
 * Java 中除了 static 和 final（private 方法也属于 final） 声明的方法，其余方法都是后期绑定
 * 
 * 对父类中 private 方法“重写”，不会产生多态效果，需要注意
 * 
 * 实际上只有非 private 的成员方法具有多态性。域和静态方法都没有多态性
 */
public class Polymorphic {
	
	private void printInfo () {
		System.out.println("Polymorphic");
	}

	@SuppressWarnings({"static-access" })
	public static void main(String[] args) {
		Polymorphic son = new Son();
		son.printInfo();

		Super s = new Super();
		s.print();
		s.staticFunction();
		System.out.println(s.name);

		Super daughter = new Daughter();
		daughter.print();
		daughter.staticFunction();
		System.out.println(daughter.name);
	}
}

class Son extends Polymorphic {
	public void printInfo () {
		System.out.println("Son");
	}
}

class Super {
	public String name = "Super Name";
	private void printInfo () {
		System.out.println("Super");
	}
	
	public void print () {
		printInfo ();
	}
	
	public static void staticFunction() {
		System.out.println("Super staticFunction");
	}
}

class Daughter extends Super {
	public String name = "Daughter Name";
	private void printInfo () {
		System.out.println("Daughter");
	}

	@Override
	public void print() {
		printInfo ();
	}
	
	public static void staticFunction() {
		System.out.println("Daughter staticFunction");
	}
}