package others.runtime.clazz;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Java 在运行时生成 class
 * https://www.liaoxuefeng.com/article/1080190250181920
 *
 */
public class CreateRuntimeClass {
	private static final String SINGLE_JAVA = "/* a single java class to one file */  "
		+ "package others.runtime.clazz;                                            "
		+ "import others.runtime.clazz.*;                            "
		+ "public class UserProxy extends User implements BeanProxy {     "
		+ "    boolean _dirty = false;                                    "
		+ "    public void setId(String id) {                             "
		+ "        super.setId(id);                                       "
		+ "        setDirty(true);                                        "
		+ "    }                                                          "
		+ "    public void setName(String name) {                         "
		+ "        super.setName(name);                                   "
		+ "        setDirty(true);                                        "
		+ "    }                                                          "
		+ "    public void setCreated(long created) {                     "
		+ "        super.setCreated(created);                             "
		+ "        setDirty(true);                                        "
		+ "    }                                                          "
		+ "    public void setDirty(boolean dirty) {                      "
		+ "        this._dirty = dirty;                                   "
		+ "    }                                                          "
		+ "    public boolean isDirty() {                                 "
		+ "        return this._dirty;                                    "
		+ "    }                                                          "
		+ "}                                                              ";
	static final String MULTIPLE_JAVA = "/* a single class to many files */   "
			+ "package others.runtime.clazz;                                            "
			+ "import java.util.*;                                            "
			+ "public class Multiple {                                        "
			+ "    List<Bird> list = new ArrayList<Bird>();                   "
			+ "    public void add(String name) {                             "
			+ "        Bird bird = new Bird();                                "
			+ "        bird.name = name;                                      "
			+ "        this.list.add(bird);                                   "
			+ "    }                                                          "
			+ "    public Bird getFirstBird() {                               "
			+ "        return this.list.get(0);                               "
			+ "    }                                                          "
			+ "    public static class StaticBird {                           "
			+ "        public int weight = 100;                               "
			+ "    }                                                          "
			+ "    class NestedBird {                                         "
			+ "        NestedBird() {                                         "
			+ "            System.out.println(list.size() + \" birds...\");   "
			+ "        }                                                      "
			+ "    }                                                          "
			+ "}                                                              "
			+ "/* package level */                                            "
			+ "class Bird {                                                   "
			+ "    String name = null;                                        "
			+ "}                                                              ";

	public static void main(String[] args) throws Exception {
		JavaStringCompiler compiler = new JavaStringCompiler();
		Map<String, byte[]> results = compiler.compile("UserProxy.java", SINGLE_JAVA);
		System.out.println("results.size():" + results.size());
		System.out.println("results.size():" + results.containsKey("others.runtime.clazz.UserProxy"));
		Class<?> clazz = compiler.loadClass("others.runtime.clazz.UserProxy", results);
		// get method:
		Method setId = clazz.getMethod("setId", String.class);
		Method setName = clazz.getMethod("setName", String.class);
		Method setCreated = clazz.getMethod("setCreated", long.class);
		// try instance:
		Object obj = clazz.newInstance();
		// get as proxy:
		BeanProxy proxy = (BeanProxy) obj;
		System.out.println("proxy.isDirty():" + proxy.isDirty());
		// set:
		setId.invoke(obj, "A-123");
		setName.invoke(obj, "Fly");
		setCreated.invoke(obj, 123000999);
		// get as user:
		User user = (User) obj;
		System.out.println("user.getId():" + user.getId());
		System.out.println("user.getName():" + user.getName());
		System.out.println("user.getCreated():" + user.getCreated());
		System.out.println("proxy.isDirty():" + proxy.isDirty());
		
		testCompileMultipleClasses();
	}
	
	public static void testCompileMultipleClasses() throws Exception {
		JavaStringCompiler compiler = new JavaStringCompiler();
		Map<String, byte[]> results = compiler.compile("Multiple.java", MULTIPLE_JAVA);
		System.out.println("results.size():" + results.size());
		System.out.println("keySet:" + results.keySet());
		Class<?> clzMul = compiler.loadClass("others.runtime.clazz.Multiple", results);
		// try instance:
		Object obj = clzMul.newInstance();
		System.out.println("obj:" + obj);
	}
}
