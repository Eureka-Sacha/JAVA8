package org.test.nio;

import org.eureka.Utils.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: 奎
 * @date: 2017/5/3 23:18
 * @description:
 */
public class JavaNIOTest {
    @Test
    public void sampleChannel() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(FileUtil.createTempFile(), "rw");
        FileChannel fileChannel=aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = fileChannel.read(buf);
        while (bytesRead!=-1){
            System.out.println("read "+bytesRead);
            buf.flip();
            while (buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead=fileChannel.read(buf);
        }

        aFile.close();
    }
}
