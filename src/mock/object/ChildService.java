package mock.object;

public class ChildService extends Service {

	public String getSelfHouseName(House house) {
		super.init(house);
		return house.getName();
	}
}
