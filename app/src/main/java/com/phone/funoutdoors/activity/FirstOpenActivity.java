package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.phone.funoutdoors.adapter.GuideVPAdapter;
import com.phone.funoutdoors.R;

import java.util.ArrayList;
import java.util.List;


public class FirstOpenActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private SharedPreferences sp;
    private boolean isFirstOpen;
    ImageView startImage;
    private ViewPager mViewPager;
    private List<ImageView> imageViews = new ArrayList<>();
    private int[] imgs = {R.mipmap.guide_one, R.mipmap.guide_two, R.mipmap.guide_three, R.mipmap.guide_four};
    private GuideVPAdapter guideVPAdapter;
    private LinearLayout linear_start;
    private LinearLayout linear_circle;
    private int prePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_open);

        mViewPager = (ViewPager) findViewById(R.id.welcome_viewPager);
        linear_start = (LinearLayout) findViewById(R.id.linear_start);
        linear_circle = (LinearLayout) findViewById(R.id.linear_circle_container);

        mViewPager.addOnPageChangeListener(this);
        sp = getSharedPreferences("isFirstOpen", MODE_PRIVATE);
        isFirstOpen = sp.getBoolean("isFirstOpen", true);
        if (isFirstOpen) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirstOpen", false);
            editor.commit();
        } else {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }

        initViewPager();
    }

    private void initViewPager() {
        for (int i = 0; i < imgs.length; i++) {
            //动态添加小圆点
            ImageView circle = new ImageView(this);
            circle.setBackgroundResource(R.drawable.circle);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(20, 20);
            llp.setMargins(30, 30, 30, 30);
            circle.setLayoutParams(llp);
            circle.setEnabled(false);
            linear_circle.addView(circle);

            //动态添加图片
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgs[i]);
            if (i == imgs.length - 1) {

                startImage = new ImageView(this);
                startImage.setImageResource(R.mipmap.yindao_button_h);
                startImage.setVisibility(View.INVISIBLE);
                linear_start.addView(startImage);
                startImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FirstOpenActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
            imageViews.add(imageView);
        }
        //将第一个设置为选中
        linear_circle.getChildAt(prePosition).setEnabled(true);

        guideVPAdapter = new GuideVPAdapter(imageViews);
        mViewPager.setAdapter(guideVPAdapter);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置立即体验是否可见
        if (position != imgs.length - 1) {
            startImage.setVisibility(View.INVISIBLE);
        } else {
            startImage.setVisibility(View.VISIBLE);
        }

        linear_circle.getChildAt(position).setEnabled(true);
        linear_circle.getChildAt(prePosition).setEnabled(false);
        prePosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

