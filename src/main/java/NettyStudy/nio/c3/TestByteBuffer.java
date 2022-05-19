package NettyStudy.nio.c3;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author xaohii
 * @Date 2022/5/11 21:01
 */
@Slf4j
public class TestByteBuffer {
	public static void main(String[] args) throws Exception{
		try (FileChannel channel = new FileInputStream("src/main/java/NettyStudy/Study/data.txt").getChannel()) {
//			// 新建buffer
//			System.out.println(ByteBuffer.allocate(10).getClass());
//			System.out.println(ByteBuffer.allocateDirect(10).getClass());
//			ByteBuffer b1 = ByteBuffer.allocate(10);
//			b1.put("1234".getBytes());
//			b1.flip();
//			b1.mark();
//			System.out.println((char) b1.get());
//
//			System.out.println((char) b1.get());
//			System.out.println((char) b1.get());
//			b1.reset();
//			System.out.println((char) b1.get());
//			System.out.println((char) b1.get());
//			System.out.println((char) b1.get());

			ByteBuffer buffer = ByteBuffer.allocate(10);
			// 从buffer读取到channel中
			while(true){
				int len = channel.read(buffer);
				log.debug("读取到的字节数{}",len);
				if (len == -1){
					break;
				}
				buffer.flip();
				while (buffer.hasRemaining()){
					byte b = buffer.get();
					log.debug("读取到的字节{}",(char) b);
				}
				//此处用clear也可以，因为已经读完了，因此可以将数据清空
				// flip更像是读写模式的切换，
				buffer.flip();
				// buffer有写入和put、读取和get


			}
		}catch (IOException e){
			System.out.println(e);
		}

	}
}
