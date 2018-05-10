package corejava.chapter5;
/*
 * Java 的反射，支持在运行过程中分析类，和对象
 */
public class Reflection {

	public static void main(String[] args) {
		String className = "corejava.chapter5.Chapter";
		// Class 类维护了运行时对象所属类型的相关信息
		Chapter chapter = new Chapter();
		Class<?> c = chapter.getClass();
		System.out.println("getClass() " + c.getName());
		c.getClass();
		// 虚拟机为每个类型管理一个 Class 对象，所以可以用 == 判断
		if (chapter.getClass() == Chapter.class) {
			System.out.println("chapter is " + Chapter.class.getName());
		}
		
		c = int.class;
		System.out.println("int.class " + c.getName());
		
		c = Chapter.class;
		System.out.println("Chapter.class " + c.getName());
		
		try {
			c = Class.forName(className);
			System.out.println("Class.forName('corejava.chapter5.Chapter') " + c.getName());
			Object o = Class.forName(className).newInstance();
			System.out.println(o.getClass().getName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}

class Chapter {
	private String title = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}