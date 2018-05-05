package com.razerblade.restaurant.kasir;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
public class Kasir extends Fragment {
    private ArrayList kasirList;
    private KasirAdapter mAdapter;
    private SwipeRefreshLayout swp;
  //  SwipeRefreshLayout swipeRefreshLayout;
    public Kasir() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kasir, container, false);
        kasirList = new ArrayList();
        swp =(SwipeRefreshLayout)view.findViewById(R.id.swp);
        RecyclerView rvKasira = (RecyclerView)view.findViewById(R.id.rvKasir1);
        rvKasira.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new KasirAdapter(kasirList,getContext());
        rvKasira.setAdapter(mAdapter);
        loadData();
        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        return view;
    }
    private void loadData(){
        swp.setRefreshing(true);
        FirebaseC.refBayar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                kasirList.clear();
                for (final  DataSnapshot ds : dataSnapshot.getChildren()){
                    String listKasir = ds.getKey();
                    kasirList.add(listKasir);
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        swp.setRefreshing(false);
    }

}
