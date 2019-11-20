package thread;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author baichen
 * @Date 2019/11/19
 * @Description socket连接处理器, 用Netty实现Echo程序服务端，Netty会创建两个EventLoopGroup，一个作为bossGroup，一个是workerGroup
 * 前者处理网络连接请求，后者用来处理读写请求
 * Netty需要事件处理器去处理对应的请求或事件
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    //处理读事件
    @Override
    public void channelRead (ChannelHandlerContext ctx, Object msg){
        ctx.write(msg);
    }

    //处理读完成事件
    @Override
    public void channelReadComplete (ChannelHandlerContext ctx){
        ctx.flush();
    }

    //处理异常事件
    @Override
    public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
    public static void main(String[] args) throws InterruptedException {
        //事件处理器
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //boss线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //worker线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //bind服务端端⼝
            ChannelFuture f = b.bind(9090).sync();
            f.channel().closeFuture().sync();
        } finally {
            //终⽌⼯作线程组
            workerGroup.shutdownGracefully();
            //终⽌boss线程组
            bossGroup.shutdownGracefully();
        }
    }
}
