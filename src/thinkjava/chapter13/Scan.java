package thinkjava.chapter13;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Scan {
	
	private static BufferedReader br = new BufferedReader(new StringReader("Bright\n33"));

	public static void main (String [] args) {
		// simple();
		// selfScanEnd();
		regexScanEnd();
	}
	
	public static void simple() {
		Scanner scanner = new Scanner(br);
		System.out.println(scanner.nextLine());
		System.out.println(scanner.nextInt());
		scanner.close();
	}
	
	public static void selfScanEnd() {
		Scanner scanner = new Scanner("Bright-1-33-4-Hi");
		// 自定义 Scanner 的结束符号，默认是空白。
		scanner.useDelimiter("-\\d-");
		while (scanner.hasNext()) {
			System.out.println(scanner.next());
		}
		System.out.println(scanner.delimiter());
		scanner.close();
	}
	
	public static void regexScanEnd() {
		Scanner scanner = new Scanner("Bright-22\nBi-33\nHappy-26\n");
		String pattern = "(\\w+)-(\\d+)";
		// scanner.hasNext(pattern) 是基于下一次输入，如果下一次输入不匹配，while 循环将结束。
		while (scanner.hasNext(pattern)) {
			scanner.next(pattern);
			MatchResult result = scanner.match();
			System.out.println(result.group(1));
		}
		scanner.close();
	}
}
