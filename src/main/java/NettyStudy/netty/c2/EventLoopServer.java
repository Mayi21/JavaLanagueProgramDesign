package NettyStudy.netty.c2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
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
		new ServerBootstrap()
				.group(new NioEventLoopGroup())
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel channel) throws Exception {
						channel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
							// 由于此时并未添加解码器
							// 因此此时还是ByteBuf类型
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
