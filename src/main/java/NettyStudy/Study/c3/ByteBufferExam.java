package NettyStudy.Study.c3;

import java.nio.ByteBuffer;

/**
 * @Author xaohii
 * @Date 2022/5/12 7:59
 */
public class ByteBufferExam {
	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);
		byteBuffer.put("Hello,world\nI'm zhangsan\nHo".getBytes());
		split(byteBuffer);
		byteBuffer.put("w are you?\n".getBytes());
		split(byteBuffer);
	}
	public static void split(ByteBuffer byteBuffer){
		//切换读写模式
		byteBuffer.flip();
		StringBuffer stringBuffer = new StringBuffer();
		while (byteBuffer.hasRemaining()){
			char c = (char)byteBuffer.get();
			if (c != '\n'){
				stringBuffer.append(c);
			}else{
				System.out.println(stringBuffer);
				byteBuffer.mark();
				stringBuffer = new StringBuffer();
			}
		}
		byteBuffer.reset();
		byteBuffer.compact();
	}
}
