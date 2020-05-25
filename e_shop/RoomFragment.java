package com.example.e_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomFragment extends Fragment implements View.OnClickListener {
    //δηλωνω τα κουμπια
Button querynutton,updatebutton,deletebutton;

    public RoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.room_layout, container, false);
        //τα συνδεω με το layout και τους δινω εναν ακροατη
        querynutton = view.findViewById(R.id.query_button);
        querynutton.setOnClickListener(this);

        updatebutton = view.findViewById(R.id.update_button);
        updatebutton.setOnClickListener(this);

        deletebutton = view.findViewById(R.id.delete_button);
        deletebutton.setOnClickListener(this);





        return view;
    }
    //μεθοδος onClick αναλογα με το κουμπι που πατησω θα με παειστο αναλοπγα fragment
    public void onClick(View v){
        switch ((v.getId())){
            case R.id.query_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new QueriesFragment()).addToBackStack(null).commit();
                break;
            case R.id.update_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateFragment()).addToBackStack(null).commit();
                break;
            case R.id.delete_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteFragment()).addToBackStack(null).commit();
                break;

        }
    }
}
