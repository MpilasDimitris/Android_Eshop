package com.example.e_shop;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//βαζω παραμετρους τους πινακαες που εχω στη βαση μου και το version
@Database ( entities = {User.class, Products.class,Cart.class,Sales.class} , version = 1 )
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract  MyDao myDao();
}
