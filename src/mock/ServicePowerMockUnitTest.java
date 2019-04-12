package mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import mock.object.Cat;
import mock.object.House;
import mock.object.Service;
import mock.object.Tool;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Tool.class, Service.class })
@PowerMockIgnore("javax.management.*")
public class ServicePowerMockUnitTest {

	@Mock
	private Tool tool;

	@Test
	public void testAnnotationInject() {
		showClassLoader("testAnnotationInject");
		String name = "name";
		Cat cat = new Cat("new");
		// 如果使用 @Mock 就不用写下面这种手动 mock
		// Tool tool = PowerMockito.mock(Tool.class);
		PowerMockito.when(tool.getCat(name)).thenReturn(cat);
		Service service = new Service();
		service.setTool(tool);
		Cat result = service.getCatByName(name);
		Assert.assertEquals(cat, result);
	}

	@Test
	public void testNormalPowerMock() {
		showClassLoader("testNormalPowerMock");
		Cat cat = new Cat("new");
		// 没有使用 @Mock 要写下面这种手动 mock
		Tool tool = PowerMockito.mock(Tool.class);
		PowerMockito.when(tool.getCat("tool")).thenReturn(cat);
		Service service = new Service();
		service.setTool(tool);
		Cat result = service.getCatByName("tool");
		Assert.assertEquals(cat, result);
	}

	@Test
	public void testPowerMockExceptionWithoutReturn() throws Exception {
		showClassLoader("testPowerMockExceptionWithoutReturn");
		PowerMockito.doThrow(new Exception("mock exception")).when(tool).aboutException(any(Cat.class));
		Service service = new Service();
		service.setTool(tool);
		String value = service.testException("s");
		Assert.assertEquals("Exception happen", value);
	}

	@Test
	public void testPowerMockExceptionWithReturn() throws Exception {
		showClassLoader("testPowerMockExceptionWithReturn");
		PowerMockito.when(tool.aboutExceptionWithReturn(any(Cat.class))).thenThrow(new Exception("mock exception"));
		Service service = new Service();
		service.setTool(tool);
		String value = service.testExceptionWithReturn("s");
		Assert.assertEquals("Exception happen", value);
	}

	@Test
	public void testPowerMockVoid() throws Exception {
		showClassLoader("testPowerMockVoid");
		PowerMockito.doNothing().when(tool).updateCatName("");
		Service service = new Service();
		service.setTool(tool);
		service.updateCatNameByTool("");
		verify(tool, times(1)).updateCatName(any());
	}

	@Test
	public void testPowerMockStatic() {
		showClassLoader("testPowerMockStatic");
		String result = "mock";
		PowerMockito.mockStatic(Tool.class);
		PowerMockito.when(Tool.getStaticValue("tool")).thenReturn(result);
		Service service = new Service();
		service.setTool(tool);
		String value = service.getStaticValue("tool");
		Assert.assertEquals(result, value);
	}

	@Test
	public void testPowerMockPrivate() throws Exception {
		showClassLoader("testPowerMockPrivate");
		PowerMockito.when(tool.randum()).thenReturn(500);
		Service service = PowerMockito.spy(new Service());
		service.setTool(tool);
		PowerMockito.when(service, "verifyMod", 100, 500).thenReturn(true);
		boolean value = service.isExist(100);
		Assert.assertTrue(value);
	}

	@Test
	public void testPowerPrivate() throws Exception {
		showClassLoader("testPowerPrivate");
		String s = "formate: null 3 RS";
		House house = PowerMockito.mock(House.class);
		Method method = PowerMockito.method(House.class, "formateName", int.class, String.class);
		String value = (String) method.invoke(house, 3, "RS");
		Assert.assertEquals(s, value);
	}

	@Test
	public void testPowerMockNew() throws Exception {
		showClassLoader("testPowerMockNew");
		House h = new House();
		h.setName("my name");
		PowerMockito.whenNew(House.class).withNoArguments().thenReturn(h);
		Service service = new Service();
		House value = service.getHouse();
		System.out.println("value: " + value);
		Assert.assertEquals("my name", value.getName());
	}

	private void showClassLoader(String methodName) {
		System.out.println("==============" + methodName + "===============");
		System.out.println("Service: " + Service.class.getClassLoader());
		System.out.println("Cat: " + Cat.class.getClassLoader());
		System.out.println("Tool: " + Tool.class.getClassLoader());
	}
}
