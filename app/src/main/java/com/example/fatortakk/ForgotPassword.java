package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button sendButton = (Button) findViewById(R.id.sendButton);
        EditText usernameET = (EditText) findViewById(R.id.editTextUsername);
        EditText emailET = (EditText) findViewById(R.id.editTextEmail);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                retrofitInterface = retrofit.create(RetrofitInterface.class);
                HashMap<String, String> map = new HashMap<>();
                map.put("username", usernameET.getText().toString());
                map.put("email", emailET.getText().toString());
                Call<UserId> call = retrofitInterface.sendRecoveryEmail(map);
                call.enqueue(new retrofit2.Callback<UserId>() {
                    @Override
                    public void onResponse(Call<UserId> call, retrofit2.Response<UserId> response) {
                        if (response.code() == 200) {
                            Toast.makeText(ForgotPassword.this, "Email sent", Toast.LENGTH_LONG).show();
                            sendEmail(emailET.getText().toString(), response.body().getPassword());
                            Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
                            startActivity(intent);
                        } else if (response.code() == 404) {
                            Toast.makeText(ForgotPassword.this, "Username and email do not match", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserId> call, Throwable t) {
                        Toast.makeText(ForgotPassword.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void sendEmail(String email, String message) {
        // test push
        BackgroundMail.newBuilder(this)
                .withUsername("fatortakmohammedfarag@gmail.com")
                .withPassword("oaegpxisihehiodq")
                .withMailto(email)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("FATORTAK - Password Reset")
                .withBody("This is your password: " + message)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(ForgotPassword.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(ForgotPassword.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .send();
    }

    public void onBackPressed(View view) {
        finish();
    }
}