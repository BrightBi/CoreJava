package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class Test {

	private static final XMLInputFactory XML_INPUT_FACTORY = XMLInputFactory.newInstance();
	static {
		XML_INPUT_FACTORY.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
		// XML_INPUT_FACTORY.setProperty(XMLInputFactory.SUPPORT_DTD, false);
	}

	public static void main(String[] args) throws IOException, XMLStreamException {

		String s = "<?xml version=\"1.0\"?>\n" + "<!DOCTYPE lolz [\n" + "<!ENTITY lol \"lol\">\n"
				+ "<!ENTITY lol2 \"&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;\">\n"
				+ "<!ENTITY lol3 \"&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;\">\n"
				+ "<!ENTITY lol4 \"&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;\">\n"
				+ "<!ENTITY lol5 \"&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;\">\n"
				+ "<!ENTITY lol6 \"&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;\">\n"
				+ "<!ENTITY lol7 \"&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;\">\n"
				+ "<!ENTITY lol8 \"&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;\">\n"
				+ "<!ENTITY lol9 \"&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;\">\n" + "]>\n"
				+ "<lolz>&lol4;</lolz>";
		XMLEventReader r = null;
		r = XML_INPUT_FACTORY.createXMLEventReader(new StringReader(s));
		while (r.hasNext()) {
			XMLEvent e = r.nextEvent();
			System.out.println(e);
		}

	}

	public static void t2() {
		Locale localeTR = new Locale("tr", "TR");
		Locale localeEN = new Locale("en", "US");
		String title = "https://blog.fondme.cn/apidoc/jdk-1.8-google/";
		// System.out.println(title.toLowerCase(localeEN));
		System.out.println(title.toLowerCase(Locale.ENGLISH).startsWith("https://blog."));

		Boolean b = new Boolean("true");
		System.out.println(b);
		if (b) {
			System.out.println(b);
		}
	}

	public static void t1() throws IOException {
		String fileName = "/Users/mingliangbi/test/test1/test.txt";
		fileName = "/Users/mingliangbi/test/./test1/test.txt";
		System.out.println(fileName.replaceAll("\\.|\\\\|/", ""));

		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String s = null;
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}
		br.close();
	}
}

class Te {
	public Object getModdle (int [] tt) {
		
		
		SortedSet<String> sortedString = new TreeSet<>();
		return tt[0];
	}
}

class Pair {
	private int first;
	private int last;
	
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
}