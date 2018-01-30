package NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by wuhao on 16/10/26.
 */
public class Channel {

    private  static void channelCopy1(ReadableByteChannel src, WritableByteChannel dest){
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(16*1024);
        try {
            while (src.read(byteBuffer)!=-1){
                //prepare th buffer to be drained
                byteBuffer.flip();
                //Write to the channel;may block
                dest.write(byteBuffer);

                byteBuffer.compact();
            }
            //EOF will leave buffer in fill state
            byteBuffer.flip();
            //Make sure that the buffer is fully drained
            while (byteBuffer.hasRemaining()){
                dest.write(byteBuffer);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  static void channelCopy2(ReadableByteChannel src,WritableByteChannel dest){
        ByteBuffer buffer=ByteBuffer.allocateDirect(16*1024);
        buffer.flip();

        try {
            while (src.read(buffer)!=-1){
                //prepare the buffer to be drained
                buffer.flip();
                //make sure that the buffer was fully drained
                while(buffer.hasRemaining()){
                    dest.write(buffer);
                }
                //make sure buffer empty,ready for filling
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception{
        ReadableByteChannel source= Channels.newChannel(System.in);
        WritableByteChannel dest=Channels.newChannel(System.out);
        channelCopy2(source,dest);
        source.close();
        dest.close();
    }
}
