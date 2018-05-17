package corejava.chapter6;

/*
 *  接口中的所有域自动属于 public static final 关键字可以省略
 *  接口中所有方法自动属于 public abstract，关键字可以省略
 *  接口中可以定义常量，但是接口中不能有实例域，也不能在接口中实现方法
 *  接口可以继承多个接口，但接口不能实现接口
 *  一个类可以实现多个接口，但只能继承一个类
 *  接口不能使用 new 来实例化，但可以定义接口类型，这与抽象类很像
 *  接口中定义的内部类自动为 public static
 */
public interface Tool {

	String WEIGHT = "W";
	public static final String CATEGORY = "T"; // 此处 public static final 可以像上面一样省略
	String getToolName();
	public abstract void work(); // 此处 public 可以像上面一样省略
}

// 接口继承多个接口
interface DailyTool extends Tool, Comparable<DailyTool>{
	public void getOwner();
}

/*
//接口不能实现接口
interface SpecialTool implements Tool{
	public void getOwner();
}
*/

// 一个类可以实现多个接口
class Knife implements Tool, Comparable<Knife> {
	@Override
	public String getToolName() {
		return "Knife";
	}
	@Override
	public void work() {
	}
	@Override
	public int compareTo(Knife o) {
		return 0;
	}
}