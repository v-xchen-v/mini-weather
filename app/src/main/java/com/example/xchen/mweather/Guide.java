package com.example.xchen.mweather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xchen on 16/12/22.
 */

public class Guide extends Activity implements ViewPager.OnPageChangeListener{
    private List<View> views;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    private Button startB;

    private ImageView[] dots;
    private int[] id={R.id.pointImg1,R.id.pointImg2,R.id.pointImg3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);


        SharedPreferences sharedPreferences = getSharedPreferences("viewpagedate",Activity.MODE_PRIVATE);
        if(sharedPreferences.getInt("time",0)==8)//
        {
            Intent intent = new Intent(Guide.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("time", 8);//0
            editor.commit();
        }

        initViews();
        initDots();
        startB = (Button)views.get(2).findViewById(R.id.startAppBtn);
        startB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guide.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initViews()
    {
        LayoutInflater lf = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(lf.inflate(R.layout.guide_page01,null));
        views.add(lf.inflate(R.layout.guide_page02,null));
        views.add(lf.inflate(R.layout.guide_page03,null));

        viewPagerAdapter = new ViewPagerAdapter(views,this);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void initDots()
    {
        dots = new ImageView[views.size()];
        for(int i=0;i< views.size();i++)
        {
            dots[i] = (ImageView)findViewById(id[i]);
        }
    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<id.length;i++)
        {
            Log.d("page select id",Integer.toString(i));
            if(i==position)
            {
                dots[i].setImageResource(R.drawable.page_indicator_focused);
            }else{
                dots[i].setImageResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

}
