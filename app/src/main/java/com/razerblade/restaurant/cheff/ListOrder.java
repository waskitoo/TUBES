package com.razerblade.restaurant.cheff;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.razerblade.restaurant.FirebaseC;
import com.razerblade.restaurant.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListOrder extends Fragment {
    private ArrayList orderList;
    private ListOrderAdapter mAdapter;
    SwipeRefreshLayout swipeRefresh;

    public ListOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_order, container, false);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        orderList = new ArrayList<>();
        RecyclerView rvOrder = (RecyclerView)view.findViewById(R.id.rvListOrder);
        rvOrder.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new ListOrderAdapter(orderList,getActivity());
        rvOrder.setAdapter(mAdapter);

        loadList();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadList();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void loadList(){
        swipeRefresh.setRefreshing(true);
        FirebaseC.refOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderList.clear();
                for (final DataSnapshot ds : dataSnapshot.getChildren()){
                    String listDaftar = ds.getKey();
                            orderList.add(listDaftar);
                            mAdapter.notifyDataSetChanged();
                }
                swipeRefresh.setRefreshing(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
