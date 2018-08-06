package javaeight;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StreamWork {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000000; i++) {
			double d = Math.random() * 1000;
			list.add(d + "");
		}
		sequential(list);
		parallel(list);

		list.stream().filter(item -> item.length() < 2).collect(Collectors.toCollection(LinkedList::new));
	}

	// Stream 串行处理
	public static void sequential(List<String> list) {
		long start = System.nanoTime();// 获取系统开始排序的时间点
		long count = list.stream().sequential().sorted().count();
		long end = System.nanoTime();// 获取系统结束排序的时间点
		long ms = TimeUnit.NANOSECONDS.toMillis(end - start);// 得到串行排序所用的时间
		System.out.println(ms + "ms| count:" + count);
	}

	// Stream 并行处理
	public static void parallel(List<String> list) {
		long start = System.nanoTime();// 获取系统开始排序的时间点
		long count = list.stream().parallel().sorted().count();
		long end = System.nanoTime();// 获取系统结束排序的时间点
		long ms = TimeUnit.NANOSECONDS.toMillis(end - start);// 得到并行排序所用的时间
		System.out.println(ms + "ms| count:" + count);
	}

}
