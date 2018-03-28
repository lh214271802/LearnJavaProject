package cn.lh.learnproject.httpserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.CRL;
import java.util.Date;

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
            //客户端请求信息
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

            //服务器响应

            StringBuilder responseContext = new StringBuilder();
            responseContext.append("你好啊");


            StringBuilder response = new StringBuilder();
            //1.HTTP协议版本、状态代码、描述
            response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
            //2.响应头
            response.append("Server:liaohui Server/0.0.1").append(CRLF)
                    .append("Date:").append(new Date()).append(CRLF);
            //相应的正文类型
            response.append("Content-type:text/html;charset=utf8").append(CRLF);
            //正文长度
            response.append("Content-Length").append(responseContext.toString().getBytes().length).append(CRLF);
            //3.正文之前
            response.append(CRLF);
            //4.正文
            response.append(responseContext);

            //输出流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(response.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {

    }

    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";

}
