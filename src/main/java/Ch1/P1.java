package Ch1;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//java简单实现异步队列:使用生产者与消费者模型
public class P1 {
	private static final Lock lock = new ReentrantLock();
	private static int count;
	public static void main(String[] args) {

	}
	public static void add(int n){
		lock.lock();
		try {
			count+=n;
		}finally {
			lock.unlock();
		}
	}
}
