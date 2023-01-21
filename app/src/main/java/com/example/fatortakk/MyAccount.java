package com.example.fatortakk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MyAccount extends AppCompatActivity {

    private String UserName;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        Intent intent3 = getIntent();
        UserName = intent3.getStringExtra("id");

        EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        EditText editTextName = (EditText) findViewById(R.id.editTextName);

        TextView editTextHello = (TextView) findViewById(R.id.fatortakTV);

        editTextUsername.setHint(UserName);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("username", UserName);

        Call<UserId> call = retrofitInterface.getUserByUsername(map);

        call.enqueue(new Callback<UserId>() {
            @Override
            public void onResponse(Call<UserId> call, Response<UserId> response) {
                if (response.code() == 200) {
                    String responseName = response.body().getName();
                    String responsePhone = response.body().getPhone();
                    String responseEmail = response.body().getEmail();
                    editTextHello.setText(responseName+"'s Profile");
                    editTextPhone.setHint(responsePhone);
                    editTextEmail.setHint(responseEmail);
                    Toast.makeText(MyAccount.this, "Success", Toast.LENGTH_LONG).show();

                } else if (response.code() == 400) {
                    Toast.makeText(MyAccount.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserId> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAccount.this.finish();
            }
        });

        Button updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = editTextPassword.getText().toString();
                String newName = editTextName.getText().toString();
                HashMap<String, String> map = new HashMap<>();
                Call<Void> call;

                if (!newPassword.isEmpty() && !newName.isEmpty()){
                    map.put("username", UserName);
                    map.put("password", newPassword);
                    map.put("name", newName);
                    call = retrofitInterface.updateNameAndPasswordByUsername(map);
                    callEnqueue(call);
                } else if (!newPassword.isEmpty()){
                    map.put("username", UserName);
                    map.put("password", newPassword);
                    call = retrofitInterface.updatePasswordByUsername(map);
                    callEnqueue(call);
                } else if (!newName.isEmpty()){
                    map.put("username", UserName);
                    map.put("name", newName);
                    call = retrofitInterface.updateNameByUsername(map);
                    callEnqueue(call);
                } else {
                    Toast.makeText(MyAccount.this, "Please enter new password or name", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void callEnqueue(Call<Void> call){
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(MyAccount.this, "Success", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(MyAccount.this, "Failed", Toast.LENGTH_LONG).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}