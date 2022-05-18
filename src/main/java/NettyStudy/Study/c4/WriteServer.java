package NettyStudy.Study.c4;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @Author xaohii
 * @Date 2022/5/12 15:59
 */
public class WriteServer {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		ssc.bind(new InetSocketAddress(12345));
		while (true){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if (key.isAcceptable()){
					ServerSocketChannel serverSocketChannel =(ServerSocketChannel) key.channel();
					SocketChannel channel = serverSocketChannel.accept();
					channel.configureBlocking(false);
					StringBuilder stringBuilder = new StringBuilder();
					for (int i=0;i<5*10;i++){
						stringBuilder.append("a");
					}
					channel.write(Charset.defaultCharset().encode(stringBuilder.toString()));

				}
			}
		}
	}
}
