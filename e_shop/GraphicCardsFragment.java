package com.example.e_shop;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GraphicCardsFragment extends Fragment  {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;


    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graphic_cards, container, false);

        recyclerView = view.findViewById(R.id.graphicRecyclerView);

        List<Products> products = MainActivity.myAppDatabase.myDao().getGraphicCards();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);





        return view;
    }


}