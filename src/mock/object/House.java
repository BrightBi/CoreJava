package mock.object;

public class House {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameDetail () {
		return formateName(9, "CN");
	}
	
	private String formateName(int number, String locale) {
		return "formate: " + this.name + " " + number + " " + locale;
	}
}
