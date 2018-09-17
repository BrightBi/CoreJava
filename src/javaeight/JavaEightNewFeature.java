package javaeight;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * lambda 表达式是匿名函数，Lambda 表达式的格式：(参数) -> 表达式
 * (String first, String second) -> Integer.compare(first.length(), second.length())
 * 如果计算的结果并不由一个单一的表达式返回（换言之，返回值存在多种情况），使用“{}"，然后明确指定返回值。
 * (String first, String second) -> { if (first.length() == second.length()) {return true;} else {return false;} }
 * 如果没有参数，则 "()"中就空着。
 * () -> { return Math.E; }
 * 如果参数的类型可以被推断出，则可以直接省略
 * Comparator<String> comp = (first, second) -> Integer.compare(first.length(), second.length());
 * 永远不要定义 result 的类型，lambda 表达式总是从上下文中推断出来的
 * 
 * 函数接口:任何满足单一抽象方法法则的接口，都会被自动视为函数接口。(比如 Runnable 和 Callable 等传统接口，及自定义接口)
 * 函数接口有 3 条重要法则：
 *    1 一个函数接口只有一个抽象方法。
 *    2 在 Object 类中属于公共方法的抽象方法不会被视为单一抽象方法。
 *    3 函数接口可以有默认方法和静态方法
 * 对第二条的说明：如果一个接口声明一个抽象方法，这个抽象方法覆盖了 java.lang.Object 其中的一个公共方法，那么这个方法也不会计入接口的抽象方法计数，
 * 因为接口的任何实现都将具有来自 java.lang.Object 的方法。
 * 要创建自己的函数接口，需要做两件事：
 *    使用 @FunctionalInterface 注释该接口，这是 Java 8 对自定义函数接口的约定。
 *    确保该接口只有一个抽象方法。
 * 
 * 预定义函数式接口(通常我们不需要自己定义函数式接口，Java已经提供了一些预定义的函数式接口)
 * UnaryOperator<T>		对类型为T的对象应用一元运算并返回结果，方法名为apply
 * BinaryOperator<T>	对类型为T的两个对象进行操作并返回结果，方法名为apply。
 * Consumer<T>			对类型为T的对象应用操作，无返回值，方法名为accept。
 * Supplier<T>			返回类型为T的对象，无入参，方法名为get。
 * Function<T, R>		对类型为T的对象应用操作，并返回类型为R的对象，方法名为apply。
 * BiFunction<T, U, R>  对类型为T和U的两个参数对象应用操作，并返回类型为R的对象，方法名为apply。
 * Predicate<T>			确定类型为T的对象是否满足某种约束，返回布尔值，方法名为test。
 * 
 * 高阶函数：可以接收函数，可以创建函数，可以返回函数
 */
public class JavaEightNewFeature {

	public static void main(String[] args) {
		// holdStatic();
		// List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");
		// findNemo(names);
		// omitType();
		lambdaException();
	}

	public static int findFirstOrElse() {
		List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		int result = 0;
		for (int e : values) {
			if (e > 3 && e % 2 == 0) {
				result = e * 2;
				break;
			}
		}
		// 注意 findFirst 和找不到时候使用 orElse
		result = values.stream().filter(e -> e > 3).filter(e -> e % 2 == 0).map(e -> e * 2).findFirst().orElse(0);
		return result;
	}

	public static void lambda() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		numbers.stream().filter(e -> e % 2 == 0).forEach(e -> System.out.println(e));
		// 如果该 lambda 表达式没有对该形参执行任何实际操作, 将传递 lambda 表达式替换为方法引用会比较有益
		numbers.stream().filter(e -> e % 2 == 0).forEach(System.out::println);

