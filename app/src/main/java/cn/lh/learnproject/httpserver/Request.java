package cn.lh.learnproject.httpserver;

import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liaohui on 2018/3/28.
 */

public class Request {
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";
    //请求方式
    private String method;
    //请求资源
    private String url;
    //请求参数,list是因为可能存在多个值
    private Map<String, List<String>> params;

    //内部
    private InputStream is;
    private String request;
    private String requestInfo;

    public Request() {
        method = "";
        url = "";
        params = new HashMap<>();
        requestInfo = "";
    }

    public Request(InputStream is) {
        this();
        this.is = is;
        byte[] data = new byte[20480];
        int len = 0;
        try {
            len = is.read(data);
        } catch (IOException e) {
            return;
        }
        requestInfo = new String(data, 0, len);
        //分析头信息
        parseRequestInfo();
    }

    /**
     * 分析请求信息
     */
    private void parseRequestInfo() {
        if (TextUtils.isEmpty(requestInfo)) {
            return;
        }

        //从信息的首行分解出：请求方式 请求路径 请求参数（get可能存在）
        // 如：GET/index.html?name=123&pwd=353 HTTP/1.1
        // 如果请求方式为post，请求参数可能在最后正文中
        String paramsString = "";//请求参数
        //1.请求方式
        String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
        int idX = requestInfo.indexOf("/");
        this.method = firstLine.substring(0, idX).trim();
        String urlStr = firstLine.substring(idX, firstLine.indexOf("HTTP/")).trim();
        if (this.method.equalsIgnoreCase("post")) {
            this.url = urlStr;
            paramsString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
        } else if (this.method.equalsIgnoreCase("get")) {
            if (urlStr.contains("?")) {
                String[] urlArray = urlStr.split("\\?");
                this.url = urlArray[0];
                paramsString = urlArray[1];
            } else {
                this.url = urlStr;
            }
        }

        //2.将请求参数封装到map中
        if (!TextUtils.isEmpty(paramsString)) {
            String[] strings = paramsString.split("&");
            for (String string : strings) {
                String[] paraArr = string.split("=");
                String key = paraArr[0];
                String value = paraArr[1] == null ? null : paraArr[1].trim();
                params.put(key, new ArrayList<String>() {{
                    add(value);
                }});
            }
        }
        //3.
    }

}
