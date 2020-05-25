package com.example.e_shop;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//εδω εχω ολα τα query,insert,delete,update για καθε πινακα
@Dao
public interface MyDao {
    @Insert
    public void addUser(User user);

//    @Insert
//    public void addSalesCopy(SalesCopy salesCopy);
    @Insert
    public void addProduct(Products products);

    @Insert
    public void addSale(Sales sales);
    @Insert
    public void addToCart(Cart cart);

    @Delete
    public void deleteUser(User user);

    @Delete
    public void deleteProduct(Products products);

    @Delete()
    public void deleteItemCart(Cart cart);
//    @Delete
//    public void deleteSalesCopy(SalesCopy salesCopy);

    @Update
    public void updateUser(User user);
//    @Update
//    public void updateSalesCopy(SalesCopy salesCopy);

    @Update
    public void updateProduct(Products products);
    //περνω ολες τις εγγραφες στον πινακα cart_table
    @Query("select * from cart_table ")
    public List<Cart> getCart();
    //περνω ολες τις εγγραφες στον πινακα users
    @Query("select * from users")
    public List<User> getUsers();
    //εδω στελνω σε παραμετρο το name και password οπου εαν αντιστοιχιζονται τοτε θα γινει η ολοκληρωση
    //Αυτο το query το καλω στο fragment validateUserOrderFragment
    @Query("SELECT * FROM users where users_name= :name and password= :password")
    User getUser(String name, String password);
    //εδω στελνω σε παραμετρο το id του product για να το βαλω στο Cart
    //το καλω στο fragment addToCartFragment
    @Query("select * from products where pid=:id ")
    public List<Products> getProductId(int id);
    //περνω ολες τις εγγραφες στον πινακα products
    @Query("select * from products")
    public List<Products> getProducts();
    //απο τη γραμμη 60 μεχρι 75 περνω ολα τα προιοντα που ανηκουν στην αναλογη κατηγορια για να τα εμφανισω στο αναλογο fragment
    @Query("select * from products where product_category = 'Graphic Card' ")
    public List<Products> getGraphicCards();
    @Query("select * from products where product_category='CPU'")
    public List<Products> getCPU();
    @Query("select * from products where product_category='Motherboard'")
    public List<Products> getMotherboard();
    @Query("select * from products where product_category='RAM'")
    public List<Products> getRam();
    @Query("select * from products where product_category='PC case'")
    public List<Products> getPCcase();
    @Query("select * from products where product_category='HDD'")
    public List<Products> getHDD();
    @Query("select * from products where product_category='SSD'")
    public List<Products> getSSD();
    //περνω ολες τις εγγραφες απο τον πινακα cart_table με παραμετρο το id αυτο το χρησιμοποιω για να αφαιρεσω ενα προιον απο το cart
    @Query("select * from cart_table where cid=:id")
    public List<Cart> getCartId(int id);
    //περνω ολες τις εγγραφες απο τον πινακα sales
    @Query("select * from sales")
    public List<Sales> getSales();
    @Query("select s.pname as field1,s.capacity as field2 from products s")
    public List<ResultStringInt> getRemainCapacity();
    //μετραω ολες τις πωλησεις που εχουν γινει
    @Query("select distinct count(*) from sales s")
    public List<Integer> getNumSales();
    //περνω τις πωλησεις που εχουν γινει ανα προιον
    @Query("select s.product_name as field1,count(*) as field2 from sales s group by s.product_name")
    public List<ResultStringInt> getSalesOnGroupBy();
    //παιρνω το user name αντιστιχοιζοντας id του user με το id του ξενου κλειδιου  του user στον sales
    @Query("select u.users_name from sales s join users u on s.suid=u.id")
    public List<String> getUsernameSales();
    //παιρνω το product name αντιστιχοιζοντας id του product με το id του ξενου κλειδιου  του product στον sales
    @Query("select p.pname from sales s join products p on s.scid=p.pid")
    public List<String> getProductNameSales();



}
