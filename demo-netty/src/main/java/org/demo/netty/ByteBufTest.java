package org.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest {
    public static void main(String[] args) {
        //创建bytebuf
        ByteBuf buf = Unpooled.copiedBuffer("hello".getBytes());
        System.out.println(buf);//UnpooledHeapByteBuf(ridx: 0, widx: 5, cap: 5/5)

        // 读取一个字节
        buf.readByte();
        System.out.println(buf);//UnpooledHeapByteBuf(ridx: 1, widx: 5, cap: 5/5)

        // 读取一个字节
        buf.readByte();
        System.out.println(buf);//UnpooledHeapByteBuf(ridx: 2, widx: 5, cap: 5/5)

        // 丢弃无用数据
        buf.discardReadBytes();
        System.out.println(buf);//UnpooledHeapByteBuf(ridx: 0, widx: 3, cap: 5/5)

        // 清空
        buf.clear();
        System.out.println(buf);//UnpooledHeapByteBuf(ridx: 0, widx: 0, cap: 5/5)

        // 写入
        buf.writeBytes("123".getBytes());
        System.out.println(buf);//UnpooledHeapByteBuf(ridx: 0, widx: 3, cap: 5/5)

        buf.markReaderIndex();
        System.out.println("mark:"+buf);//mark:UnpooledHeapByteBuf(ridx: 0, widx: 3, cap: 5/5)

        buf.readByte();
        buf.readByte();
        System.out.println("read:"+buf);//read:UnpooledHeapByteBuf(ridx: 2, widx: 3, cap: 5/5)

        buf.resetReaderIndex();
        System.out.println("reset:"+buf);//reset:UnpooledHeapByteBuf(ridx: 0, widx: 3, cap: 5/5)
    }
}
