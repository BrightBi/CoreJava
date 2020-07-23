package test;

import java.io.IOException;

public class TestFile {

	private static ThreadLocal<Integer> initalue = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue () {
			return new Integer(10);
		}
	};

	public static void main(String[] args) throws IOException {
		new Thread("t1") {
			@Override
			public void run () {
				update();
			}
		}.start();
		new Thread("t2") {
			@Override
			public void run () {
				update();
			}
		}.start();
		new Thread("t3") {
			@Override
			public void run () {
				update();
			}
		}.start();
	}

	public static void update() {
		initalue.set(initalue.get() + 66);
		System.out.println(initalue.get());
	}
}
