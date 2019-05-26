package com.justfriend.suratuin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.justfriend.suratuin.Model.andy.DataItem;
import com.justfriend.suratuin.Model.andy.Response;
import com.justfriend.suratuin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class surat_masuk extends Fragment {


    private List<DataItem> andyModel = new ArrayList<>();
    private adapter_masuk adapter;
    private RecyclerView rc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_surat_masuk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc = view.findViewById(R.id.rc);
        loadData();
    }

    private void loadData() {
        ApiClient.client2().getDataku()
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if(response.isSuccessful()){
                            andyModel=response.body().getData();
                            Log.d("andyModel",String.valueOf(andyModel.size()));
                            adapter=new adapter_masuk(andyModel);
                            RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getActivity());
                            rc.setLayoutManager(mLayoutManager);
                            rc.setItemAnimator(new DefaultItemAnimator());
                            rc.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
    }
}
