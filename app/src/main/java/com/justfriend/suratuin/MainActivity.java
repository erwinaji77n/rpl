package com.justfriend.suratuin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.justfriend.suratuin.Model.Login.LoginUser;
import com.justfriend.suratuin.Model.Login.ResponseLogin;
import com.justfriend.suratuin.Model.Login.UserSession;
import com.justfriend.suratuin.Model.other.Constant;
import com.justfriend.suratuin.retrofit.ApiClient;
import com.justfriend.suratuin.retrofit.BaseApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btn_login;
    private EditText Nim;
    private EditText password;
    private String PREFER_NAME = "Reg";
    private UserSession session;
    private SharedPreferences sharedPreferences;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();
        Nim = findViewById(R.id.txtNIM);
        password = findViewById(R.id.txtPassword);
        session = new UserSession(this);
        sharedPreferences = getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);
        loadingBar = new ProgressDialog(MainActivity.this);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Nim.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    userLogin(Nim.getText().toString(), password.getText().toString());
                }else if (TextUtils.isEmpty(Nim.getText().toString()) && TextUtils.isEmpty(password.getText().toString())) {
                    Nim.setError("NIM Tidak Boleh Kosong");
                    password.setError("Password Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(Nim.getText().toString())){
                    password.setError("NIM Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(Nim.getText().toString())){
                    password.setError("Password Tidak Boleh Kosong");
                } else {
                    Toast.makeText(MainActivity.this, "Cek Internet Anda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void userLogin( final String name, final String password){
        loadingBar.setTitle("Login");
        loadingBar.setMessage("Harap Tunggu");
        loadingBar.setCancelable(false);
        loadingBar.show();
        final LoginUser loginUser = new LoginUser(name, password);
        BaseApiService baseApiService = ApiClient.client();
        baseApiService.getLogin(loginUser).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                ResponseLogin responseLogin = response.body();
                if (response.isSuccessful()) {
                    Constant.token = response.body();
                    session.createUserLoginSession(responseLogin.getUsername());
                    Intent DashboardIntent = new Intent(MainActivity.this, DrawerActivity.class);
                    startActivity(DashboardIntent);
                    Toast.makeText(MainActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "NIM / Password Salah", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }

            }
            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Periksa Internet Anda", Toast.LENGTH_SHORT).show();
                finish();
                loadingBar.dismiss();
            }

        });
    }}