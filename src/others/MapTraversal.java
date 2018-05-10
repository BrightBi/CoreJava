package others;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTraversal {

	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("key", "value");
		
		// 第一种:
		// 效率高,以后一定要使用此种方式！
		Iterator<Map.Entry<String, Object>> iter1 = map.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry<String, Object> entry = iter1.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			System.out.println(key + val);
		}
		
		// 第二种:
		// 效率低,以后尽量少使用！
		Iterator<String> iter2 = map.keySet().iterator();
		while (iter2.hasNext()) {
			String key = iter2.next();
			Object val = map.get(key);
			System.out.println(key + val);
		}
		
		// 第三种:
		Collection<Object> c = map.values();
		Iterator<Object> it = c.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

}
