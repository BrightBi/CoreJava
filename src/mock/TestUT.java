package mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import mock.object.Service;
import mock.object.Tool;
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Tool.class, Service.class })
@PowerMockIgnore("javax.management.*")
public class TestUT {
	
	@Mock
	private Tool tool;
	
	
	@Test
	public void testPowerMockStatic() {
		String result = "mock";
		PowerMockito.mockStatic(Tool.class);
		PowerMockito.when(Tool.getStaticValue("tool")).thenReturn(result);
		Service service = new Service();
		service.setTool(tool);
		String value = service.getStaticValue("tool");
		Assert.assertEquals(result, value);
	}
}
