package NettyStudy.Study.c6;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author xaohii
 * @Date 2022/5/19 10:25
 */
@Slf4j
public class AioFileDemo {
	public static void main(String[] args) throws Exception {

		try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("src/main/java/NettyStudy/Study/data1.txt"), StandardOpenOption.READ)) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(16);
			log.debug("read begin...");
			channel.read(byteBuffer, 0, null, new CompletionHandler<Integer, ByteBuffer>() {
				// read success
				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					log.debug("read complete..");
					byteBuffer.flip();
					System.out.println(byteBuffer.position());
					System.out.println(byteBuffer.limit());
					log.debug("{}",Charset.defaultCharset().decode(byteBuffer).toString());

				}
				// read failed
				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					exc.printStackTrace();
				}
			});
			log.debug("read finished...");
			// new CompletionHandler 是一个守护线程
			// 因此在主线程结束后，守护线程也就结束了。为此在此休息几秒，等待线程把内容打印出来
			Thread.sleep(5000);
		}catch (Exception e){
			e.printStackTrace();
		};


	}
}
