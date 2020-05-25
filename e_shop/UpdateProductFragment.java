package com.example.e_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProductFragment extends Fragment {
    //δηλωνω τα TextView,spinner και κουμπια
    EditText editText,editText1,editText2,editText3;
    Spinner spinner1,spinner2;
    Button button;
    public UpdateProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update_product, container, false);
        //τα συνδεω με αυτα του layout
        editText = view.findViewById(R.id.prodIdEt);
        editText1 = view.findViewById(R.id.prodNameET);
        editText2 = view.findViewById(R.id.prodPriceET);
        editText3 = view.findViewById(R.id.prodDescET);
        spinner1 = view.findViewById(R.id.spinner);
        spinner2 = view.findViewById(R.id.capacitySpinner);
        button = view.findViewById(R.id.updateProductBtn);
        //θετω εναν ακροατη στο κουμπι οταν το πατησω
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //περνω σε μεταβλητες αυτα που εχω γραψει απο το πληκτρολογιο στο layout
                String var_prodName = editText1.getText().toString();
                String var_prodCategory = spinner1.getSelectedItem().toString();
                String var_prodDescription = editText3.getText().toString();
                // κανω ελεγχους εαν εχω αφησει καποιο πεδιο κενο και εμφανιζω καταληλο μηνυμα
                int var_id = 0;
                try {
                    var_id = Integer.parseInt(editText.getText().toString());
                }catch(NumberFormatException ex){
                    System.out.println("Could not parse"+ex);
                }
                int var_capacity=0;
                double var_prodPrice = 0.0;
                try{
                    var_capacity = Integer.parseInt(spinner2.getSelectedItem().toString());
                }catch (NumberFormatException e){
                    System.out.println("Could not parse "+e);
                }
                try {
                    var_prodPrice = Double.parseDouble(editText2.getText().toString());
                }catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                if(var_id==0){
                    editText.setError("Field required");
                    editText.requestFocus();
                }
                else if(var_prodName.isEmpty()){
                    editText1.setError("Field required");
                    editText1.requestFocus();
                }
                else if(var_prodDescription.isEmpty()){
                    editText3.setError("Field required");
                    editText3.requestFocus();
                }
                else if(var_prodPrice == 0.0){
                    editText2.setError("Field required");
                    editText2.requestFocus();
                }else {
                    //δημιουργω νεο αντικειμενο product
                    Products product = new Products();
                    //βαζω σε αυτο το αντικειμενο τις τιμες που εδωσα στο layout
                    product.setId(var_id);
                    product.setName(var_prodName);
                    product.setCategory(var_prodCategory);
                    product.setDescription(var_prodDescription);
                    product.setPrice(var_prodPrice);
                    product.setCapacity(var_capacity);
                    //κανω ενημερωση το αντιστοιχο αντικειμενο στη βαση
                    MainActivity.myAppDatabase.myDao().updateProduct(product);
                    //εμφανιζω καταλληλο μηνυμα επαληθευσης και καθαριζω τα editeTexts
                    Toast.makeText(getActivity(),"Successful update",Toast.LENGTH_LONG).show();
                    editText.setText("");
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");

                }
            }
        });


        return view;
    }
}
