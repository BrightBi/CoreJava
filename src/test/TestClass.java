package test;

import java.util.Optional;

public class TestClass {

	public static void main(String[] args) {

		FileOne f1 = FileOne.INITIALIZED;
		FileOne f2 = FileOne.INITIALIZED;
		
		System.out.println(f1 == f2);

	}
	
	public static void f () {
		Result result1 = new Result(true);
		Result result2 = new Result(false);
		Result result3 = new Result(true);
		Result result4 = new Result(false);

		Optional.ofNullable(result1).filter(Result::isFlag).map(r -> {
			r.setDetail("detail1");
			return r;
		}).orElse(result1).setReason("reason1");

		Optional.ofNullable(result2).filter(Result::isFlag).map(r -> {
			r.setDetail("detail2");
			return r;
		}).orElse(result2).setReason("reason2");

		Optional.ofNullable(result3).filter(Result::isFlag).map(r -> {
			r.setDetail("detail3");
			return r;
		}).filter(r -> !r.isFlag()).map(r -> {
			r.setReason("reason3");
			return r;
		});

		Optional.ofNullable(result4).filter(Result::isFlag).map(r -> {
			r.setDetail("detail3");
			return r;
		}).filter(r -> !r.isFlag()).map(r -> {
			r.setReason("reason3");
			return r;
		});

		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
	}

}

enum FileOne {
	INITIALIZED(false, false, false, false, false),

	UPLOADED(false, false, true, false, false),

	SCANING(false, false, false, false, false),

	SCAN_FAILED(false, false, true, false, false);

	private boolean available;
	private boolean unavailable;
	private boolean scannable;
	private boolean encryptable;
	private boolean replaceable;

	private FileOne(boolean available, boolean unavailable, boolean scannable, boolean encryptable,
			boolean replaceable) {
		this.available = available;
		this.unavailable = unavailable;
		this.scannable = scannable;
		this.encryptable = encryptable;
		this.replaceable = replaceable;
	}
}

enum FileTwo {
	INITIALIZED(false, false, false, false, false),

	UPLOADED(false, false, true, false, false),

	SCANING(false, false, false, false, false),

	COMPLETED(false, false, true, false, false);

	private boolean available;
	private boolean unavailable;
	private boolean scannable;
	private boolean encryptable;
	private boolean replaceable;

	private FileTwo(boolean available, boolean unavailable, boolean scannable, boolean encryptable,
			boolean replaceable) {
		this.available = available;
		this.unavailable = unavailable;
		this.scannable = scannable;
		this.encryptable = encryptable;
		this.replaceable = replaceable;
	}
}

class Result {
	private boolean flag;
	private String reason;
	private String detail;

	public Result(boolean flag) {
		super();
		this.flag = flag;
	}

	public Result(boolean flag, String reason, String detail) {
		super();
		this.flag = flag;
		this.reason = reason;
		this.detail = detail;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Result [flag=" + flag + ", reason=" + reason + ", detail=" + detail + "]";
	}

}
