package org.eureka.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * The type Socket server.
 *
 * @author Eureka.
 * @date 2017 -05-03 15:33:14
 * @author: 奎
 * @date: 2017 /5/3 15:30
 * @description:
 */
public class SocketServer {
    /**
     * The constant PORT.
     */
    public static final int PORT =12135;//监听端口

    public static void main(String []args){
            System.out.println("服务器启动...\n");
            SocketServer server = new SocketServer();
            server.init();
    }

    public void init(){
        try {
            ServerSocket serverSocket=new ServerSocket(PORT);
            while (true){
                Socket client=serverSocket.accept();
                new SocketThread(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SocketThread implements Runnable{
        private Socket socket;
        public SocketThread(Socket clinet){
            socket=clinet;
            new Thread(this,"ServerThread").start();
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+" thread is running");
                DataInputStream dataInput=new DataInputStream(socket.getInputStream());
                String clientInputStr= dataInput.readUTF();
                System.out.println("get client msg :"+clientInputStr);

                DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
                String input=randomResult();
                System.out.println("send random result :"+input);
                dataOutputStream.writeUTF(input);

                dataOutputStream.close();
                dataInput.close();

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    socket=null;
                    e.printStackTrace();
                }
            }
        }

        private String randomResult(){
            Random random=new Random();
            String[] result={"a","b","c","d","e","f","exit"};
            return result[random.nextInt(result.length)];
        }
    }
}
