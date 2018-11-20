package thinkjava.chapter5;

/*
 * 对象可能不被垃圾回收（内存不濒临用尽情况下，垃圾回收器是不会去回收垃圾的）
 * 垃圾回收不等于析构
 * 垃圾回收只应与内存有关
 * 
 * 垃圾回收的几种模式：
 * 引用计数：有新的引用时候计数器 +1，引用不再指向或者置为 null 计数器 —1
 * 停止复制：程序先停止，然后从静态区和堆栈出发将对象复制到另一块堆内存
 * 标记清扫：程序先停止，然后从静态区和堆栈出发将遍历到的对象进行标记，全部标记后再执行清理
 */
public class Recycle {

	public static void main(String[] args) {
		for (int i = 0; i<1000; i++) {
			new Rubbish();
		}
		System.gc(); // 强制垃圾回收
		System.out.println("Finish");
	}
}

class Rubbish {

	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize");
		super.finalize();
	}
}
