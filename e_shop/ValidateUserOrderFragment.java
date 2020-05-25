package com.example.e_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ValidateUserOrderFragment extends Fragment {
    EditText editText1,editText2;
    TextView totalPrice;
    Button button;
    List<Cart> cartList;
    List<Sales> sales_list;
    List<Products> products;
    public ValidateUserOrderFragment() {
        // Required empty public constructor
    }

    static int capacity=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_validate_user_order, container, false);

        editText1 = view.findViewById(R.id.valName);
        editText2 = view.findViewById(R.id.valPass);
        button = view.findViewById(R.id.completeOrder);
        //φτιαχνω το form  του date οπως θελω να εμφανιζεται
        String pattern = "MM/dd/yyyy HH:mm:ss";
        //εδω γινεται η αλλαγη του pattern με τις καταλληλες κλασεις
        DateFormat df = new SimpleDateFormat(pattern);
        //με την μεθοδο Callendar περνω το DATE
        Date today = Calendar.getInstance().getTime();
        //το μεταφερω σε μια μεταβλητη που θα το περασω στη βαση οταν γινει η συναλλαγη
        final String todayAsString = df.format(today);
        //θετω εναν ακροατη για οταν πατηθει το κουμπι
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//περνω τις τιμες που εχω δωσει στο το πληκτρολογιο  στις μεταβλητες
                String name = editText1.getText().toString();
                String password = editText2.getText().toString();
                //εδω καλω το ερωτημα οπου βλεπω εαν αυτες οι δυο τιμες που εχω δωσει αντιστοιχιζονται σε μια εγγραφη στον πινκα users
                User user = MainActivity.myAppDatabase.myDao().getUser(name,password);
                //εαν το ερωτημα που επιστρεφει μια γραμμη τοτε προχωραω
                if(user!=null){
                    //περνω σε μια λιστα ολα τα αντικειμενα που ειναι μεσα στον πινακα Cart
                    cartList = MainActivity.myAppDatabase.myDao().getCart();
                    //για καθε εγγραφη του πινακα Cart
                    for(Cart i:cartList){
                        //περνω τα πεδια που υπαρχουν για καθε αντικειμενο του πινακα Cart και τα βαζω σε μεταβλητες που δημιουργω
                        String prod_name=i.getName();
                         capacity = i.getCapacity();
                        //εδω περνω σε μια μεταβλητη το συνολικο ποσο αναλογα με την ποσοστητα του προιοντος
                        double price =  i.getPrice() * capacity;
                        //δημιουργω νεο αντικειμενο Sales και περναω τις τιμες που θελω να εχει ο πινακας
                        Sales sales = new Sales(user.getId(),i.getCid(),todayAsString,prod_name,capacity,price);
                        sales.setId(user.getId());
                        sales.setCid(i.getCid());
                        sales.setProduct_capacity(capacity);
                        sales.setProduct_name(prod_name);
                        sales.setTotal_price(price);
                        sales.setProduct_capacity(capacity);
                        sales.setDate(todayAsString);
                    //προσθετω τη νεα εγγραφη του αντικειμενου που δημιουργησα παραπανω στον πινακα Sales
                        MainActivity.myAppDatabase.myDao().addSale(sales);
                        //διαγραφω τα προιοντα που ειχα προσθεσει στο Cart
                        MainActivity.myAppDatabase.myDao().deleteItemCart(i);

                    }
                        //μηνυμα επαληθευσης οτι η αγορα εγινε με επιτυχια

                            Toast.makeText(getActivity(),"Thank you for your order!!!"+name,Toast.LENGTH_LONG).show();

                }else{
                    //μηνυμα εαν ο χρηστης που κανει την αγορα δεν υπαρχει στη βαση
                    Toast.makeText(getActivity(),"User don't exist",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
