package thinkjava.chapter6;
/*
 *  一个 .java 文件可以看作一个编译单元
 *  在这个编译单元中最多只能有一个 public class
 *  public class 名字必须和文件名相同（如果一个编译单元中没有 public class 也是可以的）
 *  如果这个编译单元中还有其他 class，那么这些 class 在该包外是不可见的(即 不可用的)
 *  其他非 public class 是用来服务于 public class 的（如果没有 public class 就服务于该包）
 *  
 *  private ：私有
 *  默认 ：同包
 *  protected ：同包及子类
 *  public ： 所有
 *  
 *  如果一个类的所有构造器被定义成 private 那么会阻止该类被继承
 */
 public class AboutPackage {

	public static void main(String[] args) {
		System.out.println(new Cat().age);
	}
}

class Cat {
	int age = 1;
}