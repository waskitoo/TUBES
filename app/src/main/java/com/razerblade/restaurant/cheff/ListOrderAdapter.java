package com.razerblade.restaurant.cheff;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.razerblade.restaurant.R;

import java.util.List;

/**
 * Created by waski on 03/05/2018.
 */

public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.MyViewHolder> {
    private final List<String> listOrder;
    private Context context;
    private CardView cardView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama,nomorPesan;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tvNamaListOrder);
            nomorPesan = (TextView)itemView.findViewById(R.id.nomorPesan);
            cardView = (CardView)itemView.findViewById(R.id.cvListOrder);
        }
    }
    public ListOrderAdapter(List<String>listOrder,Context context){
        this.listOrder = listOrder;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chef_adapter,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final String itemOrder = listOrder.get(position);
        int pesanan = position+1;
        holder.nomorPesan.setText(""+pesanan);
        Log.d("cacad3:",itemOrder);
        holder.tvNama.setText(itemOrder);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ListOrderDetail.class);
                i.putExtra("FKey",itemOrder);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }


}
