package com.example.deliveryboyapp.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import com.example.deliveryboyapp.R;
import com.example.deliveryboyapp.databinding.ActivityOrderDetailsBinding;
import com.example.deliveryboyapp.remote.models.OrderDatum;

public class OrderDetailsActivity extends AppCompatActivity {
    private ActivityOrderDetailsBinding binding;
    OrderDatum order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getSupportActionBar() != null;
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Order Details");
        binding= DataBindingUtil.setContentView(OrderDetailsActivity.this,R.layout.activity_order_details);
        if(getIntent().getExtras() != null) {
             order= (OrderDatum) getIntent().getSerializableExtra("order");
        }
        binding.setOrder(order);
        binding.tvRazorPayId.setText(order.getRazorpayId());
    }
}
