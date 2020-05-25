package com.example.e_shop;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddToCartFragment extends Fragment  {
    //Δηλωνουμε τις μεταβλητες,λιστες(κτλ)
    private TextView numItemsAdded,totalPrice;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private List<Products> product_list;
    private RecyclerView.LayoutManager layoutManager;
    Button button;
    public List<Cart> cart_list;
    static int cid=0;
    double priceAll;

    public AddToCartFragment(){}

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.cart_fragment,container,false);
        //Συνδεουμε τις μεταβλητες με τα id του layout
        numItemsAdded = view.findViewById(R.id.tv_total);
        totalPrice = view.findViewById(R.id.totalPrice_TV);
        button = view.findViewById(R.id.btn_placeorder);

//Παιρνουμε τα στοιχεια του πινακα cart
        cart_list = MainActivity.myAppDatabase.myDao().getCart();

//Παιρνουμε τα arguments που στειλαμε απο τον adaptor
        final Bundle bundle = getArguments();


        //Εαν δεν ειναι null τα στοιχεια που πηραμε
        if(bundle!=null){
            //Παιρνουμε σε μια μεταβλητη το id που στειλαμε απο τον adaptor
            final int id = bundle.getInt("id");
            //Παιρνουμε τα στοιχεια που αντιστοιχουν με αυτο το id απο τον πινακα products
            product_list = MainActivity.myAppDatabase.myDao().getProductId(id);
            //Εδω διαβαζουμε για καθε στοιχειο του πινακα products
            for (Products i:product_list) {
                //κραταμε τις τιμες που θελουμε
                cid = i.getId();
                String name = i.getName();
                String category = i.getCategory();
                double price = i.getPrice();
                int capacity = i.getCapacity();
                String description = i.getDescription();
                //εδω παιρνουμε το capacity απο τον πινακα products το εστειλα απο τον adaptor
                final int cap = bundle.getInt("cap");
                //κανω την αφαιρεση απο τη υπολοιπη ποσοτητα του προιοντος
                int CapacityRemain = capacity-cap;
                //και το κανω update στον πινακα products
                i.setCapacity(CapacityRemain);
                if(CapacityRemain<=0){
                    Toast.makeText(getActivity(),"not a valid amount "+capacity+" products remain",Toast.LENGTH_LONG).show();
                }else {
                    //Για καθε προιον που εβαλα στο  cart αφαιρεσε την ποσοτητα και κανε update τον πινακα product
                    MainActivity.myAppDatabase.myDao().updateProduct(i);
                    if (cap <= capacity) {
                        //Βαζω στον πινακα cart τα στοιχεια του πινακα products που επιλεξαμε να μπει στο καλαθι
                        Cart cart = new Cart(cid, category, name, description, price, cap);
                        cart.setCid(cid);
                        cart.setCategory(category);
                        cart.setName(name);
                        cart.setDescription(description);
                        cart.setPrice(price);
                        cart.setCapacity(cap);
                        //καλω την μεθοδο contains που κανει ελεγχο εαν το προιον ειναι ηδη στο καλαθι μη το βαλει ξανα
                        if (contains(cart_list, cid)) {
                            Toast.makeText(getActivity(), name + " is already in cart", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "You added to cart  " + name + " " + id, Toast.LENGTH_LONG).show();

                            MainActivity.myAppDatabase.myDao().addToCart(cart);
                        }
                    } else {
                        Toast.makeText(getActivity(), "We have in storage :" + capacity + " items of this product", Toast.LENGTH_LONG).show();
                    }
                }
            }

        }
        //αντιστοιχιζω τον recycler view απο το layout
        recyclerView = view.findViewById(R.id.addToCartRecyclerView);
        //εδω παιρνω τα προιοντα που ειναι σε αυτη τη λιστα
        cart_list = MainActivity.myAppDatabase.myDao().getCart();
        //οριζω τον layout και το αρχικοποιω
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //οριζω τον ανταπτορα και το αρχικοποιω
        adapter = new CartAdapter(cart_list);
        recyclerView.setAdapter(adapter);

        //υπολογισμος του συνολου των προιοντων που υπαρχουν στο καλαθι
        String total = String.valueOf(cart_list.size());
        numItemsAdded.setText(total);
        for(Cart i:cart_list) {
            priceAll += i.getPrice()*i.getCapacity();
        }

        //υπολογισμος συνολικης τιμης προιοντων στο καλαθι
        String fullprice = String.valueOf(priceAll);
        totalPrice.setText(fullprice);
        final ValidateUserOrderFragment validateUserOrderFragment = new ValidateUserOrderFragment();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().add(R.id.fragment_container,validateUserOrderFragment).addToBackStack(null).commit();
            }
        });

        return view;

    }

    //μεθοδος ελεγχου εαν το προιον υπαρχει ηδη στο καλαθι
    public boolean contains(List<Cart> cart_list,int  cid){
        for(Cart i:cart_list){
            if(i.getCid()==cid){
                return true;
            }
        }
        return false;

    }


}
