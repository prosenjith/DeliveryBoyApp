package com.example.deliveryboyapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliveryboyapp.R;
import com.example.deliveryboyapp.databinding.ActivityLoginBinding;
import com.example.deliveryboyapp.remote.models.LoginRequest;
import com.example.deliveryboyapp.remote.models.LoginResponse;
import com.example.deliveryboyapp.ui.main.MainActivity;
import com.example.deliveryboyapp.viewmodels.LoginViewModel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private ProgressBar progressBar;
    private TextView forgotPassTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        forgotPassTextView=findViewById(R.id.forgot_pass_tv);
        getSupportActionBar().setTitle("Log in");

        progressBar=findViewById(R.id.login_progress_bar_id);
        progressBar.setVisibility(View.GONE);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);
        loginViewModel.getUser().observe(this, new Observer<LoginRequest>() {
            @Override
            public void onChanged(@Nullable LoginRequest loginUser) {
                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getEmail())) {
                    binding.txtEmailAddress.setError("Enter an E-Mail Address");
                    binding.txtEmailAddress.requestFocus();
                }
                else if (!loginUser.isEmailValid()) {
                    binding.txtEmailAddress.setError("Enter a Valid E-mail Address");
                    binding.txtEmailAddress.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getPassword())) {
                    binding.txtPassword.setError("Enter a Password");
                    binding.txtPassword.requestFocus();
                }
                else if (!loginUser.isPasswordLengthGreaterThan5()) {
                    binding.txtPassword.setError("Enter at least 6 Digit password");
                    binding.txtPassword.requestFocus();
                }
                else {
                    if(isNetworkAvailable(getApplicationContext())) {
                        validateLogin();
                    }else{
                        Toast.makeText(LoginActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        forgotPassTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, PasswordActivity.class);
                startActivity(i);
            }
        });

    }

    private void validateLogin() {
        //progressBar.setVisibility(View.VISIBLE);
        loginViewModel.isValidUser().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if(loginResponse!=null){
                    if(loginResponse.getStatus().equals("true")){
                        //Toast.makeText(LoginActivity.this,"Login Successful.",Toast.LENGTH_SHORT).show();
                        saveInSharedPreference();
                        progressBar.setVisibility(View.GONE);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,"Login Data is not right.",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    //progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,"Login Data Error.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveInSharedPreference() {
        SharedPreferences preferences = getSharedPreferences("login_info",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("logged", "logged");
        editor.commit();
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
//    public boolean isInternetAvailable() {
//        try {
//            InetAddress address = InetAddress.getByName("www.google.com");
//            return !address.equals("");
//        } catch (UnknownHostException e) {
//            // Log error
//        }
//        return false;
//    }
}
