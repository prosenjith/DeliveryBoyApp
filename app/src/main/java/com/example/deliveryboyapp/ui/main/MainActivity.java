package com.example.deliveryboyapp.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.deliveryboyapp.R;
import com.example.deliveryboyapp.remote.models.OrderDatum;
import com.example.deliveryboyapp.remote.models.OrdersResponse;
import com.example.deliveryboyapp.ui.LoginActivity;
import com.example.deliveryboyapp.ui.OrderDetailsActivity;
import com.example.deliveryboyapp.viewmodels.MainActivityViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private ProgressBar progressBar;
    MainActivityViewModel viewModel;
    RecyclerView recyclerView;
    RecyclerViewAdapter2 recyclerViewAdapter;
    ArrayList<OrderDatum> orders =new ArrayList<>();
    MainActivity context;

    private View.OnClickListener onItemClickListener = view -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        OrderDatum orderDatum=orders.get(position);
        Intent i = new Intent(MainActivity.this, OrderDetailsActivity.class);
        i.putExtra("order",orderDatum);
        startActivity(i);
        //Toast.makeText(MainActivity.this, "You Clicked: " + orderDatum.getCity(), Toast.LENGTH_SHORT).show();
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Order list");

        Log.d("shuvo","MainActivity invoked ");
        //progressBar=findViewById(R.id.progressBar_id);
        //progressBar.setVisibility(View.VISIBLE);
        context=this;

        recyclerView = findViewById(R.id.order_recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(context).get(MainActivityViewModel.class);
        viewModel.getOrderMutableLiveData().observe(context, ordersResponse -> {
            Log.d("shuvo","MainActivity invoked "+ordersResponse.getStatus());
            List<OrderDatum> orderList=ordersResponse.getData();
            orders.addAll(orderList);
            recyclerViewAdapter = new RecyclerViewAdapter2(MainActivity.this, orders);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.setmOnItemClickListener(onItemClickListener);
            recyclerViewAdapter.notifyDataSetChanged();
            //progressBar.setVisibility(View.GONE);
        });
        //Here is problem
        Log.d("shuvo",orders.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_logout){
            SharedPreferences preferences =getSharedPreferences("login_info", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
            //Toast.makeText(this,"You clicked logout",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
