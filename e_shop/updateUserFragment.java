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
public class updateUserFragment extends Fragment {
    //δηλωνω τα EditeText ,κουμπια
    EditText editText1,editText2,editText3,editText4,editText5;
    Button updateUser;
    public updateUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update_user, container, false);
//τα αντιστοιχιζω με αυτα του layout
        editText1 = view.findViewById(R.id.iduserET);
        editText2 = view.findViewById(R.id.nameuserET);
        editText3 = view.findViewById(R.id.lastnameuserET);
        editText4 = view.findViewById(R.id.emailuserET);
        editText5 = view.findViewById(R.id.passworduserET);
        updateUser = view.findViewById(R.id.updateUserButton);
        updateUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //δηλωνω καποιες μεταβλητες οπου θα περασω τις τιμες που εχω γραψει στο layout
                int id = 0;
                try {
                    id = Integer.parseInt(editText1.getText().toString());
                }catch (NumberFormatException ex){
                    System.out.println("Could not parse!");
                }
                String name = editText2.getText().toString();
                String lastname = editText3.getText().toString();
                String email = editText4.getText().toString();
                String pass = editText5.getText().toString();
//δημιουργω νεο αντικειμενο
                User users = new User(name,lastname,email,pass);
                //βαζω στο αντικειμενο τις τιμες που εδωσα απο το πληκτρολογιο
                users.setId(id);
                users.setName(name);
                users.setLastName(lastname);
                users.setMail(email);
                users.setPassword(pass);
                //κανω ενημερωση στη βαση το συγκεκρυμενο αντικειμενο
                MainActivity.myAppDatabase.myDao().updateUser(users);
//εμφανιζω καταλληλο μηνυμα επαληθευσης και καθαριζω τα editTexts
                Toast.makeText(getActivity(),"One record updated!",Toast.LENGTH_LONG).show();
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText5.setText("");





            }
        });

        return view;
    }
}
