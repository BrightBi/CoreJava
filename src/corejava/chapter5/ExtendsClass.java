package corejava.chapter5;

public class ExtendsClass {
	
	public static void main (String [] args) {

		Person [] persons = null;
		Student [] students = {new Student(), new Student()};
		// 子类数组的引用可以转换成父类的数组引用而无需采用强制类型转换
		persons = students;
		Person personS = new Student();
		Person personP = new Person();
		// 将子类的对象放入父类数组是可以的
		persons[1] = personS;
		try {
			// 此处有潜在风险，students 也引用了这个数组，代码运行时候会报异常 java.lang.ArrayStoreException
			persons[1] = personP;
		} catch (ArrayStoreException e) {
			e.printStackTrace();
		}
		
		// 超类转子类应该用 instanceof 检测
		if (personS instanceof Student) {
			Student s = (Student)personS;
			s.getAge();
		}
		
		// Object 是所有类的祖先。除了基本类型（数值 字符 布尔）
		// 所有数组类型也是 Object 的子类
		Object o = persons;
		// getClass 获取对象所属类
		System.out.println(o.getClass());
		System.out.println(personS.getClass());
		
		MyFinal myFinal = new MyFinal();
		System.out.println(myFinal.detail());
		myFinal.setP("33");
		MyFinal.S = "SS";
		// MyFinal.FS = "SS";   // final 域不能被赋值
		System.out.println(myFinal.detail());
		
		// Animal animal = new Animal();   // 抽象类不能被实例化，但是可以定义抽象类的实例变量
		Animal animal = new Cat();
		System.out.println(animal.getName());
	}
}
