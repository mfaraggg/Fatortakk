package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends Activity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    Button b1,b2;
    EditText ed1,ed2;
    TextView tx1;
    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        b1 = (Button)findViewById(R.id.loginButton);
        ed1 = (EditText)findViewById(R.id.editTextUsername);
        ed2 = (EditText)findViewById(R.id.editTextPassword);

//        b2 = (Button)findViewById(R.id.button2);
//        tx1 = (TextView)findViewById(R.id.textView3);
//        tx1.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HashMap<String, String> map = new HashMap<>();
//                String id = ed1.getText().toString();
//                map.put("username", ed1.getText().toString());
//                map.put("password", ed2.getText().toString());
//
//                Call<LoginResult> call = retrofitInterface.executeLogin(map);
//
//                call.enqueue(new Callback<LoginResult>() {
//                    @Override
//                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
//                        if (response.code() == 200) {
//                            LoginResult result = response.body();
//                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                            intent.putExtra("id", id);
//                            startActivity(intent);
//
//                        } else if (response.code() == 400) {
//                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginResult> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),
//                                t.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });

                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    // Start MainActivity2
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });
    }
    public void signupClicked(View view) {
        Toast.makeText(getApplicationContext(), "Signing up...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, Signup.class);
        startActivity(intent);
    }
}