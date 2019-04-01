package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestFile {

	public static final long m10 = 10485760L;

	private static String one = "/Users/mingliangbi/Downloads/pl-PL/010000.pdf";
	private static String two = "/Users/mingliangbi/Downloads/pl-PL/02.pdf";
	private static String three = "/Users/mingliangbi/Downloads/pl-PL/03.pdf";

	// private static String one = "/Users/mingliangbi/Downloads/pl-PL/test.txt";
	// private static String two = "/Users/mingliangbi/Downloads/pl-PL/test1.txt";

	// private static String one = "/Users/mingliangbi/Downloads/pl-PL/ds.js";

	public static void main(String[] args) throws IOException {
		File fileOne = new File(one).getCanonicalFile();
		File fileTwo = new File(two);
		File fileThree = new File(three);

		System.out.println("one:" + fileOne.length());
		System.out.println("two:" + fileTwo.length());
		System.out.println("three:" + three.length());

		List<File> fileList = new ArrayList<>();
		fileList.add(fileOne);
		fileList.add(fileTwo);
		fileList.add(fileThree);

		long sum = fileList.stream().mapToLong(f -> f.length()).sum();

		List<String> bigFiles = fileList.stream().filter(doc -> doc.length() > m10)
				.map(File::getName).collect(Collectors.toList());
		System.out.println("bigFiles:" + bigFiles.size());
		for (String s : bigFiles) {
			System.out.println(s);
		}
	}

}
