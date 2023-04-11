package minxie.space.server;

import io.netty.buffer.Unpooled
import io.netty.channel.*
import io.netty.handler.codec.http.*
import minxie.space.server.HttpMetricResponse.response


class HttpServerHandler : SimpleChannelInboundHandler<FullHttpRequest>() {
    @Throws(Exception::class)
    override fun channelRead0(ctx: ChannelHandlerContext, msg: FullHttpRequest) {
        val uri = msg.uri()
        if ("/metrics" == uri) {
            val response: FullHttpResponse = DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(response().toByteArray())
            )
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE)
        } else {
            val response: FullHttpResponse = DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.NOT_FOUND,
                Unpooled.wrappedBuffer("NOT FOUND".toByteArray())
            )
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE)
        }
    }
}