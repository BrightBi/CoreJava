package mock.object;

public class Cat {
	
	private String name;

	public Cat (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (null == o) {
			return false;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}
		Cat a = (Cat) o;
		return this.name.equals(a.getName());
	}
	// 重写了 equals 方法一定要重写 hashCode 方法
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
