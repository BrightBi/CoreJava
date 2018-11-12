package corejava.chapter13;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * LinkedList 适用于链表内部经常做插入或删除操作
 * ArrayList 适用于链表不经常插入和删除但是频繁随机检索，线程不同步
 * Vector 线程同步，类似于ArrayList
 * Java 中散列是用链表数组实现的
 */
public class AboutCollection {

	public static void main(String[] args) {
//		iteratorSet();
//		iteratorList();
//		sortedSet();
//		queue();
		priorityQueue();
	}
	
	public static void map() {
		Map<String, String> mapString = new HashMap<>();// 用散列方式存储，不保证顺序
		Map<Worker, String> mapWorker = new TreeMap<>(new Comparator<Worker>(){
			@Override
			public int compare(Worker o1, Worker o2) {
				return o1.getId() - o2.getId();
			}
		});// 用树方式存储，迭代输出时候元素有序输出
		mapString.size();
		mapWorker.size();
	}
	
	public static void collectionView() {
		Collections.sort(new ArrayList<Worker>(), (o1, o2) -> o1.getId() - o2.getId());// 对集合排序
		Collections.shuffle(new ArrayList<String>());// 对集合混排
		Map<String, String> mapNoSynchronized = new HashMap<>();// 非线程同步
		Map<String, String> mapSynchronized = Collections.synchronizedMap(mapNoSynchronized);// 线程同步
		mapSynchronized.size();
		
		List<String> list = new ArrayList<>();
		List<String> listString = Collections.checkedList(list, String.class);// 新得到的 List 将会检查存取类型是否是 String
		listString.add("");
	}
	
	public static void priorityQueue() {
		// PriorityQueue 存入是元素是有序排放的，由于这里的 String 实现了 Comparable 内部比较使用的是 comapreTo()
		// PriorityQueue 的排序是用堆来实现的，TreeSet 的排序是用树来实现的
		PriorityQueue<String> priorityString = new PriorityQueue<>();
		priorityString.add("i");
		priorityString.add("e");
		priorityString.add("z");
		priorityString.add("o");
		while (priorityString.size() > 0) {
			// PriorityQueue 每次都会移除最小的那个元素 
			System.out.print(priorityString.remove() + ", ");
		}
		
		// 像这种没有实现接口的 Worker 类可以通过初始化 PriorityQueue 时候传入个比较器 Comparator
		PriorityQueue<Worker> priorityWorker = new PriorityQueue<>((o1, o2) -> o1.getId() - o2.getId());
		priorityWorker.add(new Worker(7));
		priorityWorker.add(new Worker(3));
		priorityWorker.add(new Worker(9));
		System.out.println();
		while (priorityWorker.size() > 0) {
			// PriorityQueue 每次都会移除最小的那个元素 
			System.out.print(priorityWorker.remove() + ", ");
		}
	}
	
	public static void queue() {
		Queue<String> queue = new ArrayDeque<>(10);
		queue.add("101");// 将元素添加到队列尾部，如果队列已满则抛出异常
		queue.offer("102");// 将元素添加到队列尾部，如果队列已满则返回 false
		String s = queue.element();// 获取队列首部元素，如果队列是空的则抛出异常
		s = queue.peek();// 获取队列首部元素，如果队列是空的则返回 null
		System.out.println(s);
		queue.remove();// 移除队列首部元素，如果队列是空的则抛出异常
		queue.poll();// 移除队列首部元素，如果队列是空的则返回null
	}
	
	public static void sortedSet() {
		// SortedSet 存入是元素是有序排放的，由于这里的 String 实现了 Comparable 内部比较使用的是 comapreTo()
		SortedSet<String> sortedString = new TreeSet<>();
		sortedString.add("i");
		sortedString.add("e");
		sortedString.add("z");
		sortedString.add("o");
		System.out.println(sortedString);
		
		// 像这种没有实现接口的 Worker 类可以通过初始化 SortedSet 时候传入个比较器 Comparator
		SortedSet<Worker> sortedWorker = new TreeSet<>((o1, o2) -> o1.getId() - o2.getId());
		sortedWorker.add(new Worker(7));
		sortedWorker.add(new Worker(3));
		sortedWorker.add(new Worker(9));
		System.out.println(sortedWorker);
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
class Worker {
	private int id;
	public Worker(int id) { this.id = id; }
	public int getId() { return id; }
	@Override
	public String toString() { return "w" + this.id; }
}
