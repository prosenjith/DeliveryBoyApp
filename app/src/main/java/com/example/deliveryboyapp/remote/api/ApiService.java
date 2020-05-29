package com.example.deliveryboyapp.remote.api;

import android.util.Log;

import com.example.deliveryboyapp.remote.models.LoginRequest;
import com.example.deliveryboyapp.remote.models.LoginResponse;
import com.example.deliveryboyapp.remote.models.OrdersResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/DeliveryBoyLoginApi.php")
    @FormUrlEncoded
    Call<LoginResponse> isValidUser(
            //@Body LoginRequest request
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("/DeliveryBoyApi.php")
    @FormUrlEncoded
    Call<OrdersResponse> getOrders(
            @Field("DeliveryBoyId") String id
    );
}
