package mock;

public class Tool {
	
	private Cat c = new Cat("Default");

	public void saveCat(Cat c) {
		System.out.println(c.getName());
	}
	
	public void aboutException(Cat c) throws Exception {
		System.out.println(c.getName());
	}
	
	public Cat findCat(Cat c) {
		return new Cat(c.getName() + "cc");
	}
	
	public String getDefaultName() {
		return this.c.getName();
	}
}
