package NettyStudy.netty.c2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @Author xaohii
 * @Date 2022/5/19 16:26
 */
@Slf4j
public class EventLoopServer {
	public static void main(String[] args) {
		// 细分2: 创建一个独立的EventLoopGroup
		EventLoopGroup eventLoopGroup = new DefaultEventLoop();
		new ServerBootstrap()
				// eventloop 分工细化
				// 细分1: boss 只负责 ServerSocketChannel accept事件； worker 只负责 SocketChannel上的读写
				.group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel channel) throws Exception {
						channel.pipeline().addLast("handler-1",new ChannelInboundHandlerAdapter(){
							// 由于此时并未添加解码器
							// 因此此时还是ByteBuf类型

							// 如果在这里面需要花费的时间太多呢，
							// 可以单独再加一个EventLoopGroup来处理
							@Override
							public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
								ByteBuf byteBuf = (ByteBuf) msg;
								log.debug(byteBuf.toString(Charset.defaultCharset()));
								// 让消息传递给下一个handler
								ctx.fireChannelRead(msg);
							}
						}).addLast(eventLoopGroup, "handler-2", new ChannelInboundHandlerAdapter(){
							@Override
							public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
								ByteBuf byteBuf = (ByteBuf) msg;
								log.debug(byteBuf.toString(Charset.defaultCharset()));
							}
						});
					}
				})
				.bind(12345);
	}
}
