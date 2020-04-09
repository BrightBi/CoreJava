package thinkjava.chapter5;

public class VariableParameter {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		char c1 = 'a';
		char c2 = 'b';
		// infoPrint (c1, c2); // 此处编译不通过
		infoPrint (1.2F, c2);
		infoPrint (11, c2);
	}
	/*
	 * 对于重载方法，应该只在其中一个方法上使用可变参数，或者对于重载方法就不要使用
	 */
	public static void infoPrint (Character ...c) {
		System.out.println("char");
	}
	
	public static void infoPrint (float f, Character ...c) {
		System.out.println("float char");
	}
}
