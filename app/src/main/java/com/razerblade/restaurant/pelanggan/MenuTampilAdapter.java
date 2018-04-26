package com.razerblade.restaurant.pelanggan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.razerblade.restaurant.R;
import com.razerblade.restaurant.admin.MAdminInput;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder( MyViewHolder holder, int position) {
        final MAdminInput photo = photoList.get(position);
        holder.tvnama.setText(photo.getNama());
        holder.tvharga.setText("RP. "+photo.getHarga());
        Picasso.get().load(photo.getImage_url()).into(holder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }


}
