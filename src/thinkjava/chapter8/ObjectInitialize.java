package thinkjava.chapter8;

@SuppressWarnings("unused")
public class ObjectInitialize extends B {
	private N n = new N();
	public ObjectInitialize () {System.out.println("ObjectInitialize"); }
	public static void main(String[] args) {
		new ObjectInitialize();
	}
}
class A { public A () {System.out.println("A");} }
class B extends A { public B () {System.out.println("B");} }

class M { public M () {System.out.println("M");} }
class N extends M { public N () {System.out.println("N");} }