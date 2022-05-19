package NettyStudy.nio.c4;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @Author xaohii
 * @Date 2022/5/12 16:10
 */
public class ReadClient {
	public static void main(String[] args) throws Exception{
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("localhost", 12345));
		int i=0;
		while (true){
			ByteBuffer byteBuffer = ByteBuffer.allocate(5);
			socketChannel.read(byteBuffer);
			byteBuffer.flip();
			System.out.println(Charset.defaultCharset().decode(byteBuffer).toString());
			System.out.println(i);

			i++;
		}
	}
}
