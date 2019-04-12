package mock.object;

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

	public void updateCatNameByTool(String name) {
		tool.updateCatName(name);
	}

	public String getToolDefaultCatName() {
		return tool.getDefaultName();
	}

	public String testException(String name) {
		Cat me = new Cat(name);
		String s = "No exception";
		try {
			tool.aboutException(me);
		} catch (Exception e) {
			s = "Exception happen";
		}
		return s;
	}
	
	public String testExceptionWithReturn(String name) {
		Cat me = new Cat(name);
		String s = "No exception";
		try {
			s = tool.aboutExceptionWithReturn(me);
		} catch (Exception e) {
			s = "Exception happen";
		}
		return s;
	}

	public String testException() throws Exception {
		Cat me = new Cat("Cat");
		tool.aboutException(me);
		return "";
	}

	public void testArgs(int a, int b, int c) {
		tool.sum(a, b, c);
	}

	public Cat getCatByName(String name) {
		Cat cat = tool.getCat(name);
		return cat;
	}
	
	public String getStaticValue(String name) {
		String str = Tool.getStaticValue(name);
		return str;
	}
	
	public boolean isExist(int id) {
		int catId = tool.randum();
		return verifyMod(id, catId);
	}
	
	public House getHouse() {
		return new House();
	}

	private boolean verifyMod(int id, int catId) {
		if (id <= catId) {
			return true;
		} else {
			return false;
		}
	}
}