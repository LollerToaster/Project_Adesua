package com.project.adrianangub.project_adesua;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//REFERENCE
//http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/

public class VirtualClassroomTwoFragment extends Fragment {

    public static VirtualClassroomTwoFragment newInstance() {
        VirtualClassroomTwoFragment fragment = new VirtualClassroomTwoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.virtual_classroom_fragment_two, container, false);
    }
}