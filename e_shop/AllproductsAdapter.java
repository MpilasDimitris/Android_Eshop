package com.example.e_shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

class AllproductsAdapter extends RecyclerView.Adapter<AllproductsAdapter.ViewHolder> {
    List<Products> products;



    public AllproductsAdapter(List<Products> products){

        this.products=products;

    }



    @Override
    public AllproductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_products_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AllproductsAdapter.ViewHolder holder, final int position) {

        //παιρναω τις τιμες που θα εμφανιστουν στον recycler view
        holder.id.setText(String.valueOf(products.get(position).getId()));
        holder.category.setText(products.get(position).getCategory());
        holder.capacity.setText(String.valueOf(products.get(position).getCapacity()));
        holder.name.setText(products.get(position).getName());
        holder.price.setText(products.get(position).getPrice() + " $");
        holder.description.setText(products.get(position).getDescription());




            }




    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Δηλωνω τις μεταβλητες που εχω στο layout
        public TextView category;
        public TextView name;
        public TextView description;
        public TextView price;
        public TextView capacity;
        public TextView id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //τα αντιστοιχιζω
            id = itemView.findViewById(R.id.id_row);
            category = itemView.findViewById(R.id.category_row);
            name = itemView.findViewById(R.id.name_row);
            description = itemView.findViewById(R.id.description_row);
            price = itemView.findViewById(R.id.price_row);
            capacity = itemView.findViewById(R.id.capacity_row);

        }
    }

}
