package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelfTest {
	public static void main(String[] args) {
		List<Long> a = new ArrayList<>();
		a.add(1L);
		a.add(2L);
		List<Long> b = new ArrayList<>();
		b.add(3L);
		b.add(4L);
		List<Long> c = new ArrayList<>();
		c.add(5L);
		c.add(6L);
		List<Long> l = Arrays.asList(1L, 2L);
		// l.addAll(c);
		Map<String, List<Long>> map = new HashMap<>();
		map.merge("name1", a, (oValue, nValue) -> {
			oValue.addAll(nValue);
			return oValue;});
		map.merge("name2", b, (oValue, nValue) -> {
			oValue.addAll(nValue);
			return oValue;});
		map.merge("name1", c, (oValue, nValue) -> {
			System.out.print(oValue.getClass().getName());
			System.out.print(nValue.getClass().getName());
			oValue.addAll(nValue);
			return oValue;});
		System.out.print("Boolean b false");
		Date d = new Date();
		System.out.println(d.getTime());
		
		byte f = 7;
		byte s = 0;
		int te = ~f;
		System.out.println(te);
		System.out.println(Integer.toBinaryString(-8));
		System.out.println(Integer.toBinaryString(-7));
		System.out.println(Integer.toBinaryString(-6));
		System.out.println(Integer.toBinaryString(-5));
		System.out.println(Integer.toBinaryString(-4));
		System.out.println(Integer.toBinaryString(-3));
		System.out.println(Integer.toBinaryString(-2));
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-0));
		System.out.println(Integer.toBinaryString(0));
	}
}
