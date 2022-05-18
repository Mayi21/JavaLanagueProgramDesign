package NettyStudy.Study.c5;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author xaohii
 * @Date 2022/5/13 10:46
 */
@Slf4j
public class MultiThreadServer {
	public static void main(String[] args) throws Exception{
		// 命名当前的Thread
		Thread.currentThread().setName("boss");
		// 开启socketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		// 开启selector
		Selector boss = Selector.open();
		// serverSocketChannel注册
		serverSocketChannel.register(boss, SelectionKey.OP_ACCEPT);
		// ssc绑定
		serverSocketChannel.bind(new InetSocketAddress(1234));
		Worker worker = new Worker("worker-0");
		while (true){
			boss.select();
			Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
			while (iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if (key.isAcceptable()){
					ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
					SocketChannel socketChannel = ssc.accept();
					socketChannel.configureBlocking(false);
					worker.register(socketChannel);
				}
			}
		}

	}
	static class Worker implements Runnable{
		private Thread thread;
		private Selector selector;
		private String name;
		private volatile boolean start = false;
		private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();
		public Worker(String name){
			this.name = name;
		}
		public void register(SocketChannel socketChannel) throws Exception{
			if (!start){
				thread = new Thread(this, this.name);
				selector = Selector.open();
				thread.start();
				start = true;
			}
			// 向队列添加任务，但是并未执行
			queue.add(()->{
				try {
					socketChannel.register(selector, SelectionKey.OP_READ);
				}catch (Exception e){
					e.printStackTrace();
				}
			});
			selector.wakeup(); // 唤醒selector

		}
		@Override
		public void run(){
			try {
				while (true){
					selector.select();
					Runnable task = queue.poll();
					if (task  != null){
						task.run();
					}
					Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
					while (iterator.hasNext()){
						SelectionKey selectionKey = iterator.next();
						if (selectionKey.isReadable()){
							ByteBuffer byteBuffer = ByteBuffer.allocate(128);
							SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
							socketChannel.read(byteBuffer);
							byteBuffer.flip();
							System.out.println(Charset.defaultCharset().decode(byteBuffer));
						}
					}

				}
			}catch (Exception e){
				e.printStackTrace();
			}

		}

	}
}
