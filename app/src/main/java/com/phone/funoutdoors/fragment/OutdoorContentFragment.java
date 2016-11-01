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
public class OutdoorContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_outdoor_content, container, false);
        return view;
    }

}
