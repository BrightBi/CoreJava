package thinkjava.chapter14;

public class ClassInfo {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> c = Class.forName("thinkjava.chapter14.Child");
		info (c);
		for (Class<?> cc : c.getInterfaces()) {
			info (cc);
		}
		Class<?> s = c.getSuperclass();
		Object o = s.newInstance();
		info (o.getClass());
	}

	public static void info (Class<?> c) {
		System.out.println(c.getName());
		System.out.println(c.isInterface());
		System.out.println(c.getSimpleName());
		System.out.println(c.getCanonicalName());
	}
}

interface One {}
interface Two {}
class Super {}
class Child extends Super implements One, Two {}