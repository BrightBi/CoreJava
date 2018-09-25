package others;

public class SpecialArrayPoint {

	public static void main(String[] args) {
		A ta = new A();
		A[] as = null;
		B[] bs = new B[5];
		as = bs; // 此时 as 与 bs 指向同一个数组
		as[1] = ta; // 此时发生异常 java.lang.ArrayStoreException，因为原本的数组是指向存储 B 类型的数组(数组会记住自己存储的数据的类型)
	}

}

class A {
}

class B extends A {
}