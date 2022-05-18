package NettyStudy.Study;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author xaohii
 * @Date 2022/5/12 8:26
 */
public class TestFileChannel {
	public static void main(String[] args) {
		String a = "hello";
		String b = "world";
		long start = System.nanoTime();
		try (FileChannel from = new FileInputStream("src/main/java/NettyStudy/Study/data.txt").getChannel();
			 FileChannel to = new FileOutputStream("data1.txt").getChannel();) {
			from.transferTo(0,from.size(), to);
			System.out.println(to.size());
		} catch (IOException e) {
		}
		long end = System.nanoTime();
		System.out.println(end-start);
	}
}
