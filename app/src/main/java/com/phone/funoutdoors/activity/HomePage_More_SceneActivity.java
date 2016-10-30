package com.phone.funoutdoors.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.utils.HomePageUtils;

import butterknife.BindView;

public class HomePage_More_SceneActivity extends AppCompatActivity {

    @BindView(R.id.home_page_toolbar)
    Toolbar home_page_toolbar;
    @BindView(R.id.home_page_banner_toolbar_title)
    TextView home_page_banner_toolbar_title;
    @BindView(R.id.home_page_banner_toolbar_image)
    TextView home_page_banner_toolbar_image;
    private int region_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_more_scene);
        region_id = getIntent().getIntExtra("region_id", 1);
        home_page_banner_toolbar_title.setVisibility(View.GONE);
        home_page_banner_toolbar_image.setVisibility(View.VISIBLE);
        String title;
        switch (region_id){
            case 1:
                title="西南";
                break;
            case 2:
                home_page_banner_toolbar_image.setText("西北");
                break;
            case 3:
                home_page_banner_toolbar_image.setText("东北");
                break;
            case 4:
                home_page_banner_toolbar_image.setText("华东");
                break;
            case 5:
                home_page_banner_toolbar_image.setText("华南");
                break;
            case 6:
                home_page_banner_toolbar_image.setText("华北");
                break;
        }
        HomePageUtils.setToolbar(this, home_page_toolbar, home_page_banner_toolbar_title, "趣播", R.menu.home_page_toolbar_all_menu);

    }
}
