package com.jonathan.api;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Interface.JsonPlaceHolderApi;
import Modelo.Posts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);
        getPosts();
    }


    private void getPosts(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apisls.upaxdev.com/task/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Posts> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }

                Posts postsList = response.body();

                    String content = "";
                    content += "userId: "+ postsList.getUserId() + "\n";
                    content += "env: "+ postsList.getEnv() + "\n";
                    content += "os:"+ postsList.getOs() + "\n";
                    mJsonTxtView.append(content);
                Toast.makeText(getApplicationContext(),""+response.code(),Toast.LENGTH_LONG).show();
                Log.e("HttpClient", "codigo: " + response.code());
                postsList
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());
            }
        });

    }
}