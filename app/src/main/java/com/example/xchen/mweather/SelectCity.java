package com.example.xchen.mweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xchen on 16/12/19.
 */

public class SelectCity extends Activity implements View.OnClickListener{
    private ImageView backBtn;
    private ListView cityListLv;

    private List<City> mCityList;
    private MyApplication mApplication;
    private ArrayList<String> mArrayList;

    private String updateCityCode = "-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        backBtn = (ImageView)findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);

        mApplication = (MyApplication)getApplication();
        mCityList = mApplication.getCityList();
        mArrayList = new ArrayList<String>();//不new会指向空
        for(int i=0;i<mCityList.size();i++)
        {
            String cityName = mCityList.get(i).getCity();
            mArrayList.add(cityName);
        }
        cityListLv = (ListView)findViewById(R.id.selectcity_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
        cityListLv.setAdapter(adapter);

        //添加ListView项的点击事件的动作
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateCityCode = mCityList.get(position).getNumber();
                Log.d("update city code",updateCityCode);
            }
        };
        //为组件绑定监听
        cityListLv.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.title_selectCity_back:
//                finish();
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("citycode",updateCityCode);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
