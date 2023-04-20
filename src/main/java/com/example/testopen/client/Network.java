package com.example.testopen.client;

import com.example.testopen.controllers.AuthController;
import com.example.testopen.controllers.OtherController;
import com.example.testopen.db.TemporaryMemory;
import com.example.testopen.entety.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import javafx.application.Platform;

public class Network {
    private SocketChannel channel;

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8189;

    public Network() {
        Thread t = new Thread(() -> {
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                channel = socketChannel;
                                socketChannel.pipeline().addLast(new StringDecoder(), new StringEncoder(),
                                        new SimpleChannelInboundHandler<String>() {
                                            @Override
                                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                                if (s.equals("true")) {
                                                    TemporaryMemory.flag = true;
                                                } else {
                                                    System.out.println(s);
                                                    TemporaryMemory.testS = s;
                                                    TemporaryMemory.makeTest();
                                                    Platform.runLater(new Runnable() {

                                                        @Override
                                                        public void run() {
                                                            TemporaryMemory.controller.open();
                                                        }
                                                    });
                                                }
                                            }
                                        });
                            }
                        });
                ChannelFuture future = b.connect(HOST, PORT).sync();
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
            }
        });
        t.start();
    }

    public void close() {
        channel.close();
    }

    public void sendMessage(String str) {
        channel.writeAndFlush(str);
    }
}