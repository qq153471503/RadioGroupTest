package com.example.radiogrouptest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.radiogrouptest.R;
import com.example.radiogrouptest.activity.TestActivity;

/**
 * Created by KunGe on 2017/8/22.
 */

public class FragmentHomePage extends Fragment {
    private Button button_home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        button_home = (Button)view.findViewById(R.id.button_homepage);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TestActivity.class));
            }
        });
        return view;
    }
}


