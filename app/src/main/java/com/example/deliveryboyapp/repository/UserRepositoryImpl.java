package com.example.deliveryboyapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.deliveryboyapp.remote.api.ApiClient;
import com.example.deliveryboyapp.remote.api.ApiService;
import com.example.deliveryboyapp.remote.models.LoginRequest;
import com.example.deliveryboyapp.remote.models.LoginResponse;
import com.example.deliveryboyapp.remote.models.OrdersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;

public class UserRepositoryImpl implements UserRepository {
    private ApiService service;
    private Retrofit retrofit;
    public UserRepositoryImpl() {
        retrofit= ApiClient.getRetrofit();
        service=retrofit.create(ApiService.class);
    }
    @Override
    public MutableLiveData<LoginResponse> isValidUser(LoginRequest request) {
        MutableLiveData<LoginResponse> userResponseData = new MutableLiveData<>();



        Call<LoginResponse> call=service.isValidUser(request.getEmail(),request.getPassword());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result=response.body();
                String s=result.getStatus();
                //Log.d("shuvo","Status is "+s+" and Data is "+result.getData().getFirstName());
                if(s.equals("true")){
                    userResponseData.setValue(result);
                }else{
                    Log.d("shuvo","Login Failed.");
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("shuvo","Login Failed onFailure. "+t.toString());
                userResponseData.setValue(null);

            }
        });
        return userResponseData;
    }

    @Override
    public MutableLiveData<OrdersResponse> getOrders(String id) {
        MutableLiveData<OrdersResponse> orderResponseData = new MutableLiveData<>();


        Call<OrdersResponse> call=service.getOrders(id);
        call.enqueue(new Callback<OrdersResponse>() {
            @Override
            public void onResponse(Call<OrdersResponse> call, Response<OrdersResponse> response) {
                OrdersResponse result=response.body();
                if(result.getStatus().equals("true")){
                    orderResponseData.setValue(result);
                    Log.d("shuvo","Orders fetched successfully.");
                }else{
                    Log.d("shuvo","Orders Failed.");
                }
            }

            @Override
            public void onFailure(Call<OrdersResponse> call, Throwable t) {
                Log.d("shuvo","Orders Failed.");
            }
        });
        return orderResponseData;
    }
}
