package com.hellowd.server;

import com.hellowd.server.netty.HttpChannelInitializer;
import com.hellowd.server.netty.TcpChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages = "com.hellowd.server")
@PropertySource(value="classpath:/properties/local/netty.properties")
public class HelloworldInfraCidPushServerBootApplication {

	static Logger logger = LoggerFactory.getLogger(HelloworldInfraCidPushServerBootApplication.class);

	@Configuration
	@Profile("live1")
	@PropertySource("classpath:/properties/live1/netty.properties")
	static class Live1{}

	@Configuration
	@Profile("live2")
	@PropertySource("classpath:/properties/live2/netty.properties")
	static class Live2{}

	@Configuration
	@Profile("local")
	@PropertySource("classpath:/properties/local/netty.properties")
	static class Local{}

	@Value("${boss.thread.count}")
	private int bossThreadCount;

	@Value("${worker.thread.count}")
	private int workerThreadCount;

	@Value("${server.tcpPort}")
	private int tcpPort;

	@Value("${server.httpPort}")
	private int httpPort;

	@Value("${server.sessionTimeout}")
	private int sessionTimeout;

	@Value("${so.keepalive}")
	private boolean soKeepalive;

	@Value("${so.tcpNoDelay}")
	private boolean soTcpNoDelay;

	@Value("${so.reuseAddress}")
	private boolean soReuseAddress;

	@Value("${so.backlog}")
	private int soBacklog;

	@Value("${zookeeper.hostPort}")
	private String zkHostPort;

	@Value("${zookeeper.sessionTimeout}")
	private int zkSessionTimeout;

	@Value("${zookeeper.znode}")
	private String zkZnode;

	@Value("${zookeeper.znodeData}")
	private String zkZnodeData;

	@Value("${call.id.watch.host}")
	private String callIdWatchHost;

	@Value("${call.id.watch.port}")
	private String callIdWatchPort;

	@Autowired
	@Qualifier("tcpChannelInitializer")
	private TcpChannelInitializer tcpChannelInitializer;

	@Autowired
	@Qualifier("httpChannelInitializer")
	private HttpChannelInitializer httpChannelInitializer;


	@Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup bossGroup() {
		return new NioEventLoopGroup(bossThreadCount);
	}

	@Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup workerGroup() {
		return new NioEventLoopGroup(workerThreadCount);
	}

	@Bean(name = "tcpSocketAddress")
	public InetSocketAddress tcpPort() {
		return new InetSocketAddress(tcpPort);
	}

	@Bean(name = "httpSocketAddress")
	public InetSocketAddress httpPort() {
		return new InetSocketAddress(httpPort);
	}

	@Bean(name="tcpChannelOptions")
	public Map<ChannelOption<?>,Object> tcpChannelOptions(){
		Map<ChannelOption<?>,Object> options = new HashMap<ChannelOption<?>,Object>();
		//Keepalives를 전달한다.
		options.put(ChannelOption.SO_KEEPALIVE, soKeepalive);
		//The maximum queue length for incoming connection indications (a request to connect) is set to the backlog parameter.
		//If a connection indication arrives when the queue is full, the connection is refused.
		options.put(ChannelOption.SO_BACKLOG, soBacklog);
		//Nagle 알고리즘이 적용되면, 운영체제는 패킷을 ACK가 오기를 기다렸다가 도착하면,
		// 그 동안 쌓여있던 데이터를 한꺼번에 보내게 된다. 이러한 방식을 사용하게 되면,
		//대역폭이 낮은 WAN에서 빈번한 전송을 줄이게 됨으로 효과적인 대역폭활용이 가능해진다
		options.put(ChannelOption.TCP_NODELAY, soTcpNoDelay);
		//이미 사용된 주소를 재사용 (bind) 하도록 한다.
		options.put(ChannelOption.SO_REUSEADDR, soReuseAddress);

		return options;
	}

	@Bean(name="tcpBootstrap")
	public ServerBootstrap getTcpBootstrap(){
		return getBootstrap(tcpChannelInitializer);
	}

	@Bean(name="httpBootstrap")
	public ServerBootstrap getHttpBootstrap(){
		return getBootstrap(httpChannelInitializer);
	}


	public ServerBootstrap getBootstrap(ChannelInitializer<SocketChannel> serverInitializer){
		ServerBootstrap tcpBootstrap = new ServerBootstrap();
		tcpBootstrap.group(bossGroup(),workerGroup())
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler((LogLevel.DEBUG)))
				.childHandler(serverInitializer);
		if(serverInitializer instanceof  TcpChannelInitializer) {
			Map<ChannelOption<?>, Object> tcpChannelOption = tcpChannelOptions();
			Set<ChannelOption<?>> keySet = tcpChannelOption.keySet();
			for (ChannelOption option : keySet) {
				tcpBootstrap.option(option, tcpChannelOption.get(option));
			}
		}
		return tcpBootstrap;
	}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx =  SpringApplication.run(HelloworldInfraCidPushServerBootApplication.class, args);
		ctx.getBean(NettyServer.class).start();
	}
}
