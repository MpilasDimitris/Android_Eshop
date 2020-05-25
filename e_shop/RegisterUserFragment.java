package com.example.e_shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterUserFragment extends Fragment {
    //δηλωση μεταβλητων
        EditText editText1,editText2,editText3,editText4,editText5;
        Button signUpButton;
        public RegisterUserFragment(){}

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_register_user,container,false);
        //τα συνδεω με το layout
        editText1 = view.findViewById(R.id.nameET);
        editText2 = view.findViewById(R.id.lastnameET);
        editText3 = view.findViewById(R.id.emailET);
        editText4 = view.findViewById(R.id.passwordET);
        editText5 = view.findViewById(R.id.confPasswordET);
        signUpButton = view.findViewById(R.id.signUpBtn);
        //οταν πατησω το κουμπι
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //[αιρνω τα στοιχεια που εγραψα
                String var_name = editText1.getText().toString();
                String var_lastname = editText2.getText().toString();
                String var_email = editText3.getText().toString();
                String var_password = editText4.getText().toString();
                String var_confpas = editText5.getText().toString();
                //κανω ελεγχο μην ειναι καποιο πεδιο κενο και εμφανιζω καταληλο μηνυμα
                if(var_name.isEmpty()){
                    editText1.setError("Field required");
                    editText1.requestFocus();
                }
                else if(var_lastname.isEmpty()){
                    editText2.setError("Field required");
                    editText2.requestFocus();
                }
                else if(var_email.isEmpty()){
                    editText3.setError("Field required");
                    editText3.requestFocus();
                }
                else if(var_password.isEmpty()){
                    editText4.setError("Field required");
                    editText4.requestFocus();
                }
                else if(!var_confpas.equals(var_password)){
                    editText5.setError("Password don't match");
                    editText5.requestFocus();
                }
                else {
                //δημιουργω το αντικειμενο Users
                        User users = new User(var_name,var_lastname,var_email,var_password);
                        //βαζω τις τιμες που εγραψα στον πινακα
                        users.setName(var_name);
                        users.setLastName(var_lastname);
                        users.setMail(var_email);
                        users.setPassword(var_password);
                        //το προσθετω στον πινακα
                        MainActivity.myAppDatabase.myDao().addUser(users);
                    Toast.makeText(getActivity(),"Successful registration",Toast.LENGTH_LONG).show();

                    //καθαριζω τα editTexts
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText5.setText("");
                }
            }
        });

        return view;
    }
}
