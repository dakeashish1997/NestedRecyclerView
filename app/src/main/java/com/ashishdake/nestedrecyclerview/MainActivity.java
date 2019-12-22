package com.ashishdake.nestedrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ashishdake.nestedrecyclerview.Adapters.MainAdapter;
import com.ashishdake.nestedrecyclerview.Interface.API;
import com.ashishdake.nestedrecyclerview.Models.MainModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MainModel> dataList = new ArrayList<>();
    MainAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.mainRacyclerView);

        adapter = new MainAdapter(dataList,MainActivity.this);
        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

        showData();
    }

    private void showData(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Loading Data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(true);

        HashMap postdata = new HashMap();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        API api = retrofit.create(API.class);

        Call<List<MainModel>> call = api.datalist(postdata);

        call.enqueue(new Callback<List<MainModel>>() {
            @Override
            public void onResponse(Call<List<MainModel>> call, Response<List<MainModel>> response) {
                List<MainModel> list = response.body();
                if (list != null) {
                    dataList.clear();
                    for (MainModel data : list) {
                        dataList.add(data);
                        adapter.notifyDataSetChanged();
                    }
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<MainModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
