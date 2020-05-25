package com.example.e_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QueriesFragment extends Fragment {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TextView querytextView,querytextResult;
    Button btnqueryrun;
    int test;
    public QueriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_queries, container, false);
       //γεμιζουμε εναν πινακα που ορισαμε με τα στοιχεια απο το querydescription_array
       final String[] queryArray = getResources().getStringArray(R.array.querydescription_array);
       //συνδεουμε τα textView και spinner απο το layout
       querytextView = view.findViewById(R.id.txtquery);
       spinner = view.findViewById(R.id.spinner);
       //εχουμε ενα ArrayAdapter οπου διαμορφωνεται με βαση τα στοιχεια στο select_query_array
       adapter = ArrayAdapter.createFromResource(getContext(),R.array.select_query_array,R.layout.support_simple_spinner_dropdown_item);
       adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
       //οριζουμε στον spinner με παραμετρο τον adaptor οπως τον διαμορφωσαμε
       spinner.setAdapter(adapter);
//οριζουμε εναν ακροατη για τον spinner οταν επιλεξουμε ενα item τοτε εμφανιζουμε το query description οπως αυτα αντιστοιχιζονται
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               querytextView.setText(queryArray[position]);
               test = position+1;
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
       //αντιστοιχιζω το TextView με το layout  και το button
       querytextResult = view.findViewById(R.id.txtqueryresult);
       btnqueryrun = view.findViewById(R.id.querybtn);
       //θετω εναν ακροατη για οταν πατηθει το κουμπι
       btnqueryrun.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               //εδω θα παρω τη θεση του αποτελεσματος  αναλογα με τη θεση του ερωτηματος που επελεξα
                querytextResult.setText("test"+test);
                //δηλωνω μια μεταβλητη για να βαλω το αποτελεσμα του ερωτηματος
                String result = "";
                //κανω μια switch αναλογα με το ποιο ερωτημα επελεξα με ωαση τη μεταβλητη test θα τρεξει το αναλογο ερωτημα
                switch (test) {
                    case 1:
                        //για το πρωτο ερωτημα περνω σε μια αλφαροθμητικη λιστα το αποτελεσμα του ερωτηματος
                        List<ResultStringInt> resultStringInt = MainActivity.myAppDatabase.myDao().getRemainCapacity();
                        //για καθε στοιχειο μεσα σε αυτη τη λιστα
                        for (ResultStringInt i : resultStringInt) {
                            //εμφανιζω το ονομα και την ποσοτητα του προιοντος
                            String product_name = i.getField1();
                            int capacity = i.getField2();
                            //βαζω ολα τα αποτελεσματα που πηρα
                            result = result + "\n Products Name: " + product_name + "\n Capacity: " + capacity + "\n";

                        }
                        //εμφανιζω τα αποτελεσματα στο layout
                        querytextResult.setText(result);
                        break;

                        //Το παραπανω γινεται για καθε περιπτωση της switch και εμφανιζει αναλογα το αποτελεσμα του ερωτηματος που εκτελεσα
                    case 2:
                        List<Integer> numcount = MainActivity.myAppDatabase.myDao().getNumSales();
                        result = result + "\n Συνολικές πωλησεις : " + numcount;
                        querytextResult.setText(result);
                        break;
                    case 3:
                        List<ResultStringInt> resultStringInts = MainActivity.myAppDatabase.myDao().getSalesOnGroupBy();
                        for (ResultStringInt i : resultStringInts) {
                            String prod_name = i.field1;
                            int sum = i.field2;
                            result = result + "\nProduct name: " + prod_name + "\nSales: " + sum;
                        }
                        querytextResult.setText(result);
                            break;

                }
                }
       });


       return view;
    }
}
