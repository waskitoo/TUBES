package com.razerblade.restaurant.kasir;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razerblade.restaurant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KasirDetail extends Fragment {


    public KasirDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kasir_detail, container, false);
    }

}
