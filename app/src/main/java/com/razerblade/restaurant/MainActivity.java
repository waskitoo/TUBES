package com.razerblade.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.razerblade.restaurant.admin.Input;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //NavigationView navigationView =null;
   // Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (FirebaseC.currentUser == null) { //jika belum login
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        } else { //jika sudah login
            //masukkan fragment pada adapter viewpager
             //mengatur tab pada viewpager
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.makan) {
            Log.d("Pelanggan","Makan");
           Input fragment = new Input();
//            FragmentTransaction fragmentTransaction =
//                    getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.container,fragment);
//            fragmentTransaction.commit();
           fragment.setArguments(getIntent().getExtras());
           getSupportFragmentManager().beginTransaction()
                   .add(R.id.container1,fragment,"Input").commit();
           getSupportFragmentManager().popBackStack();
        } else if (id == R.id.minum) {
            Log.d("Pelanggan","Minum");

        } else if (id == R.id.bayar) {
            Log.d("Pelanggan","Bayar");

        } else if (id == R.id.help) {
            Log.d("Pelanggan","Help");

        } else if (id == R.id.logout) {
            FirebaseC.mAuth.signOut();
            startActivity(new Intent(MainActivity.this, Login.class));

        } else if (id == R.id.inputMakan) {
            Log.d("Admin","inputMakan");

        } else if (id == R.id.inputMinum) {
            Log.d("Admin","Minum");

        } else if (id == R.id.printBill) {
            Log.d("Kasir","Print Bill");

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
