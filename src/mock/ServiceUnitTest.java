package mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.support.membermodification.MemberModifier;

public class ServiceUnitTest {

	@Test
    public void testTimesIgnoreParameters() {
		Tool tool = Mockito.mock(Tool.class);
		Service service = new Service();
		service.setTool(tool);
		service.saveCatByTool("name");
		
		// 不关心 Tool.saveCatByTool() 的入参，使用 any()
		// 校验 Tool.saveCatByTool() 被调用一次
		verify(tool, times(1)).saveCat(any());
    }

	@Test
    public void testTimesParametersClass() {
		Tool tool = Mockito.mock(Tool.class);
		Service service = new Service();
		service.setTool(tool);
		service.saveCatByTool("name");
		
		// 不关心 Tool.saveCatByTool() 的入参，但是要求入参必须是指定的类型，使用 any(Cat.class)
		// 校验 Tool.saveCatByTool() 使用指定入参类型被调用一次
		verify(tool, times(1)).saveCat(any(Cat.class));
	}
	
	@Test
    public void testTimesPreciseParameters() {
		Tool tool = Mockito.mock(Tool.class);
		Service service = new Service();
		service.setTool(tool);
		service.saveCatByTool("name");
		
		// 校验 Tool.saveCatByTool() 使用指定参数被调用一次
		// 入参如果是对象，一定要重写了 equals 和 hashcode 方法，不然报错
		Cat c = new Cat("name");
		verify(tool, times(1)).saveCat(c);
	}
	
	@Test
    public void testPrivateField() throws Exception {
		Cat c = new Cat("Test Default");
		Tool tool = new Tool();
		// 使用 MemberModifier 调整私有成员
		MemberModifier.field(Tool.class, "c").set(tool, c);
		Service service = new Service();
		service.setTool(tool);
		assertEquals(c.getName(), service.getToolDefaultCatName());
	}
	
	@Test
    public void testException() throws Exception {
		Tool tool = Mockito.mock(Tool.class);
		Service service = new Service();
		service.setTool(tool);
		
		// 使用 doThrow 时候，被 mock 的方法一定要是带有 throws Exception 的，不然报错
		
		// 模拟 tool 调用 aboutException 参数为 Cat("name") 时候抛出 Exception("error") 异常
		doThrow(new Exception("mock error")).when(tool).aboutException(new Cat("name"));
		try {
			// 使用参数 "name" 模拟抛出异常
			service.testException("name");
			System.out.println("No exception.");
		} catch (Exception e) {
			System.out.println("Exception happen. Message:" + e.getMessage());
		}
		try {
			// 使用参数 "n" 模拟不抛出异常
			service.testException("n");
			System.out.println("No exception.");
		} catch (Exception e) {
			System.out.println("Exception happen. Message:" + e.getMessage());
		}
	}
}
