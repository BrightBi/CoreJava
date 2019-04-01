package mock.power;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import mock.Cat;
import mock.Service;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Service.class})
@PowerMockIgnore("javax.management.*")
public class ServicePowerMockUnitTest {

	@Test
	public void testTimesIgnoreParameters() {
		String name = "name";
		Cat cat = new Cat("new");
		Service service = PowerMockito.mock(Service.class);
		PowerMockito.when(service.getCatByName(name)).thenReturn(cat);
		// can not stub like this
		//PowerMockito.doReturn(cat).when(service.getCatByName(name));
		Cat result = service.getCatByName(name);
		Assert.assertEquals(cat, result);
	}
}
