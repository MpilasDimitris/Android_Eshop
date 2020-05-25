package com.example.e_shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder> {

    List<Sales> sales;



    public SalesAdapter(List<Sales> sales){

        this.sales=sales;

    }

    @Override
    public SalesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SalesAdapter.ViewHolder holder, final int position) {

//περνω ολες τις εγγραφες απο τον πινακα SalesCopy

        sales = MainActivity.myAppDatabase.myDao().getSales();
        List<String> username = MainActivity.myAppDatabase.myDao().getUsernameSales();
        List<String> productName = MainActivity.myAppDatabase.myDao().getProductNameSales();
                //εμφανιζω στο layout  τις εγγραφες αυτου του πινακα
                holder.salesid.setText(String.valueOf(sales.get(position).getSalesid()));
                holder.userName.setText(username.get(position));
                holder.productName.setText(productName.get(position));
                holder.capacity.setText(String.valueOf(sales.get(position).getProduct_capacity()));
                holder.price.setText(String.valueOf(sales.get(position).getTotal_price()));
                holder.date.setText(sales.get(position).getDate());



            }




    @Override
    public int getItemCount() {
        return sales.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //δηλωνω τα TextView που εχω στο layout
        public TextView userName,salesid;
        public TextView productName,capacity,price,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //συνδεω τα TextView που δηλωσα με του layout
            salesid = itemView.findViewById(R.id.cart_id_row);
           userName = itemView.findViewById(R.id.user_name_row);
           productName = itemView.findViewById(R.id.cart_name_row);
           price = itemView.findViewById(R.id.Salesprice_row);
           capacity = itemView.findViewById(R.id.Salescapacity_row);
           date = itemView.findViewById(R.id.DateSales_row);
        }
    }

}
