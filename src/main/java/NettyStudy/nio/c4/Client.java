package NettyStudy.nio.c4;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @Author xaohii
 * @Date 2022/5/12 9:13
 */
public class Client {
	public static void main(String[] args) throws Exception {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("localhost", 1234));
		while (true){
			socketChannel.write(Charset.defaultCharset().encode("hello world i am iron man"));
			Thread.sleep(5000);
		}
	}
}
