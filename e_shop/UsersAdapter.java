package com.example.e_shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    //δηλώνω τη λίστα Users
    List<User> users;


//δομητής
    public UsersAdapter(List<User> users){

        this.users=users;

    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UsersAdapter.ViewHolder holder, final int position) {


//περνω στη λιστα users ολες τις εγγραφες του πινακα users
        users = MainActivity.myAppDatabase.myDao().getUsers();
                //μεφανιζω την καθε εγγραφη στον recyclerView
                holder.ID.setText(String.valueOf(users.get(position).getId()));
                holder.Name.setText(users.get(position).getName());
                holder.Lastname.setText(String.valueOf(users.get(position).getLastName()));
                holder.email.setText(String.valueOf(users.get(position).getMail()));

            }




    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //δηλωνω τα textView
        public TextView ID,Name,Lastname,email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //τα αντιστοιχιζω με του layout
           ID = itemView.findViewById(R.id.userID_row);
           Name = itemView.findViewById(R.id.name_row);
           Lastname = itemView.findViewById(R.id.lastname_row);
           email = itemView.findViewById(R.id.email_row);

        }
    }

}
