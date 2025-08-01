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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


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

        edtFirstName = findViewById(R.id.edtSignupFirstName);
        edtLastName = findViewById(R.id.edtSignupLastName);
        edtEmail = findViewById(R.id.edtSignupEmail);
        edtPassword = findViewById(R.id.edtSignupPassword);

        btnSubmit = findViewById(R.id.signupBtn);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //api calling  internet
                //api -> main thread -> new thread

                //api -> code
                new SignupApi().start();
            }
        });


    }


    class SignupApi extends Thread{
        public void run(){
            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();


            try {

                //step 1:
                URL url = new URL("https://diamondgamenode.onrender.com/api/auth/signup");

                //step 2:
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                //step 3:
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                String jsonInputString = String.format(
                        "{\"firstName\":\"%s\", \"lastName\":\"%s\", \"email\":\"%s\", \"password\":\"%s\"}",
                        firstName, lastName, email, password
                );

                //step 4:
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                //step 5:
                // Read the response
                StringBuilder response = new StringBuilder();
                try (BufferedReader
                             br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }

                Log.i("response",response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



