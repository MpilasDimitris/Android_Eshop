package com.example.e_shop;
//τα import που χρειαστήκανε
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
//Δηλώνω τον fragmentManager
    public static FragmentManager fragmentManager;
    //Δηλώνω τη βάση μας
    public static MyAppDatabase myAppDatabase;
    //Επίσης δηλώνω το toolbar που δημιούργησα
    Toolbar toolbar;
    //το drawerLayout
    private DrawerLayout drawerLayout;
    //το navigationView
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //αρχικοποιώ τον fragmentManager
        fragmentManager = getSupportFragmentManager();
        //εδώ παίρνω την βάση που δημιούργησα
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"userDB").allowMainThreadQueries().build();
//εάν το ο fragmentContainer δεν ειναι null θα κάνει απλά return
        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            //προσθέτω στον fragment container το roomFragment που θα εμφανίζεται
            fragmentManager.beginTransaction().add(R.id.fragment_container,new RoomFragment()).commit();
        }
//συνδέω το toolbar
        toolbar = findViewById(R.id.toolbar);
        //καλω τη παρακάτωγ μεθοδο setSupportActionBar με παράμετρο το toolbar έτσι θέτω το toolbar μου στο activity
        setSupportActionBar(toolbar);

        //συνδέω το drawerLayoyt
        drawerLayout = findViewById(R.id.drawer_layout);

    //δημιουργώ togle στο ActionBar για να βάλω ένα εικονόδιο από ώπου θα ανήγει το drawerLayout αλλά και ότι άλλα εικονίδια θέλω να βάλω τσο ActionBar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_open);

//θέτω έναν ακροάτη στο drawerLayout με παράμετρο το togle αυτό που πατήθηκε δηλαδη
        drawerLayout.addDrawerListener(toggle);
        //συγχρονίζω το toggle
        toggle.syncState();

//συνδέω το navigationView
        navigationView = findViewById(R.id.navigationView);
        //οταν πατησω κατι απο το navigationView στο drawerLayout θα με παει στο καταληλο fragment
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_graphic_card:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new GraphicCardsFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_cpu:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new CPUFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_motherboard:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new MotherboardFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_ram:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new RamFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_PC_case:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new PCcaseFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_hdd:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new HddFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_ssd:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new SsdFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navRegUser:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new RegisterUserFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navInsertProduct:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new InsertProductFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_sales:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new SalesFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_users:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new Users()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navProducts:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container,new ProductsFragment()).addToBackStack(null).commit();
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        }
        //mμε αυτή τη μέθοδο εμφανίζω τα εικονίδια που θέλω στο AcrionBar που βρίσκονται στο menu αρχείο
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu,menu);
        return true;
    }
    //οταν πατησω ενα απο τα εικονιδια στο toolbar με πηγαινει στο αναλογο fragment
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            //παιρνω τοο id του imageView
            case R.id.action_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddToCartFragment()).addToBackStack(null).commit();
                break;
            case  R.id.home_icon:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RoomFragment()).addToBackStack(null).commit();
                break;
        }
        return true;
    }



}




