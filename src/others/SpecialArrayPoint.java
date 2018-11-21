package others;

public class SpecialArrayPoint {

	public static void main(String[] args) {
		// changeReferenceFromChildToSuper();
	}
	
	public static void changeReferenceFromChildToSuper() {
		A a = new A();
		A[] aArray = null;
		B[] bArray = new B[5];
		aArray = bArray; // 此时 aArray 与 bArray 指向同一个数组
		/*
		 * 此时发生异常 java.lang.ArrayStoreException
		 * 因为原本的数组是指向存储 B 类型的数组(数组会记住自己存储的数据的类型)
		 */
		aArray[1] = a;
	}
}

class A { }
class B extends A { }