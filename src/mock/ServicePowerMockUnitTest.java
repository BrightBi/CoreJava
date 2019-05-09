package mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import mock.object.Cat;
import mock.object.ChildService;
import mock.object.House;
import mock.object.Service;
import mock.object.Tool;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Tool.class, Service.class })
@PowerMockIgnore("javax.management.*")
public class ServicePowerMockUnitTest {

	@Mock
	private Tool tool;

	private List<House> nodes = new ArrayList<House>();

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
	@PrepareForTest(House.class)
	public void testPrivateByPowerMock1() throws Exception {
		String name = "PowerMockito formateName";
		House house = PowerMockito.mock(House.class);
		PowerMockito.when(house.getNameDetail()).thenCallRealMethod();
		PowerMockito.when(house, "formateName", 9, "CN").thenReturn(name);
		String value = house.getNameDetail();
		Assert.assertEquals(name, value);
	}

	@Test
	public void testPrivateByPowerMock() throws Exception {
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

	@Test
	public void testPowerMockStaticVoid() throws Exception {
		showClassLoader("testPowerMockStaticVoid");
		House h = new House();
		h.setName("my name");
		PowerMockito.mockStatic(Tool.class);
		PowerMockito.doNothing().when(Tool.class, "updateValue", h);

		Service service = new Service();
		House value = service.updateHouse(h);
		Assert.assertEquals("my name", value.getName());
	}

	@Test
	public void testPowerMockDifferReturn() throws Exception {
		showClassLoader("testPowerMockDifferReturn");
		// thenReturn(true, true, false); 表示第一次调用返回 true，第二次调用返回 true，第三次调用返回 false。
		PowerMockito.when(tool.validate()).thenReturn(true, true, false);
		Service service = new Service();
		service.setTool(tool);
		int value = service.validate();
		Assert.assertEquals(2, value);
	}

	@Test
	public void testPowerMockAnswer() throws Exception {
		showClassLoader("testPowerMockDifferReturn");
		House h = new House();
		h.setName("my name");
		nodes.add(h);
		PowerMockito.whenNew(ArrayList.class).withNoArguments().thenAnswer(new Answer<List<House>>() {
			@Override
			public List<House> answer(InvocationOnMock invocation) throws Throwable {
				return nodes;
			}
		});
		Service service = new Service();
		List<House> value = service.housList();
		Assert.assertEquals(2, value.size());
	}

	@Test
	public void testPowerMockFinal() throws Exception {
		showClassLoader("testPowerMockFinal");
		// final 与普通方法一样mock，但是需要将其所在 class 添加到 @PrepareForTest 注解中
		Tool tool = PowerMockito.mock(Tool.class);
		PowerMockito.when(tool.isFinal()).thenReturn(false);
		Service service = new Service();
		service.setTool(tool);
		boolean result = service.isFinal();
		Assert.assertEquals(false, result);
	}

	@Test
	public void testPowerMockSuppress() throws Exception {
		ChildService cs = PowerMockito.spy(new ChildService());
		PowerMockito.suppress(PowerMockito.method(Service.class, "init", House.class));
		House house = new House();
		house.setName("ChildService");
		String value = cs.getSelfHouseName(house);
		Assert.assertEquals("ChildService", value);
	}

	@Test
	public void testPowerMockCareful() throws Exception {
		Service s = PowerMockito.spy(new Service());
		List<House> list = new ArrayList<House>();
		list.add(new House());
		list.add(new House());
		// 也可以使用这种方式，都可以
		// PowerMockito.when(s.getChildren(Matchers.anyInt())).thenReturn(list);
		PowerMockito.doReturn(list).when(s).getChildren(Matchers.anyInt());
		List<House> result = new ArrayList<>();
		s.getAllChildren(9, result);
		Assert.assertEquals(2, result.size());
	}

	@Test
	public void testPowerMockLotParameters() throws Exception {
		// 如果对某一个参数使用了Matcher，那么，这个方法的所有其他参数也必须使用Matcher，否则将会报错。
		PowerMockito.doReturn(true).when(tool).manyParameters(Matchers.anyInt(), Matchers.anyString(),
				Matchers.any(House.class));
		Service s = new Service();
		s.setTool(tool);
		boolean value = s.manyParameters(1, "", new House());
		Assert.assertEquals(true, value);
	}

	private void showClassLoader(String methodName) {
		System.out.println("==============" + methodName + "===============");
		System.out.println("Service: " + Service.class.getClassLoader());
		System.out.println("Cat: " + Cat.class.getClassLoader());
		System.out.println("Tool: " + Tool.class.getClassLoader());
	}
}
