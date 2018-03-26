package cn.lh.learnproject.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaohui on 2018/3/26.
 */

public class HttpServerTest {

    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        HttpServerTest serverTest = new HttpServerTest();
        serverTest.start();
        serverTest.receive();
    }

    private void start() {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            Socket socket = serverSocket.accept();
            String msg = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder builder = new StringBuilder();
            while ((msg = br.readLine()).length() > 0) {
                builder.append(msg);
                builder.append("\r\n");
                if (null == msg) {
                    break;
                }
            }
            System.out.println(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {

    }


}
