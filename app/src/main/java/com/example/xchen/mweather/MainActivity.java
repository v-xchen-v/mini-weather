package com.example.xchen.mweather;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xchen on 16/12/17.
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private ImageView UpdateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        UpdateBtn = (ImageView)findViewById(R.id.title_city_update);;
        UpdateBtn.setOnClickListener(this);

        //检查网络连接状态
        if(CheckNet.getNetState(this)==CheckNet.NET_NONE)
        {
            Log.d("MWEATHER","网络不通");
            Toast.makeText(MainActivity.this,"网络不通",Toast.LENGTH_LONG).show();
        }else
        {
            Log.d("MWEATHER","网络OK");
            Toast.makeText(MainActivity.this,"网络OK",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.title_city_update)
        {
            getWeatherDatafromNet("101010100");
        }
    }

    private void getWeatherDatafromNet(String cityCode)
    {
        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey="+cityCode;
        Log.d("Address:",address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(address);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);
                    urlConnection.setReadTimeout(8000);
                    InputStream in = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer sb = new StringBuffer();
                    String str;
                    while((str=reader.readLine())!=null)
                    {
                        sb.append(str);
                        Log.d("date from url",str);
                    }
                    String response = sb.toString();
                    Log.d("response",response);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
