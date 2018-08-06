package javaeight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

class Person {
	public enum Sex {
		MALE, FEMALE
	}

	String name;
	LocalDate birthday;
	Sex gender;
	String emailAddress;

	public String getEmailAddress() {
		return emailAddress;
	}

	public Sex getGender() {
		return gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getName() {
		return name;
	}

	public static int compareByAge(Person a, Person b) {
		return a.birthday.compareTo(b.birthday);
	}
}

class ComparisonProvider {
	public int compareByName(Person a, Person b) {
		return a.getName().compareTo(b.getName());
	}

	public int compareByAge(Person a, Person b) {
		return a.getBirthday().compareTo(b.getBirthday());
	}
}

class Greeter {
	public void greet() {
		System.out.println("Hello!");
	}
}

class ConcurrentGreeter extends Greeter {
	public void greet() {
		System.out.println("Hello, world!");
	}
	
	public void superMethodReferences () {
		// this 和 super 关键字可以在方法引用中使用
		Thread t = new Thread(super::greet);
		t.start();
	}
}

class Example {
	private int step = 0;
	
	public Example(int step) {
		this.step =step;
	}
	
	public int increment(Example number) {
		return number.step + this.step;
	}
	
	public void referencesThisMethod(List<Person> persons) {
		List<Example> examples = new ArrayList<>(); 
		examples.add(new Example(1));
		examples.add(new Example(2));
		examples.add(new Example(3));
		// examples.stream().map(item -> increment(item)).forEach(System.out::println);
		examples.stream().map(this::increment).forEach(System.out::println);
		System.out.println(examples.size());
	}

	@Override
	public String toString() {
		return step + "";
	}
}

public class MethodReferences {

	public static void main(String[] args) {
		Person[] persons = new Person[10];
		String[] stringsArray = { "Hello", "World" };
		referencesClassStaticMethod(persons);
		referencesObjectMethod(persons);
		referencesClassObjectMethod(stringsArray);
		referencesConstructMethod(new ArrayList<>());
		new ConcurrentGreeter().superMethodReferences();
	}

	public static void referencesConstructMethod(List<Person> persons) {
		Supplier<Person> supplier = Person::new;
		Person p = supplier.get();
		p.getName();
		Person[] ps = persons.stream().filter(item -> item.getName().length() > 0).toArray(Person[]::new);
		System.out.println(ps.length);
	}

	public static void referencesClassObjectMethod(String[] stringsArray) {
		// 使用lambda表达式和类的实例方法
		Arrays.sort(stringsArray, (s1, s2) -> s1.compareToIgnoreCase(s2));
		// 使用方法引用来引用类的实例方法
		/*
		 * 这里非常奇怪，sort方法接收的 lambda 表达式不应该是两个参数么，为什么这个实例方法只有一个参数也满足了lambda表达式的定义（想想这个方法是谁来调用的）
		 * 这就是 类名::实例方法名 这种方法引用的特殊之处：当使用 类名::实例方法名 方法引用时，一定是 lambda 表达式所接收的第一个参数来调用实例方法，
		 * 如果lambda表达式接收多个参数，其余的参数作为方法的参数传递进去。
		 */
		Arrays.sort(stringsArray, String::compareToIgnoreCase);
	}

	public static void referencesObjectMethod(Person[] persons) {
		ComparisonProvider provider = new ComparisonProvider();
		// 使用lambda表达式引用对象的实例方法
		Arrays.sort(persons, (a, b) -> provider.compareByAge(a, b));
		// 使用方法引用引用对象的实例方法
		Arrays.sort(persons, provider::compareByAge);
	}

	public static void referencesClassStaticMethod(Person[] persons) {
		// 使用匿名类
		Arrays.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.birthday.compareTo(o2.birthday);
			}
		});
		// 使用lambda表达式
		Arrays.sort(persons, (o1, o2) -> o1.birthday.compareTo(o2.birthday));
		// 使用lambda表达式和类的静态方法
		Arrays.sort(persons, (o1, o2) -> Person.compareByAge(o1, o2));
		// 使用方法引用,引用的是类的静态方法
		Arrays.sort(persons, Person::compareByAge);
	}
}
