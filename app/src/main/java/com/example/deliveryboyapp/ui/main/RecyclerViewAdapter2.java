package com.example.deliveryboyapp.ui.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliveryboyapp.R;
import com.example.deliveryboyapp.databinding.SingleOrderNewBinding;
import com.example.deliveryboyapp.remote.models.OrderDatum;

import java.util.ArrayList;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.RecyclerViewViewHolder> {

    Activity context;
    ArrayList<OrderDatum> ordersArrayList;
    private View.OnClickListener mOnItemClickListener;

    public RecyclerViewAdapter2(MainActivity context, ArrayList<OrderDatum> ordersArrayList) {
        this.context = context;
        this.ordersArrayList = ordersArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter2.RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleOrderNewBinding singleOrderNewBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.single_order_new, parent, false);
        return new RecyclerViewViewHolder(singleOrderNewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter2.RecyclerViewViewHolder holder, int position) {
        OrderDatum currentOrder = ordersArrayList.get(position);
        holder.singleOrderNewBinding.setOrder(currentOrder);
    }

    @Override
    public int getItemCount() {
        return ordersArrayList.size();
    }

    public void setmOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;

    }

    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        private SingleOrderNewBinding singleOrderNewBinding;
        public RecyclerViewViewHolder(@NonNull SingleOrderNewBinding singleOrderNewBinding) {
            super(singleOrderNewBinding.getRoot());
            this.singleOrderNewBinding=singleOrderNewBinding;
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}

