package com.razerblade.restaurant.kasir;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.razerblade.restaurant.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by waski on 04/05/2018.
 */

public class KasirAdapter extends RecyclerView.Adapter<KasirAdapter.MyViewHolder> {
    private CardView cardview;
    private Context context;
    private final List<String> listKasir;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama,nomorKasir;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNama =(TextView)itemView.findViewById(R.id.tvNamaKasir);
            nomorKasir = (TextView)itemView.findViewById(R.id.nomorKasir);
            cardview = (CardView)itemView.findViewById(R.id.cvKasir);
        }
    }
    public KasirAdapter(List<String>listKasir, Context context){
        this.listKasir = listKasir;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kasir_adapter,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final String itemKasir = listKasir.get(position);
        int kasir = position+1;
        holder.nomorKasir.setText(""+kasir);
        holder.tvNama.setText(itemKasir);
        Log.d("CACAD1",itemKasir);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,KasirDetail.class);
                i.putExtra("Ukey",itemKasir);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKasir.size();
    }


}
