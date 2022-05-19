package NettyStudy.netty.c2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author xaohii
 * @Date 2022/5/19 16:32
 */
public class EventLoopClient {
	public static void main(String[] args) throws InterruptedException {
		// 启动类
		Channel channel = new Bootstrap()
				// 添加Event Loop
				.group(new NioEventLoopGroup())
				// 选择客户端 channel 实现
				.channel(NioSocketChannel.class)
				// 添加处理器
				.handler(new ChannelInitializer<NioSocketChannel>() {
					// 连接建立后调用
					@Override
					protected void initChannel(NioSocketChannel channel) throws Exception {
						// 添加编码器
						channel.pipeline().addLast(new StringEncoder());
					}
				})
				.connect(new InetSocketAddress("localhost", 12345))
				.sync()
				.channel();
		AtomicInteger atomicInteger = new AtomicInteger();
		while (true){
			channel.writeAndFlush("hello world "+atomicInteger.incrementAndGet()+" times");
			Thread.sleep(5000);
		}
	}
}
