package com.example.deliveryboyapp.ui.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliveryboyapp.R;
import com.example.deliveryboyapp.databinding.SingleOrderNewBinding;
import com.example.deliveryboyapp.remote.models.OrderDatum;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {


    Activity context;
    ArrayList<OrderDatum> ordersArrayList;
    private View.OnClickListener mOnItemClickListener;


    public RecyclerViewAdapter(MainActivity context, ArrayList<OrderDatum> ordersArrayList) {
        this.context = context;
        this.ordersArrayList = ordersArrayList;
    }
    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.single_order,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        OrderDatum order=ordersArrayList.get(position);
        holder.orderIdText.setText(order.getOrderId());
        holder.userIdText.setText(order.getUserId());
        holder.totalIdText.setText(order.getTotal());
    }

    @Override
    public int getItemCount() {
        return ordersArrayList.size();
    }

    public void setmOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;

    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder{
        TextView orderIdText,userIdText,totalIdText;
        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            orderIdText=itemView.findViewById(R.id.order_id_value);
            userIdText=itemView.findViewById(R.id.user_id_value);
            totalIdText=itemView.findViewById(R.id.total_id_value);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
