package com.example.deliveryboyapp.viewmodels;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliveryboyapp.remote.models.LoginRequest;
import com.example.deliveryboyapp.remote.models.LoginResponse;
import com.example.deliveryboyapp.repository.UserRepository;
import com.example.deliveryboyapp.repository.UserRepositoryImpl;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository=new UserRepositoryImpl();
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<LoginRequest> userMutableLiveData;
    private MutableLiveData<LoginResponse> userMutableLiveDataResponse;
    public MutableLiveData<LoginRequest> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }
    public void onClick(View view) {
        LoginRequest loginUser = new LoginRequest(emailAddress.getValue(), password.getValue());
//        LoginResponse response=userRepository.isValidUser(loginUser);
        userMutableLiveData.setValue(loginUser);
    }

    public MutableLiveData<LoginResponse> isValidUser(){
        //Log.d("shuvo","ViewModel isValidUser invoked");
        LoginRequest loginUser = new LoginRequest(emailAddress.getValue(), password.getValue());
        userMutableLiveDataResponse = userRepository.isValidUser(loginUser);
        return userMutableLiveDataResponse;
    }

}
