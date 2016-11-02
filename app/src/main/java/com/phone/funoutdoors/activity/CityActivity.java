package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.CityListAdapter;
import com.phone.funoutdoors.interfaces.OnTouchingLetterChangeListener;
import com.phone.funoutdoors.utils.CityData;
import com.phone.funoutdoors.view.MySideBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityActivity extends AppCompatActivity implements OnTouchingLetterChangeListener {

    @BindView(R.id.home_page_banner_toolbar_title)
    TextView homePageBannerToolbarTitle;
    @BindView(R.id.home_page_banner_toolbar_image)
    TextView homePageBannerToolbarImage;
    @BindView(R.id.home_page_banner_toolbar_bnt)
    TextView homePageBannerToolbarBnt;
    @BindView(R.id.home_page_toolbar)
    Toolbar homePageToolbar;
    @BindView(R.id.select_city)
    Button selectCity;
    @BindView(R.id.mainlist)
    ListView mainlist;
    @BindView(R.id.myview)
    MySideBar myview;
    private ListView mainList;
    private MySideBar myView;
    private TextView tvMain;
    private List<String> data = new ArrayList<String>();
    private List<Integer> letterCharList = new ArrayList<>();
    private List<Integer> letterPositionList = new ArrayList<>();
    private String[] title = {"当前城市", "热门城市", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private int lastFirstVisibleItem;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);

        mainList = (ListView) findViewById(R.id.mainlist);
        myView = (MySideBar) findViewById(R.id.myview);
        tvMain = (TextView) findViewById(R.id.main_tv);
        myView.setOnTouchingLetterChangedListener(this);
        tvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("city", city);
                setResult(100);
                finish();
            }
        });
        int index = 0, position = 0;
        letterCharList.add(index);
        for (int i = 0; i < CityData.array.length; i++) {
            for (int j = 0; j < CityData.array[i].length; j++) {
                if (i == 0 && j == 0) {
                    index++;
                    letterPositionList.add(position);
                } else if (j == 0) {
                    letterCharList.add(index);
                    letterPositionList.add(position);
                    index++;
                } else {
                    letterCharList.add(-1);
                }
                position++;
                data.add(CityData.array[i][j]);
            }
            CityListAdapter adapter = new CityListAdapter(this, data, letterCharList, title);
            mainList.setAdapter(adapter);
            mainList.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (letterCharList.get(firstVisibleItem) >= 0) {
                        tvMain.setText(title[letterCharList.get(firstVisibleItem)]);
                        if (TextUtils.isEmpty(city)) {
                            tvMain.setText("当前城市");
                        } else {
                            tvMain.setText("当前城市" + city);
                        }

                        lastFirstVisibleItem = firstVisibleItem;
                    } else {
                        if (lastFirstVisibleItem > firstVisibleItem) {
                            tvMain.setText(city);
                        }
                    }
                }
            });
            //点击item项，转换成要定位的城市
            mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(getApplicationContext(), "当前城市" + mainList.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                    tvMain.setText((CharSequence) mainList.getItemAtPosition(position));//将城市定位的位置换成点击的城市
                }
            });
        }
        initToorBar();
    }

    /**
     * 设置toolBar
     */
    private void initToorBar() {
        homePageBannerToolbarTitle.setText("城市列表");
        homePageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onTouchingLetterChanged(int s) {
        mainList.setSelection(letterPositionList.get(s));
    }

    @OnClick(R.id.select_city)
    public void onClick() {
        //选中城市
        if (!TextUtils.isEmpty(tvMain.getText().toString())) {
            Intent intent = new Intent();
            intent.putExtra("city", tvMain.getText().toString());
            setResult(100, intent);
            finish();
        }
    }

}
