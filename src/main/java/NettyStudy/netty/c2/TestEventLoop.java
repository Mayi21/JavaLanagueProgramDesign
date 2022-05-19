package NettyStudy.netty.c2;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author xaohii
 * @Date 2022/5/19 16:10
 */
@Slf4j
public class TestEventLoop {
	public static void main(String[] args) {
		// 创建事件循环组
		// 支持 io事件、普通任务和定时任务
		NioEventLoopGroup loopGroup = new NioEventLoopGroup(2);
		// 仅支持普通任务和定时任务
//		new DefaultEventLoopGroup();

		// 获取事件循环对象
		System.out.println(loopGroup.next());

		// 执行普通任务
//		loopGroup.next().submit(()->{
//			try {
//				Thread.sleep(1000);
//			}catch (Exception e){
//				e.printStackTrace();
//			}
//			log.debug("ok");
//		});

		// 执行定时任务
		/*
		* runnable – the task to execute
		* l – the time to delay first execution
		* l1 – the period between successive executions
		* timeUnit – the time unit of the initialDelay and period parameters
		* */
		loopGroup.next().scheduleAtFixedRate(()->{
			log.debug("ok");
		}, 0, 1, TimeUnit.SECONDS);

		log.debug("main");



	}
}
