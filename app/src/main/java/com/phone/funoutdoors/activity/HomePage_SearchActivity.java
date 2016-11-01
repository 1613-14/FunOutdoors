package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.phone.funoutdoors.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePage_SearchActivity extends AppCompatActivity {
    @BindView(R.id.search_type)
    TextView search_type;
    @BindView(R.id.search_content)
    EditText search_content;
    @BindView(R.id.search_commit)
    TextView search_commit;
    @BindView(R.id.cancel_search)
    ImageView cancel_search;
    private int width, searchFlg = 1;
    private String[] titles = new String[]{ "游记", "线路", "达人", "景点", "趣播"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_search);
        ButterKnife.bind(this);
        width = getResources().getDisplayMetrics().widthPixels;
        search_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    cancel_search.setVisibility(View.VISIBLE);
                    search_commit.setText("搜索");
                } else {
                    cancel_search.setVisibility(View.GONE);
                    search_commit.setText("取消");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    /**
     * 设置弹出窗体
     */
    private void setPopWindow() {
        View view = View.inflate(HomePage_SearchActivity.this, R.layout.activity_home_page_more_scene_pop, null);
        ListView more_scene_pop = (ListView) view.findViewById(R.id.more_scene_pop);
        ArrayAdapter adapter = new ArrayAdapter(HomePage_SearchActivity.this, R.layout.activity_home_page_more_scene_pop_item, R.id.dialog_listview, titles);
        more_scene_pop.setAdapter(adapter);
        view.setBackgroundResource(R.mipmap.model_pop_bg_c);
        final PopupWindow pop = new PopupWindow(view, width / 4, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.showAsDropDown(search_type, -width / 4 + 25, 25);
        more_scene_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        searchFlg = 3;
                        break;
                    case 1:
                        searchFlg = 2;
                        break;
                    case 2:
                        searchFlg = 4;
                        break;
                    case 3:
                        searchFlg = 1;
                        break;
                    case 4:
                        searchFlg = 5;
                        break;
                }
                search_type.setText(titles[position]);
                search_content.setText("");
                pop.dismiss();
            }
        });
    }

    @OnClick({R.id.search_type,R.id.cancel_search, R.id.search_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_type:
                setPopWindow();
                break;
            case R.id.cancel_search:
                search_content.setText("");
                break;
            case R.id.search_commit:
                if (search_commit.getText().equals("搜索")) {
                    Intent intent = new Intent(this, HomePage_Search_ItemActivity.class);
                    intent.putExtra("keyword", search_content.getText().toString());
                    intent.putExtra("flag", searchFlg);
                    intent.putExtra("search_type", search_type.getText().toString());
                    startActivity(intent);
                } else {
                    finish();
                }
                break;
        }
    }

}

