package com.jonathan.api;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Interface.JsonPlaceHolderApi;
import Modelo.Posts;
import Modelo.ResponseBody;
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

        Call<ResponseBody> call = jsonPlaceHolderApi.getPosts(new Posts());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    mJsonTxtView.setText("Codigo: "+response.code());

                    ResponseBody responseBody = response.body();
                    boolean succes = responseBody.getSuccess();

                    if(succes)
                    {
                        String message = responseBody.getMessage();
                        Log.d("Respuesta", message);
                        mJsonTxtView.setText(responseBody.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}