package com.phone.funoutdoors.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone.funoutdoors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutdoorPageFragment extends Fragment {
    private View view= null;
    public OutdoorPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            if (view == null){
                view = inflater.inflate(R.layout.fragment_outdoor_page, container, false);
            }
            return view;
    }
}
