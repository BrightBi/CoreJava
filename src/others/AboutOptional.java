package others;

import java.util.Optional;

public class AboutOptional {

	public static void main(String[] args) throws Exception {
		// of("of");
		// of(null);
		//
		// ofNullable("ofNullable");
		// ofNullable(null);

		// ifPresent("ifPresent");
		// ifPresent(null);

		// orElse("orElse");
		// orElse(null);

		// orElseGet("orElseGet");
		// orElseGet(null);

		// orElseThrow("orElseThrow");
		// orElseThrow(null);

		// map("map");
		// map(null);

		// flatMap("flatMap");
		// flatMap(null);

		// filter("filter");
		// filter("filter-filter");
		// filter(null);

		// exampleOne(true, null);
		// exampleTwo(true, null);
		exampleThree(true, null);
	}

	public static User exampleThree(boolean useOptional, User user) throws Exception {
		if (useOptional) {
			if (user != null) {
				String name = user.getName();
				if ("Bi".equals(name)) {
					return user;
				} else {
					return new User("Bi", new Address("China", "Xian", "Detail"));
				}
			} else {
				return new User("Bi", new Address("China", "Xian", "Detail"));
			}
		} else {
			return Optional.ofNullable(user).filter(u -> "Bi".equals(u.getName()))
					.orElseGet(() -> new User("Bi", new Address("China", "Xian", "Detail")));
		}
	}

	public static void exampleTwo(boolean useOptional, User user) throws Exception {
		if (useOptional) {
			if (user != null) {
				System.out.println(user.getName());
			}
		} else {
			Optional.ofNullable(user).ifPresent(u -> System.out.println(u.getName()));
		}
	}

	public static String exampleOne(boolean useOptional, User user) throws Exception {
		if (useOptional) {
			if (user != null) {
				if (user.getAddress() != null) {
					Address address = user.getAddress();
					if (address.getCity() != null) {
						return address.getCity();
					}
				}
			}
			throw new Exception("Error");
		} else {
			return Optional.ofNullable(user).map(u -> u.getAddress()).map(a -> a.getCity())
					.orElseThrow(() -> new Exception("Error"));
		}
	}

	public static void filter(String s) throws Exception {
		String v = Optional.ofNullable(s).filter(o -> o.length() > 2).filter(o -> o.length() < 10).orElse("no value");
		System.out.println("Value:" + v);
	}

	public static void flatMap(String s) throws Exception {
		String v = Optional.ofNullable(s).flatMap(o -> Optional.of(new String("flatMap-1 " + o)))
				.flatMap(o -> Optional.of(new String("flatMap-2 " + o))).orElse("no flatMap");
		System.out.println("Value:" + v);
	}

	public static void map(String s) throws Exception {
		String v = Optional.ofNullable(s).map(o -> new String("map-1 " + o)).map(o -> new String("map-2 " + o))
				.orElse("no map");
		System.out.println("Value:" + v);
	}

	public static void orElseThrow(String s) throws Exception {
		String v = Optional.ofNullable(s).orElseThrow(() -> new Exception("NotFoundException"));
		System.out.println("Value:" + v);
	}

	public static void orElseGet(String s) {
		// 当 s 值不为 null 时，orElseGet 函数不会执行 lambda 表达式
		String v = Optional.ofNullable(s).orElseGet(() -> {
			System.out.println("orElseGet null");
			return new String("or");
		});
		System.out.println("Value:" + v);
	}

	public static void orElse(String s) {
		// 当 s 值不为 null 时，orElse 函数依然会执行 getString() 方法
		String v = Optional.ofNullable(s).orElse(getString("null"));
		System.out.println("Value:" + v);
	}

	public static void ifPresent(String s) {
		Optional.ofNullable(s).ifPresent(t -> System.out.println("Value:" + t));
	}

	public static void ofNullable(String s) {
		// Optional.ofNullable() 传入的参数可以为 null，如果为 null 的话，返回的就是 Optional.empty()
		Optional<String> o = Optional.ofNullable(s);
		if (o.isPresent()) {
			System.out.println("Value:" + o.get());
		} else {
			System.out.println("Value:null");
		}
	}

	public static void of(String s) {
		try {
			// Optional.of() 传入的参数一定不能为 null，否则便会抛出 NullPointerException
			Optional<String> o = Optional.of(s);
			System.out.println("Present:" + o.isPresent());
			System.out.println("Value:" + o.get());
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		}
	}

	public static String getString(String s) {
		System.out.println("getString " + s);
		return new String(s);
	}
}

class User {
	private String name;
	private Address address;

	public User(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}

class Address {
	private String country;
	private String city;
	private String detail;

	public Address(String country, String city, String detail) {
		super();
		this.country = country;
		this.city = city;
		this.detail = detail;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
