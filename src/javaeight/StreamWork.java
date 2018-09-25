package javaeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamWork {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000000; i++) {
			double d = Math.random() * 1000;
			list.add(d + "");
		}
		// sequential(list);
		// parallel(list);
		// flatMap();
		// summaryStatistics();
		// generate();
		// iterate();
		// groupingBy();
		partitioningBy();

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

	// flatMap方法，可以将多个容器的元素全部映射到一个容器中
	public static void flatMap() {
		Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
		List<Integer> collect = listStream.flatMap(theList -> theList.stream()).map(integer -> integer * integer)
				.collect(Collectors.toList());
		System.out.println("collect: " + collect);
	}

	// 统计信息
	public static void summaryStatistics() {
		List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9, 11);
		// skip(2) 忽略前两个，limit(2)再取出前两个
		IntSummaryStatistics statistics = list1.stream().filter(integer -> integer > 2).mapToInt(i -> i * 2).skip(2)
				.limit(2).summaryStatistics();
		System.out.println("Max: " + statistics.getMax());// 18
		System.out.println("Min: " + statistics.getMin());// 14
		System.out.println("Average: " + statistics.getAverage());// 16
		System.out.println("Count: " + statistics.getCount());// 2
	}

	// 生成连续不断的流或者一个全部是随机数的流
	public static void generate() {
		Stream.generate(UUID.randomUUID()::toString).findFirst().ifPresent(System.out::println);
	}

	// 迭代
	public static void iterate() {
		Stream.iterate(1, item -> item + 2).limit(10).forEach(System.out::println);
		// Stream陷阱 distinct()会一直等待产生的结果去重，将distinct()和limit(6)调换位置，先限制结果集再去重就可以了
		// IntStream.iterate(0,i -> (i + 1) %
		// 2).distinct().limit(6).forEach(System.out::println);
		IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
	}

	// 分组
	public static void groupingBy() {
		List<Student> students = Arrays.asList(new Student("Bi", 97), new Student("Ta", 99), new Student("Ta", 77));
		Map<String, List<Student>> collect0 = students.stream().collect(Collectors.groupingBy(Student::getName));
		Map<String, Long> collect1 = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
		Map<String, Double> collect2 = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));
		System.out.println("collect0: " + collect0);
		System.out.println("collect1: " + collect1);
		System.out.println("collect2: " + collect2);
	}

	// 分区
	public static void partitioningBy() {
		List<Student> students = Arrays.asList(new Student("Bi", 73), new Student("Ta", 37), new Student("Ta", 77));
		Map<Boolean, List<Student>> collect3 = students.stream().collect(Collectors.partitioningBy(student -> student.getScore() >= 60));
		System.out.println(collect3.get(true)); // 输出及格的Student
		System.out.println(collect3.get(false)); // 输出不及格的Student
	}
}

class Student {
	private String name;
	private int score;

	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
