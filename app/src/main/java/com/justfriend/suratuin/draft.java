package com.justfriend.suratuin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justfriend.suratuin.Model.andy.DataItem;
import com.justfriend.suratuin.Model.andy.Response;
import com.justfriend.suratuin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple {@link Fragment} subclass.
 */
public class draft extends Fragment {

    private List<DataItem> andyModel = new ArrayList<>();
    private adapter_masuk adapter;
    private RecyclerView rc_draft;
    public draft() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_draft, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_draft = view.findViewById(R.id.rc_draft);
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
                            rc_draft.setLayoutManager(mLayoutManager);
                            rc_draft.setItemAnimator(new DefaultItemAnimator());
                            rc_draft.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
    }

}
