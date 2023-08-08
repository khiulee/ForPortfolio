package com.example.forportfolio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class purchase_fragment extends Fragment {
    Button btn_purchase;
    boolean isPurcValid=true;
    public purchase_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_purchase_fragment, container, false);
        btn_purchase = rootView.findViewById(R.id.btn_purchase);
        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfPurcValid();
                if(isPurcValid) {
                    ((MainActivity)getActivity()).onFragmentChange(MainActivity.FRAGMENT_BOOKLIST,null);
                }
                else{
                    Toast.makeText((MainActivity)getActivity().getApplicationContext(),"계좌번호가 유효하지 않습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
    private void checkIfPurcValid(){
        /**
         * 계좌번호가 유효한지 알아봐야지.
         *
         * **/
        isPurcValid = true;
    }
}