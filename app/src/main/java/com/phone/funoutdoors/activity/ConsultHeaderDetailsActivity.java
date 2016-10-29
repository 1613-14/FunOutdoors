package com.phone.funoutdoors.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.ConsultHeaderLVAdapter;
import com.phone.funoutdoors.bean.ConsultHeaderDetailsLVData;
import com.phone.funoutdoors.interfaces.GetConsultHeaderData;
import com.phone.funoutdoors.interfaces.GetConsultLVHeaderData;
import com.phone.funoutdoors.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultHeaderDetailsActivity extends AppCompatActivity {

    private String title;
    private TextView text_title;
    private ImageView image_back;
    private ListView mListView;
    private String feature_content;
    private String feature_cover;
    private List<ConsultHeaderDetailsLVData.ResultListBean> allList = new ArrayList<>();
    private ConsultHeaderLVAdapter adapter;
    private View headerView;
    private ImageView headerImage;
    private TextView headerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_header);
        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra("headerTitle");
            feature_content = intent.getStringExtra("feature_content");
            feature_cover = intent.getStringExtra("feature_cover");
        }
        initHeaderView();
        initView();

        getDataFromNet();
    }

    private void initHeaderView() {
        headerView = View.inflate(this, R.layout.consult_header_detail_header, null);
        headerImage = (ImageView) headerView.findViewById(R.id.consult_header_details_image);
        headerText = (TextView) headerView.findViewById(R.id.consult_header_details_text);
        headerText.setText(feature_content);
        Glide.with(this).load(Constant.SPILT_IMG_URL + feature_cover).placeholder(R.mipmap.ic_launcher).into(headerImage);
    }

    private void initView() {
        text_title = (TextView) findViewById(R.id.title);
        image_back = (ImageView) findViewById(R.id.back);
        mListView = (ListView) findViewById(R.id.consult_header_details_listview);

        //添加头
        mListView.addHeaderView(headerView);
        adapter = new ConsultHeaderLVAdapter(allList, this);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    Intent intent = new Intent(ConsultHeaderDetailsActivity.this, WebActivity.class);
                    intent.putExtra("title", "趣户外新闻");
                    intent.putExtra("wapUrl", allList.get(position - 1).getWapurl());
                    startActivity(intent);
                }
            }
        });
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        text_title.setText(title);
    }

    private void getDataFromNet() {
        /**
         详细页：http://www.quhuwai.cn/webservice/qhw1001/func_sub1205
         请求方式；POST
         请求头：Content-Type: application/x-www-form-urlencoded
         请求体：pageSize=20&featureId=4&pageNo=1
         */
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.quhuwai.cn/").addConverterFactory(GsonConverterFactory.create()).build();
        //进行拼接,获取接口对象
        GetConsultHeaderData getConsultHeaderData = retrofit.create(GetConsultHeaderData.class);
        //通过接口获取请求
        Call<ConsultHeaderDetailsLVData> call = getConsultHeaderData.getConsultHeaderData(20, 4, 1);
        //进行请求
        call.enqueue(new Callback<ConsultHeaderDetailsLVData>() {
            @Override
            public void onResponse(Call<ConsultHeaderDetailsLVData> call, Response<ConsultHeaderDetailsLVData> response) {
                List<ConsultHeaderDetailsLVData.ResultListBean> resultList = response.body().getResultList();

                allList.addAll(resultList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ConsultHeaderDetailsLVData> call, Throwable t) {

            }
        });
    }


}
