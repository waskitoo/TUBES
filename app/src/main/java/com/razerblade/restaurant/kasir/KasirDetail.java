package com.razerblade.restaurant.kasir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.razerblade.restaurant.FirebaseC;
import com.razerblade.restaurant.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class KasirDetail extends AppCompatActivity {
    private ArrayList<KasirCons> listKasir;
    private KasirDetailAdapter mAdapter;
    private Button btnbayar,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnclr,btn100,btn20;
    private Button btn30,btn40,btn500,btn1000;
    private TextView tvTunai,tvKembai;
    String tunai ="";
    int jumlah;
    private TextView tvTot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kasir_detail);
        getWindow().setSoftInputMode(

                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

        );
        final DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        tvKembai =(TextView)findViewById(R.id.textKembali);
        tvTunai =(EditText)findViewById(R.id.editTextnoinal);
        tvTot = (TextView)findViewById(R.id.textTot);
        inisialisasiButton();
        btnbayar =(Button)findViewById(R.id.btnbayar);
        listKasir = new ArrayList<>();
        final RecyclerView rvKasir = (RecyclerView)findViewById(R.id.rvListKasir);
        rvKasir.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new KasirDetailAdapter(listKasir,this);
        rvKasir.setAdapter(mAdapter);
        final String key = getIntent().getStringExtra("Ukey");
        FirebaseC.refBayar.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            listKasir.clear();
            for (final DataSnapshot ds : dataSnapshot.getChildren()){
                KasirCons dataList = ds.getValue(KasirCons.class);
                jumlah += Integer.parseInt(dataList.getHarga());
                tvTot.setText(""+kursIndonesia.format(jumlah));
                listKasir.add(dataList);
                mAdapter.notifyDataSetChanged();
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        allButnon();

    }
    private void allButnon(){
        btnbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvTunai.getText().toString().isEmpty()){
                    final DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp. ");
                    formatRp.setGroupingSeparator('.');
                    kursIndonesia.setDecimalFormatSymbols(formatRp);
                    int total;
                    total = Integer.parseInt(tunai)-jumlah;
                    tvKembai.setText(""+kursIndonesia.format(total));
                    final String key = getIntent().getStringExtra("Ukey");
                    FirebaseC.refBayar.child(key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (final DataSnapshot ds : dataSnapshot.getChildren()){
                                KasirCons dataKasir = ds.getValue(KasirCons.class);
                                String keyx = FirebaseC.refHistory.push().getKey();
                                FirebaseC.refHistory.child(key).child(keyx).setValue(new KasirCons(
                                        dataKasir.getIdMakanan(),
                                        dataKasir.getJumlahMakanan(),
                                        dataKasir.getHarga(),
                                        dataKasir.getMeja(),
                                        "Selesai",
                                        "Selesai"
                                ));
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    FirebaseC.refBayar.child(key).removeValue();

                }else {
                    tvTunai.setError("Required");
                }
                }

        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "0";
                tvTunai.setText(""+tunai);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "1";
                tvTunai.setText(""+tunai);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "2";
                tvTunai.setText(""+tunai);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "3";
                tvTunai.setText(""+tunai);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "4";
                tvTunai.setText(""+tunai);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "5";
                tvTunai.setText(""+tunai);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "6";
                tvTunai.setText(""+tunai);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "7";
                tvTunai.setText(""+tunai);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "8";
                tvTunai.setText(""+tunai);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai += "9";
                tvTunai.setText(""+tunai);
            }
        });
        btnclr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai = "";
                tvTunai.setText(""+tunai);
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai = "100000";
                tvTunai.setText(""+tunai);
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai = "200000";
                tvTunai.setText(""+tunai);
            }
        });
        btn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai = "300000";
                tvTunai.setText(""+tunai);
            }
        });
        btn40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai = "400000";
                tvTunai.setText(""+tunai);
            }
        });
        btn500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai = "5000000";
                tvTunai.setText(""+tunai);
            }
        });
        btn1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tunai = "1000000";
                tvTunai.setText(""+tunai);
            }
        });

}
    private void inisialisasiButton(){
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnclr = (Button)findViewById(R.id.btnclr);
        btn100 = (Button)findViewById(R.id.btn100);
        btn20 = (Button)findViewById(R.id.btn20);
        btn30 = (Button)findViewById(R.id.btn30);
        btn40 = (Button)findViewById(R.id.btn40);
        btn500 = (Button)findViewById(R.id.btn500);
        btn1000 = (Button)findViewById(R.id.btn10000);
    }


}
