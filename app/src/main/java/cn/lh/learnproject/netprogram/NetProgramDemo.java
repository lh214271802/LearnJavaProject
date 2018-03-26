package cn.lh.learnproject.netprogram;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by liaohui on 2018/3/26.
 * TCP:类似电话，三次握手，面向连接，安全可靠，效率相对低下
 * UDP:类似短信，非面向连接，效率高
 * 应用层-表示层-会话层-传输层-网络层-数据链路层-物理层
 * 应      用      层-传输层-网络层-物理+数据链路层
 */

public class NetProgramDemo {
    public static void main(String[] args) {
        //底层传输还是用的流
        //InetAddress InetScoketAddress
        //URL
        //TCP:ServerSocket Socket
        //UDP:DatagramSocket DatagramPacket
        //DNS 域名解析

        TCPTest();

        TCPServerTest();
    }

    /**================================================================UDP=================================**/

    /**
     * 1.客户端
     * ******a.创建客户端 DatagramSocket 类+指定端口
     * ******b.准备数据，字节数组
     * ******c.打包DatagramPacket +服务器地址及端口
     * ******d.发送
     * ******e.释放资源
     * 2.服务器端
     * ******a.创建服务器DatagramSocket  类+指定端口
     * ******b.准备接收容器  字节数组  封装DatagramPacket
     * ******c.包 接收数据
     * ******d.分析
     * ******e.释放资源
     */

    /**
     * 服务端
     */
    public static class MyServer {
        public static void main(String[] args) {
            try {
                DatagramSocket socket = new DatagramSocket(8888);
                byte[] container = new byte[1024];
                DatagramPacket packet = new DatagramPacket(container, container.length);
                socket.receive(packet);
                byte[] data = packet.getData();
                int len = packet.getLength();
                System.out.println(new String(data, 0, len));
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 客户端
     */
    public static class MyClient {
        public static void main(String[] args) {
            try {
                DatagramSocket server = new DatagramSocket(6666);
                String msg = "udp 编程";
                byte[] bytes = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, new InetSocketAddress("localhost", 8888));
                server.send(packet);
                server.close();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ================================================================UDP=================================
     **/
    /**
     * ================================================================TCP=================================
     **/
    public static class TCPServer {
        public static void main(String[] args) {
            try {
                Socket client = new Socket("localhost",8888);
             /*   BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String echo = br.readLine();
                System.out.println(echo);*/
                DataInputStream dis = new DataInputStream(client.getInputStream());
                String echo = dis.readUTF();
                System.out.println(echo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TCP是面向连接的
    private static void TCPServerTest() {
        try {
            ServerSocket server = new ServerSocket(8888);
            Socket socket = server.accept();
            System.out.println("链接了一个客户端啊!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void TCPTest() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost.toString());
            System.out.println(localHost.getCanonicalHostName());

            InetAddress[] inetAddresses = InetAddress.getAllByName("www.baidu.com");
            System.out.println(Arrays.toString(inetAddresses));
            System.out.println(inetAddresses[0].getHostAddress());


            InetSocketAddress address = new InetSocketAddress("192.168.240.2", 9999);
            System.out.println(address.getHostName());
            System.out.println(address.getAddress());


            URL url = new URL("https://www.baidu.com");
            InputStream inputStream = url.openStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            File file = new File("a.html");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
            int len = 0;
            byte[] buf = new byte[1024];
            if ((len = bufferedInputStream.read(buf)) != -1) {
                bufferedOutputStream.write(buf, 0, len);
            }
            bufferedOutputStream.flush();
            System.out.print(out.toString());

            out.close();
            bufferedOutputStream.close();
            inputStream.close();
            bufferedInputStream.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * ================================================================TCP=================================
     **/
}
