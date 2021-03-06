package com.razerblade.restaurant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.razerblade.restaurant.admin.Input;
import com.razerblade.restaurant.cheff.ListOrder;
import com.razerblade.restaurant.kasir.Kasir;
import com.razerblade.restaurant.pelanggan.AboutRes;
import com.razerblade.restaurant.pelanggan.MenuTampil;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //NavigationView navigationView =null;
   // Toolbar toolbar = null;
    private EditText mMeja;
    private TextView mNoMeja,mEmailPelanggan,mNamaPelanggan;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filterUser();

        if (FirebaseC.currentUser == null) { //jika belum login
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        } else { //jika sudah login
            //masukkan fragment pada adapter viewpager
             //mengatur tab pada viewpager
        }
        navigationView =(NavigationView)findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        SharedPreferences sharedPreferences = getSharedPreferences("Preference", Context.MODE_PRIVATE);
        mNoMeja = (TextView)header.findViewById(R.id.nomorMeja);
        mNoMeja.setText(sharedPreferences.getString("meja",""));
        mEmailPelanggan = (TextView)header.findViewById(R.id.emailPelanggan);
        mEmailPelanggan.setText(FirebaseC.mAuth.getCurrentUser().getEmail());
        mNamaPelanggan =(TextView)header.findViewById(R.id.NamaPelanggan);
        mNamaPelanggan.setText(FirebaseC.mAuth.getCurrentUser().getDisplayName());
        AboutRes fragment = new AboutRes();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1,fragment,"help").commit();
        getSupportFragmentManager().popBackStack();
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
            Log.d("Setting","Meja");
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            View mview = getLayoutInflater().inflate(R.layout.dialog_meja,null);
            mMeja = (EditText)mview.findViewById(R.id.mejaId);
            mBuilder.setView(mview);
            mBuilder.setTitle("Isi Nomor Meja");
            mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences preferences = getApplicationContext().
                            getSharedPreferences("Preference",0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("meja",mMeja.getText().toString());
                    editor.commit();
                }
            });
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
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
            MenuTampil fragment = new MenuTampil();
            Bundle bundle = new Bundle();
            bundle.putString("jenis","makanan");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1,fragment,"Input").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.minum) {
            Log.d("Pelanggan","Minum");
            MenuTampil fragment = new MenuTampil();
            Bundle bundle = new Bundle();
            bundle.putString("jenis","minuman");
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1,fragment,"Input").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.help) {
            Log.d("Pelanggan","Help");
            AboutRes fragment = new AboutRes();
            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1,fragment,"help").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.logout) {
            FirebaseC.mAuth.signOut();
            startActivity(new Intent(MainActivity.this, Login.class));

        } else if (id == R.id.inputMakan) {
            Log.d("Admin","inputMakan");
            Input fragment = new Input();

            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1,fragment,"Input").commit();
            getSupportFragmentManager().popBackStack();

        } else if (id == R.id.printBill) {
            Log.d("Kasir","Print Bill");
            Kasir fragment = new Kasir();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container1,fragment,"Kasir").commit();
            getSupportFragmentManager().popBackStack();

        }else if (id == R.id.orderList) {
            Log.d("Order List","Order List");
            ListOrder fragment = new ListOrder();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container1,fragment,"Order List").commit();
            getSupportFragmentManager().popBackStack();
        }else if (id == R.id.share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi, This Restaurant is Very Good Check This Location https://goo.gl/maps/bSGZKVHiNCE2");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item1 = menu.findItem(R.id.action_settings);
        if(FirebaseC.mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
            item1.setEnabled(true);
        }else {
            item1.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    public void filterUser(){
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem input = menu.findItem(R.id.inputMakan);
        MenuItem order = menu.findItem(R.id.orderList);
        MenuItem printBill = menu.findItem(R.id.printBill);
        MenuItem makan = menu.findItem(R.id.makan);
        MenuItem minum = menu.findItem(R.id.minum);
        MenuItem help = menu.findItem(R.id.share);
        MenuItem share = menu.findItem(R.id.help);
        if(FirebaseC.mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
            makan.setVisible(false);
            minum.setVisible(false);
            help.setVisible(false);
            share.setVisible(false);
        }else{
            input.setVisible(false);
            order.setVisible(false);
            printBill.setVisible(false);


        }



    }

}

