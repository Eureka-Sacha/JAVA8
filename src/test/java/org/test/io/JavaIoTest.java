package org.test.io;

import org.eureka.Utils.FileUtil;
import org.junit.Test;

import java.io.*;

/**
 * Java IO  测试
 *
 * @author Eureka.
 * @date 2017 -04-24 11:28:28
 * @author: 奎
 * @date: 2017 /4/24 11:27
 * @description:
 */
public class JavaIoTest {

    private InputStream inputStream;
    private OutputStream outputStream;

    /**
     * New input stream test.
     *
     * @throws IOException the io exception
     */
    @Test
    public void newInputStreamTest() throws IOException {
        StringBuffer tempString = new StringBuffer("Hello String");
        char[] tempChars = new String("Hello Chars").toCharArray();
        byte[] tempBytes = new String("Hello Bytes").getBytes();
        File tempFile = FileUtil.createTempFile();

        //二进制输入流 从二进制数组读取    ByteArrayInputStream(二进制数组,开始标记,结束标记)
        InputStream arrayInput = new ByteArrayInputStream(tempBytes, 0, tempBytes.length - 1);
        //文件流 从文件中读取;
        InputStream fileInput = new FileInputStream(tempFile);
        //缓冲流 对其他数据流进行包装  BufferedInputStream(需要缓冲的流,缓冲大小);
        InputStream bufferedInput = new BufferedInputStream(arrayInput, 2 * 1024);
        //对象流  从数据流中获取可Serializable的对象;
        InputStream ObjectInput = new ObjectInputStream(fileInput);
        //管道流  线程之间共享的数据流; 需要绑定outputStream
        InputStream pipedInput = new PipedInputStream(new PipedOutputStream());
        //合并流   将两个数据流合并;
        InputStream sequenceInput = new SequenceInputStream(arrayInput, fileInput);

        close(arrayInput);
        close(fileInput);
        close(bufferedInput);
        close(ObjectInput);
        close(pipedInput);
        close(sequenceInput);
    }

    /**
     * New output stream test.
     *
     * @throws IOException the io exception
     */
    @Test
    public void newOutputStreamTest() throws IOException {
        StringBuffer tempString = new StringBuffer("Hello String");
        char[] tempChars = new String("Hello Chars").toCharArray();
        byte[] tempBytes = new String("Hello Bytes").getBytes();
        File tempFile = FileUtil.createTempFile();

        //二进制输出流
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //文件流
        OutputStream fileOutputStream = new FileOutputStream(tempFile);
        //缓冲流
        OutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream, 2 * 1024);
        //对象流
        OutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        //管道流
        OutputStream pipedOutputStream = new PipedOutputStream();

        close(byteArrayOutputStream);
        close(fileOutputStream);
        close(bufferedOutputStream);
        close(objectOutputStream);
        close(pipedOutputStream);

    }

    /**
     * Gets byte from stream.
     *
     * @throws IOException the io exception
     * @author Eureka.
     * @date 2017 -04-26 14:14:59
     */
    @Test
    public void getByteFromStream() throws IOException {
        byte[] tempBytes = new String("Hello Bytes").getBytes();
        byte[] get = new byte[1024];

        //转换数据为数据流
        InputStream arrayInput = new ByteArrayInputStream(tempBytes);
        //将字节流转换为缓冲字节流
        InputStream bufferedInput = new BufferedInputStream(arrayInput, 2 * 1024);
        //关闭字节流
        arrayInput.close();

        //读取到字节数组中
        bufferedInput.read(get);
        //关闭缓冲流
        bufferedInput.close();
        //print
        System.out.println(new String(get));
    }

    /**
     * Save byte from stream.
     *
     * @throws IOException the io exception
     */
    @Test
    public void saveByteFromStream() throws IOException {
        byte[] tempBytes = new String("Hello Bytes").getBytes();

        OutputStream arrayOutput = new ByteArrayOutputStream();
        InputStream bufferedInput = new BufferedInputStream(new ByteArrayInputStream(tempBytes));
        byte[] bytes = new byte[1];
        int n=0;
        while ((n=bufferedInput.read(bytes))>-1) {
            arrayOutput.write(bytes,0,n);
        }
        bufferedInput.close();
        System.out.println(arrayOutput.toString());
        arrayOutput.close();
    }

    private void close(Closeable stream) throws IOException {
        stream.close();
    }

}
