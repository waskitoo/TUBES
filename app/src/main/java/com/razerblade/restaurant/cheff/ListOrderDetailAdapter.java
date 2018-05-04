package com.razerblade.restaurant.cheff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.razerblade.restaurant.R;
import com.razerblade.restaurant.pelanggan.ConBeli;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by waski on 03/05/2018.
 */

public class ListOrderDetailAdapter extends RecyclerView.Adapter<ListOrderDetailAdapter.MyViewHolder> {
    private List<LisOrderConsChild> ListChild;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView noMeja,namaDetail,jumlahDetai;
        public MyViewHolder(View itemView) {
            super(itemView);
            noMeja = (TextView)itemView.findViewById(R.id.NoMejaDetail);
            namaDetail = (TextView)itemView.findViewById(R.id.detailOrderNamaMakanan);
            jumlahDetai = (TextView)itemView.findViewById(R.id.detailOrderJumlah);
        }
    }
    public ListOrderDetailAdapter(List<LisOrderConsChild> photoList, Context context){
        this.ListChild = photoList;
        this.context =context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.order_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final LisOrderConsChild menuDetai = ListChild.get(position);
        holder.namaDetail.setText(menuDetai.getIdMakanan());
        holder.jumlahDetai.setText(menuDetai.getJumlahMakanan());

    }

    @Override
    public int getItemCount() {return ListChild.size();
    }


}
