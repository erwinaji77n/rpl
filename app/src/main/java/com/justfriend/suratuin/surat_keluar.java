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

import com.justfriend.suratuin.Model.outbox.MessageItem;
import com.justfriend.suratuin.Model.outbox.ResponseOutbox;
import com.justfriend.suratuin.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple {@link Fragment} subclass.
 */
public class surat_keluar extends Fragment {
    private List<MessageItem> dataModel = new ArrayList<>();
    private adapterKeluar adapter;
    private RecyclerView rc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_surat_keluar, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc = view.findViewById(R.id.rc);
        loadData();
    }
    private void loadData() {
        ApiClient.client().getOutbox()
                .enqueue(new Callback<ResponseOutbox>() {
                    @Override
                    public void onResponse(Call<ResponseOutbox> call, retrofit2.Response<ResponseOutbox> response) {
                        if(response.isSuccessful()){
                            dataModel=response.body().getMessage();
                            adapter=new adapterKeluar(dataModel);
                            RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getActivity());
                            rc.setLayoutManager(mLayoutManager);
                            rc.setItemAnimator(new DefaultItemAnimator());
                            rc.setAdapter(adapter);
                            Log.d("Erwin",String.valueOf(dataModel.size()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseOutbox> call, Throwable t) {

                    }
                });
    }

}
