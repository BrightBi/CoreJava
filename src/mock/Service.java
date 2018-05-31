package mock;

public class Service {
	private Cat cat = null;
	private Tool tool = null;

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat me) {
		this.cat = me;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void saveCatByTool(String name) {
		Cat cat = new Cat(name);
		tool.saveCat(cat);
	}
	
	public String getToolDefaultCatName() {
		return tool.getDefaultName();
	}
	
	public void testException(String name) throws Exception{
		Cat me = new Cat(name);
		tool.aboutException(me);
	}
}