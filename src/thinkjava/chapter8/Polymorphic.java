package thinkjava.chapter8;
/*
 * Java 中除了 static 和 final（private 方法也属于 final） 声明的方法，其余方法都是后期绑定
 * 
 * 对父类中对 private 方法“重写”，不会产生多态效果，需要注意
 */
public class Polymorphic {
	
	private void printInfo () {
		System.out.println("Polymorphic");
	}

	public static void main(String[] args) {
		Super s = new Super();
		Super daughter = new Daughter();
		Polymorphic son = new Son();
		s.print();
		daughter.print();
		son.printInfo();
	}
}

class Son extends Polymorphic {
	public void printInfo () {
		System.out.println("Son");
	}
}

class Super {
	private void printInfo () {
		System.out.println("Super");
	}
	
	public void print () {
		printInfo ();
	}
}

class Daughter extends Super {
	private void printInfo () {
		System.out.println("Daughter");
	}

	@Override
	public void print() {
		printInfo ();
	}
}