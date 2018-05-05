package com.razerblade.restaurant.kasir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.razerblade.restaurant.R;

import java.util.List;

/**
 * Created by waski on 05/05/2018.
 */

public class KasirDetailAdapter extends RecyclerView.Adapter<KasirDetailAdapter.MyViewHolder> {
    private List<KasirCons>KasirList;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama,tvJumlah,tvHarga;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNama =(TextView)itemView.findViewById(R.id.tvKasirMakanan);
            tvJumlah = (TextView)itemView.findViewById(R.id.tvKasirJumlah);
            tvHarga = (TextView)itemView.findViewById(R.id.tvKasirHarga);
        }
    }
    public  KasirDetailAdapter(List<KasirCons> KasirList, Context context){
        this.KasirList = KasirList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kasri_harga,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    final KasirCons listDetail = KasirList.get(position);
    holder.tvNama.setText(listDetail.getIdMakanan());
    holder.tvJumlah.setText(listDetail.getJumlahMakanan());
    holder.tvHarga.setText(listDetail.getHarga());
    }

    @Override
    public int getItemCount() {
        return KasirList.size();
    }


}
