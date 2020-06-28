package javaeight;

import java.util.Optional;

public class OptionalExample {

	public static void main(String[] args) {
		// 如果参数是 null，of() 方法会抛出 NullPointerException
		Optional<User> userOf = Optional.of(new User(1L, "Bright"));
		// 如果参数是 null，对 ofNullable() 方法没有影响
		Optional<User> userOfNullable = Optional.ofNullable(null);

		// isPresent() 用来检查 Optional 中是否有值（即 是否为 null）
		System.out.println("userOfNullable is:" + userOfNullable.isPresent() + "   userOf is:" + userOf.isPresent());

		// ifPresent() 也可以用来检查 Optional 中是否有值（即 是否为 null）
		userOf.ifPresent(u -> System.out.println("userOf is not null."));
		userOfNullable.ifPresent(u -> System.out.println("userOfNullable is not null."));

		// 如果值为 null，get() 会抛出异常 NoSuchElementException
		System.out.println(userOf.get());
		
		// orElse() 跟 orElseGet() 都会在包含 null 时候做参数中的操作。
		System.out.println(userOfNullable.orElse(new User(2L, "orElse")));
		System.out.println(userOfNullable.orElseGet(() -> new User(3L, "orElseGet")));
		
		// 在不包含 null 时候也会执行行参数中的 new 操作，而 orElseGet() 不会执行参数中的操作。
		System.out.println(userOf.orElse(new User(4L, "orElse")));
		System.out.println(userOf.orElseGet(() -> new User(5L, "orElseGet")));
		
		try {
			// orElseThrow() 在对象为空的时候抛出异常
			userOf.orElseThrow(() -> new IllegalArgumentException());
		} catch (IllegalArgumentException e) {
			
		}
		
		// map() 会返回一个用 Optional 包裹的对象
		Optional<Long> optionalId = userOf.map(u -> u.getId());
		System.out.println(optionalId.get());
		
		// flatMap() 要求入参 Function 必须返回 Optional 包裹的对象
		Optional<String> name = userOf.flatMap(u -> u.getName());
		System.out.println(name.get());
		
		// filter() 过滤器
		Optional<User> filter = userOf.filter(u -> u.getId() > 10);
		System.out.println(filter.isPresent());
		filter = userOf.filter(u -> u.getId() == 1);
		System.out.println(filter.isPresent());
	}

}

class User {
	private Long id;
	private String name;

	public User(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		System.out.println("New " + id + " | " + name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
