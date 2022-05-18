package NettyStudy.Study.c4;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author xaohii
 * @Date 2022/5/12 9:01
 */
public class Server {
	public static StringBuilder stringBuilder = new StringBuilder();
	public static void main(String[] args) throws IOException {
		serverConnect();
	}

	public static void serverConnect() throws IOException {
		// create selector
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		// event des key
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		ssc.bind(new InetSocketAddress(12345));
		while (true){
			// 来事件必须处理，没有处理的话，select就认为来事件了，继续不阻塞
			// 可以通过key.channel()来消费事件
			// 也可以通过key.cancel()来取消事件
			// 这个是阻塞的，没有事件处理就阻塞在这里
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()){
				SelectionKey key = iterator.next();
				if (key.isAcceptable()){
					ServerSocketChannel channel = (ServerSocketChannel) key.channel();
					SocketChannel socketChannel = channel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(128));
				} else if (key.isReadable()) {
					try {
						// 由于此处key没有消息了，但是没有释放
						// 因此selector还是认为key里面的消息没有使用
						// 因此会一直报错
						SocketChannel socketChannel = (SocketChannel) key.channel();
						//
						ByteBuffer byteBuffer =(ByteBuffer) key.attachment();
						int read = socketChannel.read(byteBuffer);
						// 这种是为了处理，socketChannel关闭的问题
						if (read == -1){
							key.cancel();
						}else{
							byteBuffer.flip();
							System.out.println(Charset.defaultCharset().decode(byteBuffer).toString());
							byteBuffer.clear();
						}
					}catch (Exception e){
						e.printStackTrace();
						key.cancel();
					}
				}else if (key.isWritable()){

				}
				iterator.remove();
			}
		}
	}

	public static void byteBufferOp(){
		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		try (FileChannel channel = new FileInputStream("src/main/java/NettyStudy/Study/data1.txt").getChannel()) {
			boolean flag = false;
			while (channel.read(byteBuffer)!=-1){
				byteBuffer.flip();
				while (byteBuffer.hasRemaining()){
					char c = (char) byteBuffer.get();
					if (c != '\n'){
						stringBuilder.append(c);
					}else{
						System.out.println(stringBuilder);
						stringBuilder = new StringBuilder();
					}
				}
				byteBuffer.flip();
			}
			System.out.println(stringBuilder);
		}catch (Exception e){
			e.printStackTrace();			
		}
		
		
		
	}
	public static void m1() throws IOException {
		// create server
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// server socket channel no blocking for accept
		serverSocketChannel.configureBlocking(false);
		// bind port
		serverSocketChannel.bind(new InetSocketAddress(8796));
		List<SocketChannel> socketChannelList = new ArrayList<>();
		while (true){
			// connect with client and
			SocketChannel socketChannel = serverSocketChannel.accept();
			if (socketChannel != null){
				// socket channel no blocking for read
				socketChannel.configureBlocking(false);
				socketChannelList.add(socketChannel);
			}
			for (SocketChannel s:socketChannelList){
				ByteBuffer byteBuffer = ByteBuffer.allocate(16);
				if (s.read(byteBuffer) != 0){
					byteBuffer.flip();
					System.out.println((char) byteBuffer.get(0));
				}


			}
		}
	}
}
