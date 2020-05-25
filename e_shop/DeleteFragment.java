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
public class DeleteFragment extends Fragment implements View.OnClickListener {
    //δηλωνω τις μεταβλητες
    Button button1,button2;
    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete, container, false);
        //τα συνδεω με τα id τους στο layout και θετω ακροατες
        button1 = view.findViewById(R.id.delete_user);
        button1.setOnClickListener(this);

        button2 = view.findViewById(R.id.delete_product);
        button2.setOnClickListener(this);





        return view;
    }
    //οταν κανω κλικ σε καποιο απο τα κουμπια αναλογα πηγαινε με στο αντιστοιχο fragment
    public void onClick(View v){

        switch (v.getId()){
            case R.id.delete_user:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new deleteUserFragment()).addToBackStack(null).commit();
                break;
            case R.id.delete_product:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new deleteProductFragment()).addToBackStack(null).commit();
                break;

        }
    }

}
