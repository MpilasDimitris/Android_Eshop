package com.example.e_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class deleteUserFragment extends Fragment {
    //δηλωνω τις μεταβλητες
    EditText editText;
    Button button;
    public deleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete_user, container, false);
        //τα συνδεω με το layout
        editText = view.findViewById(R.id.deleteIDET);
        button = view.findViewById(R.id.deleteUserButton);
        //οταν παταω το κουμπι διαγραφεται ο χρηστης με αυτο το id που εδωσα
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //δηλωνω μια μεταβλητη οπου θα περασω αυτο που εγραψα απο το πληκτρολοογιο
                int var_id=0;
                try {
                    //περναω αυτο που εγραψα
                    var_id = Integer.parseInt(editText.getText().toString());
                }catch (NumberFormatException ex){
                    System.out.println("Could not parse");
                }
                //δημιουργω νεο αντικειμενο User
                User user = new User();
                //περναω τη μεταβλητη στο αντικειμενο
                user.setId(var_id);
                //διαγραφω το αντικειμενο με αυτη την τιμη
                MainActivity.myAppDatabase.myDao().deleteUser(user);
                //καθαριζω το editText
                editText.setText("");
                //εμφανιζω μηνυμα επαληθευσης
                Toast.makeText(getActivity(),"One record deleted",Toast.LENGTH_LONG).show();


            }
        });

        return view;
    }
}
