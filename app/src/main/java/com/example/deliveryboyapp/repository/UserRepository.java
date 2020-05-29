package com.example.deliveryboyapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.deliveryboyapp.remote.models.LoginRequest;
import com.example.deliveryboyapp.remote.models.LoginResponse;
import com.example.deliveryboyapp.remote.models.OrdersResponse;

public interface UserRepository {
    MutableLiveData<LoginResponse> isValidUser(LoginRequest request);

    MutableLiveData<OrdersResponse> getOrders(String id);
}
