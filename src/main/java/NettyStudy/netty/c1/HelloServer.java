package NettyStudy.netty.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author xaohii
 * @Date 2022/5/19 11:15
 */
public class HelloServer {
	public static void main(String[] args) {
		// 服务器端启动器，负责组装netty组件
		new ServerBootstrap()
				.group(new NioEventLoopGroup())
				// 选择服务器的ServerSocketChannel实现
				.channel(NioServerSocketChannel.class)
				// boss 负责处理连接；worker(child) 负责处理读写 决定了worker可以干什么
				.childHandler(
						// channel代表和客户端进行读写的通道 负责添加别的handler
						new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel channel) throws Exception{
						// 添加具体的handler
						// 将bytebuffer转化为字符串
						channel.pipeline().addLast(new StringDecoder());
						// 自定义的handler
						channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
							@Override
							public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
								System.out.println(msg);
							}
						});
					}
		}).bind(12345);
	}
}
