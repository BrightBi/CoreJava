package thinkjava.chapter13;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * B 指定字符 B；\xhh 十六进制为 oxhh 的字符；\ uhhhh 十六进制为 oxhhhh 的 Unicode 字符；\t 制表符；\n 换行符；\r回车符；\f换页符；\e 转义符（Escape）。
 * 
 * . -> 任意字符；
 * [adg] -> a d g 中任意一个，等同于 a|d|g
 * [^adg] -> 除 a d g 外，任意一个字符
 * [a-zA-Z] -> a 到 z 或 A 到 Z 中任意一个字符
 * [ab[xy]] -> a b x y 中任意一个，等同于 a|b|x|y（取并集）
 * [aie&&[iey]] -> i e 中任意一个（取交集）
 * \s -> 空白（空格 tab 换行 换页 回车）
 * \S -> 非空白
 * \d -> 数字[0-9]
 * \D -> 非数字
 * \w -> 词符[a-zA-Z0-9]
 * \W -> 非词符
 * 
 * ? -> 0 或 1 次
 * * -> 0 或多次
 * + -> 1 或多次
 * {n} -> 恰好 n 次
 * {n,} -> 至少 n 次
 * {n,m} -> 至少 n 次，且不超过 m 次
 * 
 * 正则表达式 A(B(C))D(E) 一共有 4 组，
 * 第 0 组：ABCD
 * 第 1 组：BC
 * 第 2 组：C
 * 第 3 组：E
 *
 */
public class Regex {

	public static void main(String[] args) {
		// base();
		// fingIndex();
		// group();
		// pattern();
		matcher();
	}

	public static void base() {
		String txt = "abcabcdefabc";
		String [] regexs = {"abc+", "(abc)+", "(abc){2,}"};
		for (String r : regexs) {
			Pattern p = Pattern.compile(r);
			Matcher m = p.matcher(txt);
			System.out.println("=======" + r + "=======");
			while(m.find()) {// find() 是从字符串的起始位置开始查找，再次调用 find() 将从与上次不匹配的第一个字符开始。
				System.out.println("Match " + m.group() + " at " + m.start() + " - " + (m.end() - 1));
			}
		}
		/*
			=======abc+=======
			Match abc at 0 - 2
			Match abc at 3 - 5
			Match abc at 9 - 11
			=======(abc)+=======
			Match abcabc at 0 - 5
			Match abc at 9 - 11
			=======(abc){2,}=======
			Match abcabc at 0 - 5
		 */
	}
	
	public static void fingIndex() {
		Matcher m = Pattern.compile("\\w+").matcher("It is.");
		int i = 0;
		while(m.find(i)) {// find(index) 是从字符串的 index 位置开始查找，再次调用 find() 将从与上次不匹配的第一个字符开始。
			System.out.println("Match " + m.group() + " at " + m.start() + " - " + (m.end() - 1));
			i++;
		}
		/*
			Match It at 0 - 1
			Match t at 1 - 1
			Match is at 3 - 4
			Match is at 3 - 4
			Match s at 4 - 4
		 */
	}
	
	public static void group() {
		Matcher m = Pattern.compile("\\d+([abc](\\d+))(-)+").matcher("86c9-11d2-33a7-19b3");
		while(m.find()) {
			int groupNum = m.groupCount();
			for (int i = 0; i <= groupNum; i++) {
				System.out.println("Match group " + i + ":" + m.group(i) + " | " + m.start() + " - " + (m.end() - 1));
			}
		}
		
		/*
			Match group 0:86c9- | 0 - 4
			Match group 1:c9 | 0 - 4
			Match group 2:9 | 0 - 4
			Match group 3:- | 0 - 4
			Match group 0:33a7- | 10 - 14
			Match group 1:a7 | 10 - 14
			Match group 2:7 | 10 - 14
			Match group 3:- | 10 - 14
		 */
	}
	
	public static void pattern() {
		// Pattern.matches(regex, s) 判断整个 s 字符串是否匹配 regex
		if (Pattern.matches("\\d+", "11d")) {// Not match
			System.out.println("Match");
		} else {
			System.out.println("Not match");
		}
		if (Pattern.matches("\\d+", "117")) {// Match
			System.out.println("Match");
		} else {
			System.out.println("Not match");
		}
		System.out.println(Arrays.asList(Pattern.compile("\\d+").split("123aa456bb")));// [, aa, bb]
	}
	
	public static void matcher() {
		// Matcher.matches() 判断整个 s 字符串是否匹配 regex
		Matcher m1 = Pattern.compile("\\d+").matcher("11d");
		Matcher m2 = Pattern.compile("\\d+").matcher("111");
		System.out.println(m1.matches()); // false
		System.out.println(m2.matches()); // true
		
		// Matcher.lookingAt() 从 s 字符串开始位置判断 s 字符串是否匹配 regex
		Matcher m3 = Pattern.compile("\\d+").matcher("11d");
		Matcher m4 = Pattern.compile("\\d+").matcher("d11");
		System.out.println(m3.lookingAt()); // true
		System.out.println(m4.lookingAt()); // false
		
		// Pattern.compile 带模式参数
		Matcher m5 = Pattern.compile("^jaVa", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)
				.matcher("java is a\nJava is b\nJAVA is c\nd is java");
		while (m5.find()) {
			System.out.println(m5.group());
			/*
			java
			Java
			JAVA
			 */
		}
		
		System.out.println(Arrays.toString(Pattern.compile("--").split("11--22--33--")));// [11, 22, 33]
		
		Matcher m6 = Pattern.compile("--").matcher("11--22--33--");
		StringBuffer sb = new StringBuffer();
		while (m6.find()) {
			// Matcher.appendReplacement 渐进式替换，每次匹配成功都会用指定字符串来替换。
			m6.appendReplacement(sb, "[" + m6.group() + "]");
		}
		// Matcher.appendTail 将剩余没匹配成功部分追加到 sb 末尾。
		m6.appendTail(sb);
		System.out.println(sb.toString());// 11[--]22[--]33[--]
		
		Matcher m7 = Pattern.compile("\\d+").matcher("11--22");
		while (m7.find()) {
			System.out.println(m7.group());
		}
		m7.reset("99--00"); // 重制字符串，Matcher.reset()会重制到当前字符串起始位置。
		while (m7.find()) {
			System.out.println(m7.group());
		}
	}
}
