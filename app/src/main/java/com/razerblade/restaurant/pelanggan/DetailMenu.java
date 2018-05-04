package com.razerblade.restaurant.pelanggan;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razerblade.restaurant.FirebaseC;
import com.razerblade.restaurant.R;
import com.razerblade.restaurant.admin.MAdminInput;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailMenu extends AppCompatActivity {
    private ImageView imgDetail;
    private TextView textTitle,textharga,textDeskripsi,jumlahTextView;
    private Button buttonDetail,plusButton,minusButton,mOrderButton;
    private int jumlah =1;
    public int hitung;
    MAdminInput dataMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        SharedPreferences sharedPreferences = getSharedPreferences("Preference", Context.MODE_PRIVATE);
        Toast.makeText(this,sharedPreferences.getString("meja",""),Toast.LENGTH_LONG).show();
        displayHomeAsUpEnabled();
        imgDetail = (ImageView)findViewById(R.id.imgDetail);
        textTitle = (TextView) findViewById(R.id.textViewTitle);
        textharga = (TextView)findViewById(R.id.textViewHarga);
        textDeskripsi = (TextView)findViewById(R.id.textViewDeskrip);
        jumlahTextView = (TextView)findViewById(R.id.jumlahTextView);
        plusButton = (Button)findViewById(R.id.plusButton);
        minusButton = (Button)findViewById(R.id.minusButton);
        mOrderButton = (Button)findViewById(R.id.pesan);

        dataMenu = (MAdminInput)getIntent().getSerializableExtra("dataMenu");

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderMenu();
            }
        });
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah ++;
                jumlahTextView.setText(""+jumlah);
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jumlah <= 1){
                jumlah --;
                jumlahTextView.setText(""+jumlah);}
            }
        });

        loadData();

    }
    private void loadData(){
        if(getIntent().getExtras() != null){

            Picasso.get().load(dataMenu.getImage_url()).into(imgDetail);
            textTitle.setText(dataMenu.getNama());
            textDeskripsi.setText(dataMenu.getDeskripsi());
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);
            textharga.setText(kursIndonesia.format(dataMenu.getHarga()));

        }

    }
    private void orderMenu(){
        hitung = jumlah*Integer.parseInt(String.valueOf(dataMenu.getHarga()));
        String key = FirebaseC.refOrder.push().getKey();
        SharedPreferences sPref = getSharedPreferences("Preference", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("order").child(FirebaseC.mAuth.getCurrentUser().getEmail().toString().
                split("@")[0]);
         ref.child(dataMenu.getKey()).
                setValue(new ConBeli(
                dataMenu.getNama(),
                jumlahTextView.getText().toString(),
                ""+hitung,
                sPref.getString("meja",""),
                "Belum Selesai",
                "Belum Selesai"
        ));
        Toast.makeText(this, "Uploaded!", Toast.LENGTH_SHORT).show();
    }
    //handler jika back button di klik
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
