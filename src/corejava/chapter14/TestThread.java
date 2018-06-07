package corejava.chapter14;

public class TestThread {

	public static void main(String[] args) throws InterruptedException {
		new Thread(new MyThread()).start();
		Thread thread = new Thread(new MyRunnable());
		thread.start();
		thread.join(); // 此处表示 thread 线程加入进来，main 线程会在 thread 执行完以后继续执行
		System.out.println("TestThread");
	}
}

// 要想成为线程 一定要实现 Runnable 接口
class MyRunnable implements Runnable{
	@Override
	public void run() {
		System.out.println("MyRunnable");
	}
}

// Thread 实现了 Runnable 接口
class MyThread extends Thread{
	@Override
	public void run() {
		try {
			sleep(2000);// 线程休眠 2000 毫秒，sleep() 是 Thread 的方法
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("MyThread");
	}
}
