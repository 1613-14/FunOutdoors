package com.phone.funoutdoors.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phone.funoutdoors.R;
import com.phone.funoutdoors.adapter.OutdoorViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutdoorPageFragment extends Fragment {
    View view;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    OutdoorViewPagerAdapter adapter;
    //tab标题
    String[] titles = {"热门", "关注", "约伴"};
    private TextView text_countryWide;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_outdoor_page, container, false);

        initView();
        //tab和viewPager关联
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void initView() {
        mViewPager = (ViewPager) view.findViewById(R.id.outdoor_viewPager);
        mTabLayout = ((TabLayout) view.findViewById(R.id.outdoor_tabLayout));
        text_countryWide = ((TextView) view.findViewById(R.id.countryWide));
        initTabLayout();
        initViewPager();
    }

    //添加ViewPager
    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
//        for (int i = 0; i < titles.length; i++) {
//            OutdoorContentFragment outdoorContentFragment = new OutdoorContentFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt(Constant.OUTDOOR_POS, i);
//            outdoorContentFragment.setArguments(bundle);
//            fragments.add(outdoorContentFragment);
//        }
        HotFragment hotFragment = new HotFragment();
        DateFragment dateFragment = new DateFragment();
        AttentionFragment attentionFragment = new AttentionFragment();

        fragments.add(hotFragment);
        fragments.add(attentionFragment);
        fragments.add(dateFragment);

        adapter = new OutdoorViewPagerAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);

        //显示隐藏全国
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        text_countryWide.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        text_countryWide.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        text_countryWide.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //添加标题
    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titles.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles[i]));
        }
    }
}
