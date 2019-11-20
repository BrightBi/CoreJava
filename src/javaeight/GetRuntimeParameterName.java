package javaeight;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
/**
 * Method.getParameters 为 1.8新增方法，可以获取参数信息，包括参数名称。
 * 参数名称被编译进了 class 文件，替代了早期版本里无意义的 arg0、arg1 ...
 * 保留参数名这一选项由编译开关javac -parameters打开，默认是关闭的。
 * 在 Eclipse 中，可以通过 Properties - Java Compiler - Store information about method ... 选项打开
 */
public class GetRuntimeParameterName {
	public void createUser(String name, int age, int version) {
		//
	}

	public static void main(String[] args) throws Exception {
		for (Method m : GetRuntimeParameterName.class.getMethods()) {
			System.out.println("----------------------------------------");
			System.out.println("   method: " + m.getName());
			System.out.println("   return: " + m.getReturnType().getName());
			for (Parameter p : m.getParameters()) {
				System.out.println("parameter: " + p.getType().getName() + ", " + p.getName());
			}
		}
	}
}
