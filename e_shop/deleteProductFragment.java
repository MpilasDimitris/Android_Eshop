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
public class deleteProductFragment extends Fragment {
    //δηλωνω τις μεταβλητες
    EditText editText;
    Button button;

    public deleteProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete_product, container, false);
        //τα συνδεω με τα id στο layout
        editText = view.findViewById(R.id.deleteProductIDET);
        button = view.findViewById(R.id.deleteProductButton);
        //οταν παταω το κουμπι διαγραφω το προιον με το συγκεκριμενο id που εδωσα
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //δηλωνω μια μεταβλητη ωπου θα παρω αυτο που θα γραψω
                int var_id=0;
                try {
                    //εδω περναω την τιμη που εχω γραψει
                    var_id = Integer.parseInt(editText.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("Could not parse"+ex);
                }
                //δημιουργω νεο αντικειμενο products
                Products products = new Products();
                //βαζω στο id αυτο που εγραψα
                products.setId(var_id);
                //το διαγραφω
                MainActivity.myAppDatabase.myDao().deleteProduct(products);
                //καθαριζω το editText
                editText.setText("");
                //εμφανιζω ένα μηνυμα επαληθευσης
                Toast.makeText(getActivity(),"One record deleted",Toast.LENGTH_LONG).show();


            }
        });

        return view;
    }
}
