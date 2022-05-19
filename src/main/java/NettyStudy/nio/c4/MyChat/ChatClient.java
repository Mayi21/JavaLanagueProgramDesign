package NettyStudy.nio.c4.MyChat;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Author xaohii
 * @Date 2022/5/12 17:04
 */
@Slf4j
public class ChatClient {
	public static void main(String[] args) throws Exception {
		// 配置socketChannel
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress("localhost", 1122));
		sc.configureBlocking(true);
		Selector selector = Selector.open();
		sc.register(selector, SelectionKey.OP_CONNECT);
		while (true) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			log.debug("{}", iterator.hasNext());
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				log.debug("{}",key);
				iterator.remove();
				if (key.isConnectable()) {
					SocketChannel socketChannel = (SocketChannel) key.channel();
					socketChannel.write(Charset.defaultCharset().encode("hello"));
					socketChannel.register(selector, SelectionKey.OP_WRITE);
					key.interestOps(SelectionKey.OP_WRITE);
				} else if (key.isWritable()) {
					Scanner scanner = new Scanner(System.in);
					System.out.println("输入信息:");
					String msg = scanner.nextLine();
					SocketChannel socketChannel = (SocketChannel) key.channel();
					socketChannel.write(Charset.defaultCharset().encode(msg));
					socketChannel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					try {
						SocketChannel socketChannel = (SocketChannel) key.channel();
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						int len = socketChannel.read(byteBuffer);
						if (len != -1) {
							String message = Charset.defaultCharset().decode(byteBuffer).toString();
							log.debug(">>> Message is {}", message);
						} else {
							key.cancel();
						}
					} catch (Exception e) {
						key.cancel();
					}
				}
			}
		}
	}
}
