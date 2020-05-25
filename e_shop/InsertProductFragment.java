package com.example.e_shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

public class InsertProductFragment extends Fragment {
    //δηλωνω τις μεταβλητες
        EditText editText1,editText3,editText4;
        Spinner spinner,spinner2;
        Button insertProdButton;
        public InsertProductFragment(){}

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_insert_product,container,false);
//τα συνδεω με το layout
        editText1 = view.findViewById(R.id.prodNameET);
        spinner= view.findViewById(R.id.spinner);
        editText3 = view.findViewById(R.id.prodDescET);
        editText4 = view.findViewById(R.id.prodPriceET);
        spinner2 = view.findViewById(R.id.capacitySpinner);
        insertProdButton = view.findViewById(R.id.insertProductBtn);
        //οταν πατηθει το κουμπι παιρνει τα στοιχεια που εχω δωσει στα πεδια και τα κανει add στον πινακα Product
        insertProdButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //παιρνω τα στοιχεια που εγραψα κανω και καποιους ελεγχουν ετσι ωστε να μη ειναι κατι κενο και εμφανιζει καταληλο μηνυμα
                String var_prodName = editText1.getText().toString();
                String var_prodCategory = spinner.getSelectedItem().toString();
                String var_prodDescription = editText3.getText().toString();
                int var_capacity=0;
                double var_prodPrice = 0.0;
                try{
                    var_capacity = Integer.parseInt(spinner2.getSelectedItem().toString());
                }catch (NumberFormatException e){
                    System.out.println("Could not parse "+e);
                }
                try {
                    var_prodPrice = Double.parseDouble(editText4.getText().toString());
                }catch (NumberFormatException ex){
                    System.out.println("Could not parse "+ex);
                }
                if(var_prodName.isEmpty()){
                    editText1.setError("Field required");
                    editText1.requestFocus();
                }
                else if(var_prodDescription.isEmpty()){
                    editText3.setError("Field required");
                    editText3.requestFocus();
                }
                else if(var_prodPrice == 0.0){
                    editText4.setError("Field required");
                    editText4.requestFocus();
                }


                else {
                    //τα προσθετω στον πινακα
                    Products product = new Products(var_prodCategory,var_prodName,var_prodDescription,var_prodPrice,var_capacity);
                    product.setName(var_prodName);
                    product.setCategory(var_prodCategory);
                    product.setDescription(var_prodDescription);
                    product.setPrice(var_prodPrice);
                    product.setCapacity(var_capacity);
                    MainActivity.myAppDatabase.myDao().addProduct(product);
                    //εμφαζιζω μηνυμα επαληθευσης και καθαριζω το editText
                    Toast.makeText(getActivity(),"Successful insert of product",Toast.LENGTH_LONG).show();
                    editText1.setText("");
                    editText3.setText("");
                    editText4.setText("");

                }
            }
        });

        return view;
    }
}
