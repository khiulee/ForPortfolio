package com.example.forportfolio;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class main_fragment extends Fragment {
    Button btn_toStart;
    Button btn_toSetting;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_fragment, container, false);
        // Inflate the layout for this fragment
        btn_toStart = rootView.findViewById(R.id.btn_toStart);
        btn_toStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onFragmentChange(MainActivity.FRAGMENT_PURCHASE,null);
            }
        });
        btn_toSetting = rootView.findViewById(R.id.btn_toSetting);
        btn_toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onFragmentChange(MainActivity.FRAGMENT_SETTING,null);
            }
        });
        return rootView;
    }
}