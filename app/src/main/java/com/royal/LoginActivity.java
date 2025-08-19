package com.royal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.royal.apiservice.ApiService;
import com.royal.model.ResponseModel;
import com.royal.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword = findViewById(R.id.edtLoginPassword);
        btnLogin = findViewById(R.id.btnLoginLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validation

                //call login api

                //create object of retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://diamondgamenode.onrender.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                //create instance of api service
                ApiService apiService = retrofit.create(ApiService.class);

                UserModel userModel = new UserModel();
                userModel.setEmail(edtEmail.getText().toString());
                userModel.setPassword(edtPassword.getText().toString());

                //login api call -> call
                Call<ResponseModel> call =  apiService.loginApi(userModel);

                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        //  200 : valid
                        //  400 : invalid

//                        Log.i("api",response.body().getMessage());
                        if(response.code() == 200){
                            //valid
                            Log.i("api",response.body().getUser().getFirstName());
                        }else {
                            //invalid
                            Log.i("api","Invalid");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.i("api","fail "+t.getMessage());
                    }
                });


            }
        });


    }
}