package com.example.deliveryboyapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliveryboyapp.remote.models.LoginRequest;
import com.example.deliveryboyapp.remote.models.OrderDatum;
import com.example.deliveryboyapp.remote.models.OrdersResponse;
import com.example.deliveryboyapp.repository.UserRepository;
import com.example.deliveryboyapp.repository.UserRepositoryImpl;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    MutableLiveData<OrdersResponse> ordersResponseMutableLiveData;
    private UserRepository repository=new UserRepositoryImpl();


    public MutableLiveData<OrdersResponse> getOrderMutableLiveData() {
        //Log.d("shuvo","MainActivityViewModel invoked.");
        if (ordersResponseMutableLiveData == null) {
            ordersResponseMutableLiveData=new MutableLiveData<>();
            getOrdersFromRepository();
        }

        //Problem is here
        Log.d("shuvo","MainActivityViewModel invoked."+ordersResponseMutableLiveData.getValue());
        return ordersResponseMutableLiveData;
    }

    private void getOrdersFromRepository() {
        String id="2";
        ordersResponseMutableLiveData=repository.getOrders(id);
    }
}
