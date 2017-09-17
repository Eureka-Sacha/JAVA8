package org.test.io;

import org.eureka.utils.FileUtil;
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

        //读取到字节数组中
        bufferedInput.read(get);
        //关闭缓冲流
        bufferedInput.close();
        //print
        System.out.println(new String(get));
    }

    /**
     * Write new file.
     * 随便写点东西到文件
     *
     * @throws IOException the io exception
     */
    @Test
    public void writeNewFile() throws IOException {
        //创建缓冲输出流
        OutputStream bufferedOutput = new BufferedOutputStream(new FileOutputStream("d:\\temp\\测试.txt"));
        //随便写入点东西
        for (int i = 0; i < 999999; i++) {
            bufferedOutput.write(("" + i + "\r\n").getBytes());
        }
        //关闭流
        bufferedOutput.close();
    }

    /**
     * Copy file.
     * 用流的方式复制文件
     *
     * @throws IOException the io exception
     */
    @Test
    public void copyFile() throws IOException {
        //创建输出流 并装饰为缓冲流
        OutputStream fileOutput = new FileOutputStream(new File("D:\\temp\\copy.txt"));
        OutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
        //创建输入流  并装饰为缓冲
        InputStream fileInput = new FileInputStream(new File("d:\\temp\\测试.txt"));
        InputStream bufferedInput = new BufferedInputStream(fileInput);
        //定义缓冲块
        byte[] bytes = new byte[8096];
        //标记位
        int n = 0;
        //循环   从输入流中读入到缓冲块, 并写入到缓冲输出流 0-标记位
        while ((n = bufferedInput.read(bytes)) > -1) {
            bufferedOutput.write(bytes, 0, n);
            bufferedOutput.flush();
        }
        //关闭流
        close(bufferedInput);
        close(bufferedOutput);
    }

    /**
     * Create reader.
     *
     * @throws IOException the io exception
     */
    @Test
    public void createReaderTest() throws IOException {
        StringBuffer tempString = new StringBuffer("Hello String");
        char[] tempChars = new String("Hello Chars").toCharArray();
        byte[] tempBytes = new String("Hello Bytes").getBytes();
        File tempFile = FileUtil.createTempFile();

        //最常用的字符流, 由字节流转换为字符流
        Reader reader = new InputStreamReader(new FileInputStream(tempFile));
        //字符流, 直接使用char[]创建
        Reader charArrayReader = new CharArrayReader(tempChars);
        //文件字符流   直接读取文本文件
        Reader fileReader = new FileReader(tempFile);
        //管道字符流   用法与字节流一致  创建时也需要绑定输出流
        Reader pipedReader = new PipedReader(new PipedWriter());
        //String字符流
        Reader stringReader = new StringReader(tempString.toString());
        //最常用的字符流装饰   缓冲字符流
        Reader bufferedReader = new BufferedReader(reader, 1024 * 2);


        close(reader);
        close(charArrayReader);
        close(fileReader);
        close(pipedReader);
        close(stringReader);
        close(bufferedReader);
    }

    /**
     * Create writer.
     *
     * @throws IOException the io exception
     */
    @Test
    public void createWriterTest() throws IOException {
        StringBuffer tempString = new StringBuffer("Hello String");
        char[] tempChars = new String("Hello Chars").toCharArray();
        byte[] tempBytes = new String("Hello Bytes").getBytes();
        File tempFile = FileUtil.createTempFile();

        //装饰器   将字节流转换为字符流
        Writer writer = new OutputStreamWriter(new ByteArrayOutputStream());
        //字节流
        Writer charArrayWriter = new CharArrayWriter();
        //文件输出字符流
        Writer fileWriter = new FileWriter(tempFile);
        //管道输出流
        Writer pipedWriter = new PipedWriter();
        //String流
        Writer stringWriter = new StringWriter();
        //缓冲字符输出流
        Writer bufferedWriter = new BufferedWriter(writer, 1024 * 2);

        close(writer);
        close(charArrayWriter);
        close(fileWriter);
        close(pipedWriter);
        close(stringWriter);
        close(bufferedWriter);
    }

    /**
     * Split text file test.
     *
     * @throws IOException the io exception
     */
    @Test
    public void splitTextFileTest() throws IOException {
        File localFile = new File("d:\\temp\\ecology_20150809.log");
        File path=new File("D:\\temp\\SplitTextFileTest\\");
        Reader reader = new InputStreamReader(new FileInputStream(localFile));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            if (!path.exists()){
                path.mkdir();
            }
            File temp = new File(path.getPath()+File.separator + (Math.random()*100000) + ".txt");
            Writer writer = new FileWriter(temp);
            writer.write(s);
            writer.flush();
            writer.close();

        }
        bufferedReader.close();
    }

    private void close(Closeable stream) throws IOException {
        stream.close();
    }


}
