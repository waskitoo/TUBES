package com.razerblade.restaurant.pelanggan;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

/**
 * Created by waski on 25/04/2018.
 */

public class MenuTampilAdapter extends RecyclerView.Adapter<MenuTampilAdapter.MyViewHolder> {
    private List<MAdminInput> photoList;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvnama,tvharga;
        public ImageView imgPhoto;
        public CardView cvPhoto;
        public Button buttonAddChart;
        public MyViewHolder(View view) {
            super(view);
            imgPhoto = (ImageView)view.findViewById(R.id.imgPhoto);
            tvnama = (TextView)view.findViewById(R.id.TextviewRowtTitle);
            tvharga = (TextView)view.findViewById(R.id.TextviewRowtHarga);
            cvPhoto = (CardView)view.findViewById(R.id.cvPhoto);
        }
    }
    public MenuTampilAdapter(List<MAdminInput> photoList, Context context){
        this.photoList = photoList;
        this.context =context;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_menu,
                        parent,
                        false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MAdminInput photo = photoList.get(position);
        holder.tvnama.setText(photo.getNama());
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        holder.tvharga.setText(kursIndonesia.format(photo.getHarga()));
        Picasso.get().load(photo.getImage_url()).into(holder.imgPhoto);
        holder.cvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ia = new Intent(context,DetailMenu.class);
                ia.putExtra("dataMenu",photo);
                context.startActivity(ia);
            }
        });

        //View mView = View.inflate(R.layout.detail_menu,null);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
