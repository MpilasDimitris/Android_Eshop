package com.example.e_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment implements View.OnClickListener {
    //δηλωση κουμπιων
    Button updateuser,updateproduct;
    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update, container, false);
        //τα συνδεω με το layout και τους βαζω εναν ακροατη
        updateuser = view.findViewById(R.id.update_user);
        updateuser.setOnClickListener(this);

        updateproduct = view.findViewById(R.id.update_product);
        updateproduct.setOnClickListener(this);






        return view;
    }
//οταν πατηθει καποιο κουμπι θα με παει στο αναλογο fragment
    public void onClick(View v){
        switch (v.getId()){
            case R.id.update_user:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new updateUserFragment()).addToBackStack(null).commit();
                break;
            case R.id.update_product:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new UpdateProductFragment()).addToBackStack(null).commit();
                break;

        }
    }
}
