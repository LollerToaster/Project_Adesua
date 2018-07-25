package com.project.adrianangub.project_adesua;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class HomeFourFragment extends Fragment {
    public static HomeFourFragment newInstance() {
        HomeFourFragment fragment = new HomeFourFragment();
        return fragment;
    }

    ListView lv_pdf;
    public static ArrayList<File> fileList = new ArrayList<File>();
    PDFAdapter obj_adapter;
    //public static int REQUEST_PERMISSIONS = 1;
    //boolean boolean_permission;
    File dir;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.home_fragment_four, container, false);

        View view = inflater.inflate(R.layout.home_fragment_four, container, false);

        lv_pdf = (ListView)view.findViewById(R.id.lv_pdf);
        //dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

        //Check The Directory
        dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Adesua/");
        getfile(dir);

        obj_adapter = new PDFAdapter(getContext(), fileList);
        lv_pdf.setAdapter(obj_adapter);

        lv_pdf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), PDFActivity.class);
                intent.putExtra("position", i);
                startActivity(intent);

                Log.e("Position", i + "");
            }
        });
        return view;
    }



    public ArrayList<File> getfile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);

                } else {

                    boolean booleanpdf = false;
                    if (listFile[i].getName().endsWith(".pdf")) {

                        for (int j = 0; j < fileList.size(); j++) {
                            if (fileList.get(j).getName().equals(listFile[i].getName())) {
                                booleanpdf = true;
                            } else {

                            }
                        }

                        if (booleanpdf) {
                            booleanpdf = false;
                        } else {
                            fileList.add(listFile[i]);

                        }
                    }
                }
            }
        }
        return fileList;
    }

    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                boolean_permission = true;
                getfile(dir);

                obj_adapter = new PDFAdapter(getContext(), fileList);
                lv_pdf.setAdapter(obj_adapter);

            } else {
                Toast.makeText(getContext(), "Please allow the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    */
}

