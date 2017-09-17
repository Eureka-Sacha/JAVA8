package org.eureka.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The type File util.
 *
 * @author Eureka.
 * @date 2017 -04-24 11:32:51
 * @author: 奎
 * @date: 2017 /4/24 11:32
 * @description:
 */
public class FileUtil {

    private FileUtil(){}

    public static File createTempFile() throws IOException {
        File temp=File.createTempFile("test","txt");
        FileWriter writer=new FileWriter(temp);
        StringBuffer tempStr=new StringBuffer();
        tempStr.append("1234567890\r\n");
        tempStr.append("abcdefghijklmnopqrstuvwxyz\r\n");
        tempStr.append("这里是中文\r\n");
        tempStr.append("~!@#$%^&*()_+\r\n");
        tempStr.append("\t\b\r\n");
        writer.write(tempStr.toString().toCharArray());
        writer.close();
        return temp;
    }
}
