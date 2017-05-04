package org.eureka.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

/**
 * @author: 奎
 * @date: 2017/5/3 15:30
 * @description:
 */
public class SocketClient {

    public static final String IP="localhost";
    public static final int PORT=12135;

    private Socket client;
    public SocketClient(){
        try {
            while (true) {
                client = new Socket(IP, PORT);
                new ClientThread(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientThread implements Runnable{
        private Socket socket;
        public ClientThread(Socket server){
            socket=server;
            new Thread(this,"ClientThread").start();
        }

        @Override
        public void run() {
            try {
                //读取服务器端数据
                DataInputStream input = new DataInputStream(socket.getInputStream());
                //向服务器端发送数据
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("send some random String..");
                String str=randomResult();
                out.writeUTF(str);

                String result=input.readUTF();
                System.out.println("get server msg:"+result);
                if(result.equals("exit")){
                    System.out.println("客户端将关闭连接");
                    Thread.sleep(500);
                }
                out.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }

        private String randomResult(){
            Random random=new Random();
            String []result={"随机1","随机2","随机3","随机4","随机5","随机6","随机7","随机8"};
            return result[random.nextInt(result.length)];
        }
    }


}
