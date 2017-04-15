package chapter3;

public class Loop {

	public static void main(String[] args) {
//		floatLoop();
		breakLoop();

	}
	
	public static void breakLoop() {
		loop1:
		for (int i = 0; i < 4; i++) {
			System.out.println("OuterBegin:[" + i + "]");
			if (i == 1) {
				continue;
			}
			for (int j = 0; j < 10; j++) {
				System.out.println("InnerBegin:[" + i + "][" + j + "]");
				if (j == 1) {
					continue loop1;
				}
				if (j == 2) {
					break loop1;
				}
				System.out.println("InnerEnd:[" + i + "][" + j + "]");
			}
			if (i == 2) {
				break;
			}
			
			System.out.println("OuterEnd:[" + i + "]");
		}
	}
	
	public static void floatLoop() {
		// 对于浮点数，不要使用等于或者不等于来判断，应为不精确，容易产生不可预料的错误
		for (double i = 0.0; i != 1; i += 0.1) {
			System.out.println(i);
			if (i > 1.2) {
				break;
			}
		}
	}
}
