package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.fragment.ConsultPageFragment;
import com.phone.funoutdoors.fragment.GuestPageFragment;
import com.phone.funoutdoors.fragment.HomePageFragment;
import com.phone.funoutdoors.fragment.MinePageFragment;
import com.phone.funoutdoors.fragment.OutdoorPageFragment;

public class MainActivity extends AppCompatActivity {
    private int[] resIds = {R.drawable.home_page_selector, R.drawable.main_qk_page_selector,
            R.drawable.main_zx_page_selector, R.drawable.main_hwq_page_selector, R.drawable.main_mine_page_selector};
    private Class[] fragments = {HomePageFragment.class, GuestPageFragment.class, ConsultPageFragment.class, OutdoorPageFragment.class, MinePageFragment.class};
    private FragmentTabHost fth_tabHost;
    private int position;
    private boolean isLogin;
    private SharedPreferences mPref;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fth_tabHost = (FragmentTabHost) findViewById(R.id.fth_tabHost);

        mPref = getSharedPreferences("config", MODE_PRIVATE);
        position = mPref.getInt("position", position);
        isLogin = mPref.getBoolean("login", false);
        initFragment();
        if (isLogin) {
            fth_tabHost.setCurrentTab(0);
        } else {
            fth_tabHost.setCurrentTab(position);
        }

        fth_tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if ("fra4".equals(tabId)) {
                    if (isLogin) {
                        String opt = tabId.substring(3);
                        position = Integer.parseInt(opt);
                    }
                } else {
                    String opt = tabId.substring(3);
                    position = Integer.parseInt(opt);
                }


            }
        });
    }

    /**
     * 初始化fragment，并设置tabHost
     */
    private void initFragment() {
        fth_tabHost.setup(this, getSupportFragmentManager(), R.id.fragment_container);
        fth_tabHost.getTabWidget().setDividerDrawable(R.color.white);

        for (int i = 0; i < fragments.length; i++) {
            final int count = i;
            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(widthPixels / 5, widthPixels / 7);
            ll.topMargin = 10;
            ll.bottomMargin = 10;
            ImageView view = new ImageView(this);
            view.setLayoutParams(ll);
            view.setImageResource(resIds[i]);
            TabHost.TabSpec tabSpec = fth_tabHost.newTabSpec("fra" + i).setIndicator(view);
            fth_tabHost.addTab(tabSpec, fragments[i], null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPref.edit().putInt("position", position).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPref.edit().putInt("position", 0).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            fth_tabHost.setCurrentTab(position);
        }
        if (requestCode == 101) {
            recreate();
        }


    }
}
