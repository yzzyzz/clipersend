package com.mydev.clipersend;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Funcs {

    //请求方法
    public static String postMethod(String url,String param){
        // 结果值
        StringBuffer rest=new StringBuffer();
        HttpURLConnection conn=null;
        OutputStream out=null;
        BufferedReader br=null;
        String ret="";
        try {
            // 创建 URL
            URL restUrl = new URL(url);
            // 打开连接
            conn= (HttpURLConnection) restUrl.openConnection();
            //请求时间
            conn.setConnectTimeout(1000);
            // 请求方式为 POST
            conn.setRequestMethod("POST");
            //JSON数据
            conn.setRequestProperty("Content-Type","application/json");
            // 输入 输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //连接
            conn.connect();

            // 传递参数流的方式
            out=conn.getOutputStream();
            out.write(param.getBytes());
            out.flush();

            // 读取数据
            br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line=null;
            while (null != (line=br.readLine())){
                rest.append(line);
            }
            return rest.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭所有通道
            try {
                if (br!=null)
                    br.close();
                if (out!=null)
                    out.close();
                if (conn!=null)
                    conn.disconnect();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return ret;
    }

    public static String getStringFromurl(String jsonURL){
        String ret = "";
        try {
            URL url = new URL(jsonURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla");
            conn.addRequestProperty("Referer", "google.com");

            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();

            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line).append("\n");
            }

            if (stringBuffer.length() == 0)
            {
                return "" ;
            }
            ret= stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
