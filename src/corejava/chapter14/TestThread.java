package corejava.chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 线程的六个状态：新生   可运行   阻塞   等待   计时等待   终止（调用 Thread 的 getState 方法可获得线程的当前状态）
 * 线程优先级：默认继承父类的优先级（不要过度依赖优先级，对应不同系统平台，优先级可能不一样，甚至不起作用）
 * 
 * 当在一个被阻塞的线程（调用了 sleep 或 wait）上调用 interrupt 方法时，阻塞调用会被 InterruptedException 中断
 * 在一个 interrupted 线程上调用 sleep 方法，它不会休眠而会清除该线程的中断状态并抛出 InterruptedException
 * interrupted 是静态方法，用来检查当前线程是否被中断，这个方法会清除该线程的中断状态
 * isInterrupted 是实例方法，用来检查当前线程是否被中断，这个方法不会清除该线程的中断状态
 * 
 * 守护线程：为其他线程服务，比如提供报时功能。当只剩下守护线程时，虚拟机就会退出。
 * 守护线程不应访问固有资源，因为它可能在任意时间中断。
 * 通过 Thread 的 setDaemon 将一个线程设置成守护线程
 * 
 * java.util.concurrent 下包含了许多用于编写并发程序的类
 * 
 * 线程挂起时可以按 Contrl + \ 会得到一个所有线程的列表
 */
public class TestThread {
	
	/*
	 * volatile 修饰的变量，当一条线程修改了变量的值，新值对于其他线程来说是可以立即得知的
	 * volatile 不保证原子性，使用 volatile 来处理线程同步是有条件的：
	 * 1 对变量的写操作不依赖当前值，如多线程下执行a++，是无法通过volatile保证结果准确性的;
	 * 2 该变量没有包含在具有其它变量的不变式中
	 *  （对于不变式的定义不是很清楚，从例子来看，定义了 private volatile int lower = 0，value 是另一个变量
	 *    如果其他地方使用了 诸如 value < lower value != lower，那么就是说lower 包含在了含有其他变量的不变式）
	 */
	private static volatile boolean change = true;
	private static volatile boolean print = true;

	public static void main(String[] args) throws InterruptedException {
		conditionLock ();
	}
	
	public static void conditionLock () throws InterruptedException {

		StoreNum first = new StoreNum();
		StoreNum last = new StoreNum();
		OutPutWithCondition outPutWithCondition = new OutPutWithCondition();
		first.setNum(9);
		last.setNum(1);
		Thread threadPrint = new Thread(() -> {
			while (print) {
				outPutWithCondition.outPut(first, last);
			}
		});
		Thread threadChange = new Thread(() -> {
			while (change) {
				outPutWithCondition.changeValue(first, last);
			}
		});
		threadPrint.start();
		threadChange.start();
	}
	
	public static void normal () throws InterruptedException {
		new Thread(new MyThread()).start();
		Thread thread = new Thread(new MyRunnable());
		thread.start();// 启动线程需要调用 start 方法，不能调用 run 方法
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
			e.printStackTrace();
		}
		System.out.println("MyThread");
	}
}

// 使用 java.util.concurrent.locks.Lock 实现锁
class OutPut {
	/*
	 * 对于同一个 OutPut 实例对象，每个线程争夺的同一个锁，会出现阻塞
	 * 对于不同的 OutPut 实例对象，每个线程得到不通的锁对象，两个线程不会发生阻塞
	 * 
	 * 锁是可以重入的，线程可以重复的获得已经持有的锁，锁会对此进行计数,
	 * outPut(char c) 在 lock-1 处获得锁，计数器为 1；
	 * outPutSeparatorLines-1 处调用 outPutSeparatorLines()，在lock-2 处再次获得锁，计数器为 2；
	 * outPutSeparatorLines-1 处的 outPutSeparatorLines() 执行完成，在 unlock-2 处释放锁，计数器为 1；
	 * outPutSeparatorLines-2 处调用 outPutSeparatorLines()，在lock-2 处再次获得锁，计数器为 2；
	 * outPutSeparatorLines-2 处的 outPutSeparatorLines() 执行完成，在 unlock-2 处释放锁，计数器为 1；
	 * outPut(char c) 在 unlock-1 处释放锁，计数器为 0；
	 * 此后其他线程可以再次获得锁来执行 outPut(char c)
	 */
	private Lock printLock = new ReentrantLock();
	private Object clientLock = new Object();
	
	public void outPut(char c) {
		
		printLock.lock(); // lock-1
		try {
			outPutSeparatorLines(); // outPutSeparatorLines-1
			System.out.print(c);
			System.out.print(c);
			System.out.print(c);
			System.out.println();
			outPutSeparatorLines(); // outPutSeparatorLines-2
		} finally {
			// 必须在 finally 中将拿到的锁释放掉，否则线程异常终止，
			// 加在上面的锁将不会释放，其他线程永远无法得到锁
			printLock.unlock(); // unlock-1
		}
	}
	
	public void outPutSeparatorLines() {
		
		printLock.lock(); // lock-2
		try {
			System.out.print("===");
			System.out.print("***");
			System.out.print("===");
			System.out.println();
		} finally {
			printLock.unlock(); // unlock-2
		}
	}
	
	// synchronized 利用对象的内部锁实现线程同步
	public synchronized void outPutSeparatorLines(String s) {
		System.out.print("===");
		System.out.print(s);
		System.out.print("===");
		System.out.println();
	}
	
	// synchronized 利用第三方对象的内部锁实现线程同步
	public void outPutSeparatorLines(String s, String t) {
		synchronized (clientLock) { // 对于 clientLock 对象，仅使用他的对象锁
			System.out.print(t);
			System.out.print(s);
			System.out.print(t);
			System.out.println();
		}
	}
}

//使用 java.util.concurrent.locks.Lock 实现带条件锁
class OutPutWithCondition {

	private Lock printLock = new ReentrantLock();
	private Condition c = printLock.newCondition();
	
	public void outPut(StoreNum first, StoreNum last) {
		
		printLock.lock();
		try {
			while (first.getNum() > last.getNum()) {
				System.out.println(Thread.currentThread().getName() + " is waiting for :" + first.getNum()  + " | " + last.getNum());
				// 调用了 Condition.await() 一定要有 Condition.signalAll() 去唤醒其他线程，不然容易导致全部线程都阻塞
				c.await();
			}
			System.out.print(last.getNum());
			System.out.print(" - ");
			System.out.print(first.getNum());
			System.out.print(" = " + (last.getNum() - first.getNum()));
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			printLock.unlock();
		}
	}
	
	public void changeValue (StoreNum first, StoreNum last) {
		printLock.lock();
		try {
			StoreNum t = new StoreNum();
			t.setNum(last.getNum());
			last.setNum(first.getNum());;
			first.setNum(t.getNum());
			System.out.println(Thread.currentThread().getName() + " call all." + " New value :" + first.getNum()  + " | " + last.getNum());
			c.signalAll();
		} finally {
			printLock.unlock();
		}
	}
}

class StoreNum {
	private int Num;

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}
}