		numbers.stream().filter(e -> e % 2 == 0).forEach(e -> e.toString());
		// numbers.stream().filter(e -> e % 2 == 0).forEach(Integer::toString);
		// 我们不能将 lambda 表达式 (Integer e) -> e.toString() 替换为方法引用 Integer::toString，
		// 因为 Integer 类同时包含静态方法 public static String toString(int i) 和实例方法 public String toString()
		// 必须能够确认方法引用正在调用想要的方法。在存在疑问时，最好使用 lambda 表达式，以避免任何混淆或可能的错误。
	}

	/*
	 * Stream 惰性能提高效率，也使得并行化的编写与顺序处理一样容易 
	 * 但是有一个陷阱：这取决于代码的纯度。函数管道中的所有 lambda 表达式和闭包都必须是纯的。
	 * 
	 * 纯函数是幂等的：
	 * 这意味着对纯函数的调用次数没有限制。
	 * 其次，无论调用纯函数多少次，只要给定相同的输入，它都会产生相同的结果。
	 * 第三，纯函数没有副作用：无论您使用它做什么，纯函数都不会更改您的程序中的其他任何元素。
	 * 
	 * 如果您想编写纯函数，请记住，最后这个特征最为重要。实质上，函数纯度有两个规则： 函数不会更改任何元素。 函数不依赖于任何可能更改的元素。
	 * 
	 * 纯函数绝不会在执行期间引起更改或发生更改。 传递给操作的 lambda 表达式和闭包应该是纯的。它们不应修改任何外部状态，也不应依赖于任何可变的外部状态。
	 * 从函数管道的开始到结束，闭包所依赖的状态绝不应被多个线程修改。
	 */
	public static void holdStatic() {
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		int[] factor = new int[] { 2 };
		Stream<Integer> stream = numbers.stream().map(e -> e * factor[0]);
		factor[0] = 0; // 这里改变了状态，实际编程中不应该这么做
		stream.forEach(System.out::println);
	}

	/*
	 * Java 编程风格：命令式，声明式，函数式
	 * 命令式：自己写 for 循环查找容器内指定元素，知道做什么，并具体实现
	 * 声明式：List.contains(value)，利用容器函数，只告知做什么，不关心具体过程
	 * 函数式：Map.merge(key, value, (oldValue, value) -> oldValue + value); 一般都是使用了高阶函数
	 * 可以接收、创建或返回函数的函数或方法被视为高阶函数。
	 */
	public static void findNemo(List<String> names) {
		// 命令式：
		boolean found = false;
		for (String name : names) {
			if (name.equals("Nemo")) {
				found = true;
				break;
			}
		}
		System.out.println("Found Nemo ? :" + found);

		// 声明式：
		System.out.println("Found Nemo ? :" + names.contains("Nemo"));

		// 函数式：
		Map<String, Integer> pageVisits = new HashMap<>();
		String page = "https://qq.com";
		pageVisits.merge(page, 1, (oldValue, value) -> oldValue + value);
	}

	public static List<String> methodInsteadLambda() {
		List<Car> cars = Arrays.asList(new Car("Dodge", "Avenger", 2010), new Car("Buick", "Cascada", 2016), new Car("Ford", "Focus", 2012));
		return cars.stream().filter(car -> car.getYear() > 2000)
			// 将方法引用 Car::getYear 传递给 comparing 方法，而不传递 car -> car.getYear()。方法引用简短、简洁且富于表达。尽可能地使用它们。
			.sorted(Comparator.comparing(Car::getYear)).map(Car::getModel).collect(Collectors.toList());
	}

	public static void insteadFor() {
		for (int i = 1; i < 4; i++) {
			System.out.print(i + "...");
		}
		IntStream.range(1, 4).forEach(i -> System.out.print(i + "..."));
		// for(int i = 0; i <= 5; i++) 替换成 IntStream.rangeClosed(0, 5)
		// for(int i = 7; i > 0; i--) 替换成 IntStream.iterate(7, e -> e - 1).limit(7);
		// for(int i = 1; i <= 10; i = i + 3) 替换成 IntStream.iterate(1, e -> e + 3).takeWhile(i -> i <= 10) Java 9 新增功能
	}

	public static void omitType() {
		IntStream.range(1, 4).forEach((int i) -> System.out.print(i + "..."));
		// Java 能根据推断出 i 的类型，所以省略 类型能简化 lambda 表达式
		IntStream.range(1, 4).forEach((i) -> System.out.print(i + "..."));
		// 简化类型以后，由于只有一个形参，所以 (i) 可以改写成 i；如果 lambda 表达式用的是多个参数，() 不可省略，必须使用 (x, y) -> ...
		IntStream.range(1, 4).forEach(i -> System.out.print(i + "..."));
	}

	// lambda 表达式可以抛出异常，如果抛出经检查的异常，就必须与函数式接口的抽象方法 throws 语句所列出的异常兼容(lambda 抛出异常类型为其实现的接口抛出异常类型或子类)
	public static void lambdaException() {
		MyNumber number = (arr) -> {
			if (arr.length == 0) {
				throw new EmptyArrayException();
			}
			double res = 0;
			for (double i : arr) {
				res += i;
			}
			res /= arr.length;
			return res;
		};
		try {
			System.out.println(number.getAvr());
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
	}
	// 及联 lambda 表达式
	public static void unionLambdaException() {
		List<Integer> numbers = Arrays.asList(7, 77, 777);

		numbers.stream().filter(e -> e > 50).collect(Collectors.toList());
		numbers.stream().filter(e -> e > 50).map(e -> e * 2).collect(Collectors.toList());

		// e -> e > 50 重构
		Predicate<Integer> isGreaterThan50 = number -> number > 50;
		numbers.stream().filter(isGreaterThan50).collect(Collectors.toList());
		numbers.stream().filter(isGreaterThan50).map(e -> e * 2).collect(Collectors.toList());
		
		numbers.stream().filter(e -> e > 5).collect(Collectors.toList());
		numbers.stream().filter(e -> e > 50).map(e -> e * 2).collect(Collectors.toList());
		numbers.stream().filter(e -> e > 500).map(e -> e * 2).collect(Collectors.toList());

		// e -> e > ? 重构, 思路是创建一个 函数／lambda 入参是一个入参是 Integer 出参 是 e -> e > ? 
		// isGreaterThan1 是一个 Function，入参 Integer 返回一个 Predicate<Integer>；简单讲，传入 Integer 返回一个 lambda 表达式
		Function<Integer, Predicate<Integer>> isGreaterThan1 =
				(Integer pivot) -> {
					Predicate<Integer> isGreaterThanPivot = (Integer candidate) -> { return candidate > pivot; };
					return isGreaterThanPivot;
				};
		// 对 isGreaterThan1 进行精简，除去不必要的类型信息
		Function<Integer, Predicate<Integer>> isGreaterThan2 =
				pivot -> {
					Predicate<Integer> isGreaterThanPivot = candidate -> { return candidate > pivot; };
					return isGreaterThanPivot;
				};
		// 对 isGreaterThan2 进行精简，除去不必要的中间变量和 return
		Function<Integer, Predicate<Integer>> isGreaterThan3 =
				pivot -> {
					return candidate -> candidate > pivot;
				};
		// 对 isGreaterThan3 再度进行精简，除去不必要的 return
		Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> candidate -> candidate > pivot; // 直接看到这个是不是看不懂 啊哈哈哈哈哈

		numbers.stream().filter(isGreaterThan.apply(5)).collect(Collectors.toList());
		numbers.stream().filter(isGreaterThan1.apply(5)).collect(Collectors.toList());
		numbers.stream().filter(isGreaterThan2.apply(5)).collect(Collectors.toList());
		numbers.stream().filter(isGreaterThan3.apply(5)).collect(Collectors.toList());
	}
	
	// Java 闭包（引用了自由变量的函数）
	// lambda 表达式是无状态的，闭包是有状态的
	public static void closure() {
		int factor = 3;
		List<Integer> numbers = Arrays.asList(7, 77, 777);
		// map(e -> e * 2) 此处 map 接受的是 lambda 表达式；lambda 表达式是无状态的，lambda 表达式依赖于传入的参数和字面常量。二者都是独立的，这意味着它们没有任何外部依赖项。
		numbers.stream().filter(e -> e % 2 == 0).map(e -> e * 2).collect(Collectors.toList());
		// map(e -> e * factor) 此处 map 接受的是闭包；闭包包含了外部状态（factor）
		numbers.stream().filter(e -> e % 2 == 0).map(e -> e * factor).collect(Collectors.toList());
	}
}

class EmptyArrayException extends Exception {
	private static final long serialVersionUID = -5733877883290086757L;
	EmptyArrayException() {
		super("Empty Array");
	}
}

interface MyNumber {
	double getAvr(double... arr) throws EmptyArrayException;
}

class Car {
	private String make;
	private String model;
	private int year;

	public Car(String theMake, String theModel, int yearOfMake) {
		make = theMake;
		model = theModel;
		year = yearOfMake;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}
}
