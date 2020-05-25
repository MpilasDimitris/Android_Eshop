package com.example.e_shop;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CPUFragment extends Fragment {
    //δηλωνω τον recyclerView και τον ανταπτορα
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_cpu,container,false);
        //συνδεω τον recyclerView με το layout
        recyclerView = view.findViewById(R.id.graphicRecyclerView);
        //στη λιστα αυτη παιρνω ολα τα προιοντα της κατηγοριας cpu
        List<Products> products = MainActivity.myAppDatabase.myDao().getCPU();
    //αρχικοποιω νεο layoutManager στον recyclerView και τον ανταπρορα
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
