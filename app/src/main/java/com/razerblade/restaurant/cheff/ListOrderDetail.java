package com.razerblade.restaurant.cheff;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.razerblade.restaurant.FirebaseC;
import com.razerblade.restaurant.R;
import com.razerblade.restaurant.pelanggan.ConBeli;

import java.util.ArrayList;

public class ListOrderDetail extends AppCompatActivity {
    private ArrayList<LisOrderConsChild> listchild;
    private ListOrderDetailAdapter mAdapter;
    private ListOrderAdapter mAdapter1;
    private TextView mMeja;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order_detail);
        displayHomeAsUpEnabled();
        mMeja = findViewById(R.id.NoMejaDetail);
        listchild = new ArrayList<>();
        RecyclerView rvList =(RecyclerView)findViewById(R.id.rvListOrderDetail) ;
        rvList.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new ListOrderDetailAdapter(listchild,this);
        rvList.setAdapter(mAdapter);
        final String keyx = getIntent().getStringExtra("FKey");
        mButton = (Button)findViewById(R.id.pesananSelesai);
        String key = FirebaseC.refBayar.push().getKey();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseC.refOrder.child(keyx)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(final DataSnapshot ds1 : dataSnapshot.getChildren()){
                                    LisOrderConsChild dataOrder = ds1.getValue(LisOrderConsChild.class);
                                  FirebaseC.refBayar.child(keyx).child(ds1.getKey()).setValue(new LisOrderConsChild(
                                          dataOrder.getIdMakanan(),
                                          dataOrder.getJumlahMakanan(),
                                          dataOrder.getHarga(),
                                          dataOrder.getMeja(),
                                          dataOrder.getStatusBayar(),
                                          "Selesai"
                                  ));

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                FirebaseC.refOrder.child(keyx).removeValue();

            }


        });

        FirebaseC.refOrder.child(keyx)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            listchild.clear();
            for(final DataSnapshot ds1 : dataSnapshot.getChildren()){
                LisOrderConsChild dataOrder1 = ds1.getValue(LisOrderConsChild.class);
                mMeja.setText("Meja "+dataOrder1.getMeja());
                listchild.add(dataOrder1);
                mAdapter.notifyDataSetChanged();
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}


