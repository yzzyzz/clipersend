package com.mydev.clipersend;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "主界面 MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            Log.e(TAG, "handleSendText: "+sharedText );
            SendCliperMessageTask aa = new SendCliperMessageTask();
            aa.execute(sharedText);
        }
    }

    private class SendCliperMessageTask extends AsyncTask<String, Integer, String> {
        //onPreExecute方法在execute()后执行
        @Override
        protected void onPreExecute() {
            Log.i("PicUrlTask", "onPreExecute() enter");
        }

        @Override
        protected String doInBackground(String... params) {
            String message = params[0];
            Map<String,Object> map=new HashMap();//要传的值
            map.put("message",message);
            map.put("key2",123456);
            JSONObject jsonObject=new JSONObject(map);

            Log.e(TAG, "doInBackground: "+jsonObject.toString());
            String ret = Funcs.postMethod("http://gz.999887.xyz/clip.php",jsonObject.toString());
            Log.e(TAG, "doInBackground: get json ret "+ret );
            return ret;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(TAG, "onPostExecute: 任务完成:"+result );
        }
    }

}