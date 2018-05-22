package corejava.chapter13;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/*
 * LinkedList 适用于链表内部经常做插入或删除操作
 * ArrayList 适用于链表不经常插入和删除但是频繁随机检索，线程不同步
 * Vector 线程同步，类似于ArrayList
 * Java 中散列是用链表数组实现的
 */
public class AboutIterator {

	public static void main(String[] args) {
		iteratorSet();
		iteratorList();
	}
	
	public static void iteratorList() {
		System.out.println("----------------------");
		List<String> l = new LinkedList<>();
		l.add("K1");
		l.add("K2");
		System.out.println(l.size());
		ListIterator<String> iterator = l.listIterator();// 此时 iterator 指向头
		iterator.add("First");// 此时 First 是头元素，而 iterator 指向 First 和 K1 之间。iterator.add() 会将元素添加到 iterator 之前
		String t = iterator.next();// 此时 iterator 指向 K1 和 K2 之间
		System.out.println(t);// 输出 K1
		iterator.remove();// 移除到是刚跳过的元素 K1，此时 iterator 指向 First 和 K2 之间
		t = iterator.previous();// 此时 iterator 指向头
		System.out.println(t);// 输出 First
		iterator.remove();// 移除到是刚跳过的元素 First
		t = iterator.next();
		System.out.println(t);
		iterator.set("Update");
		iterator.add("Second");
		System.out.println("----------------------");
		for (String s : l) {
			System.out.println(s);
		}
		// 使用指定索引位置创建一个迭代器，迭代器指向索引位置之前等同于 get(1)，效率快些
		iterator = l.listIterator(1);
		t = iterator.next();
		System.out.println(t);
		t = l.get(1);
		System.out.println(t);
	}
	
	public static void iteratorSet() {
		Set<String> s = new HashSet<>();
		s.add("K1");
		s.add("K2");
		s.add("K3");
		System.out.println(s.size());
		Iterator<String> iterator = s.iterator();
		while (iterator.hasNext()) {
			String t = iterator.next();
			if (t.equals("K2")) {
				// remove 的是 iterator.next() 得到的数据
				iterator.remove();
				// remove 不能连续调用，iterator.next() 一次，才能做下次remove
				// iterator.remove();
			}
		}
		System.out.println(s.size());
	}
}
