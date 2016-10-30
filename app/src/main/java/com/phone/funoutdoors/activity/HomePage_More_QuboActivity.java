package com.phone.funoutdoors.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.HomePageUtils;

import butterknife.BindView;

public class HomePage_More_QuboActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_more_qubo);
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_title, "趣播", R.menu.home_page_toolbar_all_menu);
    }
}
