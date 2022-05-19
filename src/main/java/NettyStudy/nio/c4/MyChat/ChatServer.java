package NettyStudy.nio.c4.MyChat;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @Author xaohii
 * @Date 2022/5/12 16:38
 */
@Slf4j
public class ChatServer {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		serverSocketChannel.bind(new InetSocketAddress(1122));
		while (true){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if (key.isAcceptable()){
					// 获取到serverSocketChannel
					ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
					// 阻塞获取socketChannel
					SocketChannel socketChannel = ssc.accept();
					socketChannel.configureBlocking(false);
					log.debug("remote address is {}", socketChannel.getRemoteAddress());
					// 注册socketChannel到selector中
					socketChannel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					try {
						SocketChannel socketChannel = (SocketChannel) key.channel();
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						int len = socketChannel.read(byteBuffer);
						if (len != -1){
							byteBuffer.flip();
							String message = Charset.defaultCharset().decode(byteBuffer).toString();
							byteBuffer.clear();
							log.debug("<< Message is \t{}", message);
							message += "\tget it!";
							socketChannel.register(selector, SelectionKey.OP_WRITE, message);
						}else{
							key.cancel();
						}
					}catch (Exception e){
						key.cancel();
					}
				} else if (key.isWritable()) {
					SocketChannel socketChannel = (SocketChannel) key.channel();
					String message = (String) key.attachment();
					log.debug(">> MESSAGE IS \t{}",message);
					socketChannel.write(Charset.defaultCharset().encode(message));
					key.interestOps(SelectionKey.OP_READ);
					key.attach(null);
				}
			}

		}
	}
}
