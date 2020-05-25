package com.example.e_shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    //δηλώνουμε τη λίστα Products
    List<Products> products;


//δομητής
    public ProductAdapter(List<Products> products){

        this.products=products;

    }



    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, final int position) {


//βαζω τις τιμες που θα παρει ο recyclerView
        holder.category.setText(products.get(position).getCategory());
        holder.name.setText(products.get(position).getName());
        holder.price.setText(products.get(position).getPrice() + " $");
        holder.description.setText(products.get(position).getDescription());


    //οταν κανω κλικ σε ενα itemView στον recyclerView το στελνω στο fragment addToCartFragment
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {



//δημιουργω νεο fragment
            AddToCartFragment addToCartFragment = new AddToCartFragment();

            //δημιουργω το Bundle
                Bundle bundle = new Bundle();
                //στελνω το id του product που πατηθηκε
                int id= products.get(position).getId();
                //στελνω το capacity που επελεξα απο τον spinner
                int capacity = Integer.parseInt(holder.capacity.getSelectedItem().toString());
                //τα βαζω στο bundle
                bundle.putInt("id",id);
                bundle.putInt("cap",capacity);
                //στελνω τα arguments
                addToCartFragment.setArguments(bundle);


                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,addToCartFragment).addToBackStack(null).commit();
            }
        });
            }




    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public TextView name;
        public TextView description;
        public TextView price;
        public Spinner capacity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category_row);
            name = itemView.findViewById(R.id.name_row);
            description = itemView.findViewById(R.id.description_row);
            price = itemView.findViewById(R.id.price_row);
            capacity = itemView.findViewById(R.id.capacity_row);

        }
    }

}
