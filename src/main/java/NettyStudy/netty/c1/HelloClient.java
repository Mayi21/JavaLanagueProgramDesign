package NettyStudy.netty.c1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @Author xaohii
 * @Date 2022/5/19 15:43
 */
public class HelloClient {
	public static void main(String[] args) throws InterruptedException {
		// 启动类
		new Bootstrap()
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
				.channel()
				.writeAndFlush("Hello, world");
	}
}
