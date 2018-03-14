package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class ItemOneFragment extends Fragment {
    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_one, container, false);

        //

        ImageButton btn1 = (ImageButton) rootView.findViewById(R.id.bookImage1); // you have to use rootview object..
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                Toast.makeText(getActivity(),"Hitler", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), bookInfoPage_v2.class);
                startActivity(intent);
            }
        });

        return rootView;

        //


    }



}
