package thinkjava.chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
 * HashMap 用于快速访问，TreeMap 的键始终处于排序状态，LinkedHashMap 保持元素插入顺序
 * HashSet 用于快速访问，TreeSet 的键始终处于排序状态，LinkedHashSet 保持元素插入顺序
 * 
 * 新程序中不应再出现过时的 Vector Hashtable Stack
 */
public class CollectionUtill {

	@SuppressWarnings(value = { "unused" })
	public static void main(String[] args) {
		Integer [] i = new Integer [] {1, 3, 7, 9};
		// java.util.Arrays 将数组或者多个入参转换成 List
		List<Integer> li = Arrays.asList(i);
		li = Arrays.asList(new Integer(2), 0);

		// java.util.Collections 将数组或者多个入参添加到某个 Collection
		Collection<Integer> c = new ArrayList<>();
		Collections.addAll(c, i);
		Collections.addAll(c, new Integer(2), 0);

		// Arrays.<Snow>asList() 此处 <Snow> 表示 Arrays.asList() 要返回一个 存储 Snow 类型的 List
		List<Snow> snow1 = Arrays.<Snow>asList(new Power(), new Crusty());
		List<Snow> snow2  = Arrays.asList(new Heavy(), new Light());

		Queue<Integer> q = new LinkedList<>();
		q.offer(3);
		q.offer(9);
		q.offer(7);
		// peek() 在队列为空的情况下返回 null 而 element() 抛异常。
		while (q.peek() != null) {
			// poll() 在队列为空的情况下返回 null 而 remove() 抛异常。
			System.out.println(q.poll());
		}

		System.out.println("Done");
	}
}

class Snow {}
class Power extends Snow {}
class Light extends Power {}
class Heavy extends Power {}
class Crusty extends Snow {}
class Slush extends Snow {}
