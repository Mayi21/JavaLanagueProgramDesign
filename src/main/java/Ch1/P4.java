package Ch1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author xaohii
 * @Date 2022/4/21 14:39
 */

// 测试线程的
public class P4 {
	private static final int count =20;
	private static CountDownLatch countDownLatch = new CountDownLatch(count);
	public static AtomicInteger race = new AtomicInteger(0);
	public static synchronized void increase(){
		race.getAndIncrement();
	}

	public static void main(String[] args) throws Exception {
		Thread[] threads = new Thread[count];
		for (int i =0;i<count;i++){
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0;i<10000;i++){
						increase();
					}
					countDownLatch.countDown();
				}
			});
			threads[i].start();
		}
		countDownLatch.await();
		System.out.println(race.get());
	}

}
