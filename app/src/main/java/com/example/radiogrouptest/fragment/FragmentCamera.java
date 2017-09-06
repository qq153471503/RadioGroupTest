package com.example.radiogrouptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static com.example.radiogrouptest.R.*;


/**
 * Created by KunGe on 2017/8/22.
 */

public class FragmentCamera extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(layout.fragment_camera, container, false);
    }
}
