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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


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

                    //api -> network
                SignupApi s = new SignupApi();
                s.start();
            }
        });

    }
    class SignupApi extends Thread{
        public void run(){
            //read
            String firstName  = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            //api?
            String apiUrl = "https://diamondgamenode.onrender.com/api/auth/signup";

            //api call
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

                httpConnection.setRequestMethod("POST");
                httpConnection.setRequestProperty("Content-Type", "application/json");
                httpConnection.setRequestProperty("Accept", "application/json");
                httpConnection.setDoOutput(true); // Required to send body

                HashMap<String,Object> data = new HashMap<>();
                data.put("firstName",firstName);
                data.put("lastName",lastName);
                data.put("email",email);
                data.put("password",password);
                data.put("credit",25000);

                JSONObject jsonObject = new JSONObject(data);
                String jsonData  = jsonObject.toString();

                try (OutputStream os = httpConnection.getOutputStream()) {
                    byte[] input = jsonData.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int statusCode = httpConnection.getResponseCode();

                //200
                //201
                if(statusCode == 201){
                    //success
                   // Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }
}//end of class
//1) internet access -> AndroidManifest.xml
//2) main thread network call not allow -> class extends Thread -> logic -> start()



