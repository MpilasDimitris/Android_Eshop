package com.example.e_shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<Cart> cart_list;




    public CartAdapter(List<Cart> cart_list){

        this.cart_list=cart_list;

    }



    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {

        //παιρναω τις τιμες που θα εμφανιστουν για καθε αντικειμενο στη λιστα
        holder.category.setText(cart_list.get(position).getCategory());
        holder.name.setText(cart_list.get(position).getName());
        holder.price.setText(String.valueOf(cart_list.get(position).getPrice()+" $"));
        holder.capacity.setText(String.valueOf(cart_list.get(position).getCapacity()));
        holder.description.setText(cart_list.get(position).getDescription());
        //εδω οταν κανω κλικ στην εικονα delete διαγραφεται απο τον πινακα Cart
        holder.deleteImg.setOnClickListener(new View.OnClickListener(){

            @Override
            //kathe fora pou pataw thn eikona me ton kado gia diagrafh
            public void onClick(View v) {
                //Δημιουργω νεο fragment
                AddToCartFragment addToCartFragment = new AddToCartFragment();
                //περνω το id του αντικειμενου που πατιθηκε
                int pos = cart_list.get(position).getCid();
                //διαβαζω τον πινακα Cart με παραμετρο το id που εχω πατησει
                cart_list = MainActivity.myAppDatabase.myDao().getCartId(pos);
                //για καθε αντικειμενο στον πινακα
                for(Cart i:cart_list){
                    //εαν υπαρχει στον πινακα
                    if(pos==i.getCid()){
                        //το διαγραφω
                        MainActivity.myAppDatabase.myDao().deleteItemCart(i);
                    }
                }
//
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,addToCartFragment).addToBackStack(null).commit();
            }
        });
            }




    @Override
    public int getItemCount() {
        return cart_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView deleteImg;
        public TextView category;
        public TextView name;
        public TextView description;
        public TextView price;
        public TextView capacity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.categoryCart_row);
            name = itemView.findViewById(R.id.nameCart_row);
            description = itemView.findViewById(R.id.descriptionCart_row);
            price = itemView.findViewById(R.id.priceCart_row);
            capacity = itemView.findViewById(R.id.capacityCart_row);
            deleteImg = itemView.findViewById(R.id.delete_icon);

        }
    }

}
