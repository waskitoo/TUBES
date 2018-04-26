package com.razerblade.restaurant.pelanggan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.razerblade.restaurant.FirebaseC;
import com.razerblade.restaurant.R;
import com.razerblade.restaurant.admin.MAdminInput;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuTampil extends Fragment {
    //private RecyclerView rvMenu;
    private ArrayList<MAdminInput> menuList;
    private MenuTampilAdapter mAdapter;
    private Bundle bundle;
    private String jenis;

    public MenuTampil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmenView = inflater.inflate(R.layout.fragment_menu_tampil, container, false);
        menuList = new ArrayList<>();
        RecyclerView rvMenu = (RecyclerView)fragmenView.findViewById(R.id.rvMenu);
        rvMenu.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new MenuTampilAdapter(menuList,getActivity());
        rvMenu.setAdapter(mAdapter);
        bundle = this.getArguments();
        jenis = bundle.getString("jenis");
        loadMenu();
        // Inflate the layout for this fragment
        return fragmenView;
    }
    private void loadMenu(){
        if(jenis.equals("makanan")){
            FirebaseC.refPhoto.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    menuList.clear();
                    for(final DataSnapshot ds : dataSnapshot.getChildren()){
                        MAdminInput datasMakanan = ds.getValue(MAdminInput.class);
                        if(datasMakanan.getJenis().equals("Makanan")){
                            menuList.add(datasMakanan);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Firebase:Load ","Gagal");
                }
            });
        }else {FirebaseC.refPhoto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menuList.clear();
                for(final DataSnapshot ds : dataSnapshot.getChildren()){
                    MAdminInput datasMakanan = ds.getValue(MAdminInput.class);
                    if(datasMakanan.getJenis().equals("Minuman")){
                        menuList.add(datasMakanan);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase:Load ","Gagal");
            }
        });

        }

    }


}
