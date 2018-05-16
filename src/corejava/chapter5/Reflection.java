package corejava.chapter5;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
		
		try {
			printClass("corejava.chapter5.Chapter");
			valueGetSetByReflection();
			callMethodByReflect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int [] a = {1, 2};
		// Object [] o = a; 是不对的。int [] 数组不能转换成对象数组，但可以转换成对象
		Object o = a;
		System.out.println("Object o: " + o);
	}
	
	public static void callMethodByReflect() throws Exception {
		Chapter chapter = new Chapter();
		Class<?> c = Chapter.class;
		// getDeclaredMethod() 查找指定方法名和参数的那个 Method
		Method m = c.getDeclaredMethod("setTitle", String.class);
		// 对于实例方法，调用时候要指定具体哪个实例和入参
		m.invoke(chapter, "method");
		System.out.println("Method Title: " + chapter.getTitle());
		// getDeclaredMethod() 查找指定方法名且无参的那个 Method
		m = c.getDeclaredMethod("getChapterId");
		// 对于静态方法，调用时候不需要指定具体哪个实例
		String id = (String) m.invoke(null);
		System.out.println("Method id: " + id);
	}
	
	public static void valueGetSetByReflection() throws Exception {
		Chapter chapter = new Chapter();
		chapter.setTitle("myTitle");
		Class<?> c = chapter.getClass();
		Field f = c.getDeclaredField("title");
		// 检查 title 访问权限
		// 由于 Chapter 中的 title 是私有的，无法访问，所以这里要改变其访问权限。等价于 f.setAccessible(true);
		if (!f.isAccessible()) {
			AccessibleObject.setAccessible(new Field [] {f}, true);
		}
		Object o = f.get(chapter);
		System.out.println("Title: " + o.toString());
		f.set(chapter, "ourTitle");
		System.out.println("Title: " + chapter.getTitle());
	}
	
	public static void printClass(String className) throws Exception {
		Class<?> c = Class.forName(className);
		Class<?> superC = c.getSuperclass();
		// getModifiers() 返回的是个 int 需要使用 Modifier.toString() 转成对应的 public private ...
		String modifiers = Modifier.toString(c.getModifiers());
		if (modifiers.length() > 0) {
			System.out.print(modifiers + " ");
		}
		System.out.print("class " + className);
		if (superC != null && superC != Object.class) {
			System.out.print("extends " + superC.getName());
		}
		System.out.println(" {");
		printFields (c);
		System.out.println();
		printConstructors(c);
		System.out.println();
		printMethods(c);
		System.out.println("}");
	}
	
	public static void printMethods(Class<?> c) {
		// getMethods() 获取类的所有方法，包括通过继承得来的
		// getDeclaredMethods() 获取类中定义的方法，不包含继承来的方法
		Method [] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			String modifiers = Modifier.toString(method.getModifiers());
			Class<?> returnTypes = method.getReturnType();
			if (modifiers.length() > 0) {
				System.out.print("    " + modifiers + " " + returnTypes.getName() + " " + method.getName() + " (");
			} else {
				System.out.print("    " + returnTypes.getName() + " " + method.getName() + " (");
			}
			Class<?> [] parameterTypes = method.getParameterTypes();
			for (int i=0; i<parameterTypes.length; i++) {
				if (i > 0) {
					System.out.print(", " + parameterTypes[i].getName());
				} else {
					System.out.print(parameterTypes[i].getName());
				}
			}
			System.out.println(");");
		}
	}
	
	public static void printConstructors(Class<?> c) {
			Constructor<?> [] constrctors = c.getDeclaredConstructors();
			for (Constructor<?> constructor : constrctors) {
				String name = constructor.getName();
				String modifiers = Modifier.toString(constructor.getModifiers());
				System.out.print("    ");
				if (modifiers.length() > 0) {
					System.out.print(modifiers + " ");
				}
				System.out.print(name + " (");
				
				Class<?> [] parameterTypes = constructor.getParameterTypes();
				for (int i=0; i<parameterTypes.length; i++) {
					if (i > 0) {
						System.out.print(", " + parameterTypes[i].getName());
					} else {
						System.out.print(parameterTypes[i].getName());
					}
				}
				System.out.println(");");
			}
	}
	public static void printFields (Class<?> c) {
		Field [] fields = c.getDeclaredFields();
		for (Field field : fields) {
			Class<?> fieldType = field.getType();
			String modifiers = Modifier.toString(field.getModifiers());
			if (modifiers.length() > 0) {
				System.out.println("    " + modifiers + " " + fieldType.getName() + " " + field.getName() + ";");
			} else {
				System.out.println("    " + fieldType.getName() + " " + field.getName() + ";");
			}
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
	
	public static String getChapterId () {
		return "1001";
	}
	
	public static String getChapterId (String id) {
		return "1001-" + id;
	}
}