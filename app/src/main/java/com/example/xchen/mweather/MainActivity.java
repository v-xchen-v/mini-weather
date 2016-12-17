package com.example.xchen.mweather;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by xchen on 16/12/17.
 */

public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
}
