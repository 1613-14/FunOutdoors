package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.SpecialAdapter;
import com.phone.funoutdoors.bean.ConsultLVHeaderData;

import java.util.List;

public class SpecialActivity extends AppCompatActivity {

    private ConsultLVHeaderData consultLVHeaderData;
    private ListView mListView;
    private ImageView image_back;
    private List<ConsultLVHeaderData.ResultListBean> resultList;
    private SpecialAdapter specialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        Intent intent = getIntent();
        if (intent != null) {
            consultLVHeaderData = (ConsultLVHeaderData) intent.getSerializableExtra("consultLVHeaderData");
        }

        initView();
    }

    private void initView() {
        image_back = (ImageView) findViewById(R.id.back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mListView = (ListView) findViewById(R.id.more_lv);
        resultList = consultLVHeaderData.getResultList();

        specialAdapter = new SpecialAdapter(resultList, this);
        mListView.setAdapter(specialAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SpecialActivity.this, ConsultHeaderDetailsActivity.class);
                intent.putExtra("headerTitle", consultLVHeaderData.getResultList().get(0).getFeature_title());
                intent.putExtra("feature_content", consultLVHeaderData.getResultList().get(0).getFeature_content());
                intent.putExtra("feature_cover", consultLVHeaderData.getResultList().get(0).getFeature_cover());
                startActivity(intent);
            }
        });
    }
}
