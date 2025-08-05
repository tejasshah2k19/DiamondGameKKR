package com.royal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.royal.apiservice.ApiService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignupActivity extends AppCompatActivity {

    EditText edtFirstName,edtLastName,edtEmail,edtPassword;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //binding
        edtFirstName = findViewById(R.id.edtSignupFirstName);
        edtLastName = findViewById(R.id.edtSignupLastName);
        edtEmail = findViewById(R.id.edtSignupEmail);
        edtPassword = findViewById(R.id.edtSignupPassword);
        btnSubmit = findViewById(R.id.signupBtn);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://diamondgamenode.onrender.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

               ApiService apiService =  retrofit.create(ApiService.class);

               Call<Integer> call =  apiService.signupApi();

               call.enqueue(new Callback<Integer>() {
                   @Override
                   public void onResponse(Call<Integer> call, Response<Integer> response) {

                       //
                       //
                   }

                   @Override
                   public void onFailure(Call<Integer> call, Throwable t) {

                   }
               });


            }
         });

    }

}//end of class
//1) internet access -> AndroidManifest.xml
//2) main thread network call not allow -> class extends Thread -> logic -> start()



