package mock.object;

public class Tool {

	private Cat c = new Cat("Default");

	public void saveCat(Cat c) {
		System.out.println(c.getName());
	}

	public void updateCatName(String name) {
		c.setName(name);
		System.out.println(c.getName());
	}

	public void aboutException(Cat c) throws Exception {
		System.out.println(c.getName());
	}

	public String aboutExceptionWithReturn(Cat c) throws Exception {
		return c.getName();
	}

	public Cat findCat(Cat c) {
		return new Cat(c.getName() + "cc");
	}

	public String getDefaultName() {
		return this.c.getName();
	}

	public int sum(int... num) {
		int sum = 0;
		for (int n : num) {
			sum += n;
		}
		return sum;
	}

	public Cat getCat(String name) {
		Cat cat = new Cat(name);
		return cat;
	}
	
	public static String getStaticValue(String name) {
		String s = "static: " + name;
		return s;
	}
	
	public static void updateValue(House house) {
		house.setName("new update name");
		System.out.println("nothing");
	}
	
	public boolean validate() {
		return true;
	}
	
	public final boolean isFinal() {
		return true;
	}
	
	public int randum() {
		return (int) (Math.random() * 10);
	}

	public boolean manyParameters(int in, String s, House house) {
		return false;
	}
}
