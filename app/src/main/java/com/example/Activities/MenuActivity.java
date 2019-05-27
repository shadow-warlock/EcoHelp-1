package com.example.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecohelp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;

public class MenuActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        TextView nick1 = navigationView.getHeaderView(0).findViewById(R.id.NickMenuActivity);
        TextView email1 = navigationView.getHeaderView(0).findViewById(R.id.EmailMenuActivity);
        ImageView avataroffline = navigationView.getHeaderView(0).findViewById(R.id.avatarMenuActivity);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        String RedSignal = getIntent().getStringExtra("BackToRezerv");
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("users").child(getUid());

        Log.i("deecode",getUid());
        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nick = dataSnapshot.child("username").getValue(String.class);
                String email = dataSnapshot.child("account").getValue(String.class);
                String Avatar = dataSnapshot.child("GoogleAvatar").getValue(String.class);
                nick1.setText(nick);
                email1.setText(email);
                if (Avatar.length() >= 2) {


                    new DownloadImageTask(navigationView.getHeaderView(0).findViewById(R.id.avatarMenuActivity)).execute(Avatar);

                }
                if (RedSignal!= null){
                    String AvatarRezerv = dataSnapshot.child("GoogleAvatarRezerv").getValue(String.class);
                    new DownloadImageTask(navigationView.getHeaderView(0).findViewById(R.id.avatarMenuActivity)).execute(AvatarRezerv);
                }
                else {
                    if (Avatar.equals("1")) {
                        avataroffline.setImageResource(R.drawable.boy1);


                    }
                    if (Avatar.equals("2")) {
                        avataroffline.setImageResource(R.drawable.boy_1);

                    }

                    if (Avatar.equals("4")) {
                        avataroffline.setImageResource(R.drawable.girl_1);

                    }
                    if (Avatar.equals("5")) {
                        avataroffline.setImageResource(R.drawable.man);

                    }
                    if (Avatar.equals("7")) {
                        avataroffline.setImageResource(R.drawable.man_2);

                    }
                    if (Avatar.equals("8")) {
                        avataroffline.setImageResource(R.drawable.man_3);

                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        uidRef.addValueEventListener(valueEventListener2);











Log.v("dwdwdwdwd",""+getIntent());
        int count = getIntent().getIntExtra("Avatar", 0);
        Log.v("dwdwdwdwd",""+count);

        if (count != 0) {

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    uidRef.child("GoogleAvatar").setValue(""+count);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            uidRef.addListenerForSingleValueEvent(valueEventListener);



        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



    public void onMapReady(GoogleMap googleMap) {

//ВкусВилл
        LatLng VkusVill1 = new LatLng(60.079938, 30.344437);
        googleMap.addMarker(new MarkerOptions().position(VkusVill1).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill1));

        LatLng VkusVill4 = new LatLng(60.000551, 30.207977);
        googleMap.addMarker(new MarkerOptions().position(VkusVill4).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill4));

        LatLng VkusVill5 = new LatLng(55.567593, 37.573666);
        googleMap.addMarker(new MarkerOptions().position(VkusVill5).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill5));

        LatLng VkusVill6 = new LatLng(55.679970, 37.451393);
        googleMap.addMarker(new MarkerOptions().position(VkusVill6).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill6));

        LatLng VkusVill7 = new LatLng(55.696925, 37.554020);
        googleMap.addMarker(new MarkerOptions().position(VkusVill7).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill7));

        LatLng VkusVill8 = new LatLng(55.546288, 37.555566);
        googleMap.addMarker(new MarkerOptions().position(VkusVill8).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill8));

        LatLng VkusVill9 = new LatLng(55.734125, 37.416472);
        googleMap.addMarker(new MarkerOptions().position(VkusVill9).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill9));

        LatLng VkusVill10 = new LatLng(55.744790, 37.546410);
        googleMap.addMarker(new MarkerOptions().position(VkusVill10).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill10));

        LatLng VkusVill11 = new LatLng(55.684778, 37.541695);
        googleMap.addMarker(new MarkerOptions().position(VkusVill11).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill11));

        LatLng VkusVill12 = new LatLng(55.568050, 37.566271);
        googleMap.addMarker(new MarkerOptions().position(VkusVill12).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill12));

        LatLng VkusVill13 = new LatLng(55.675662, 37.270045);
        googleMap.addMarker(new MarkerOptions().position(VkusVill13).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill13));

        LatLng VkusVill14 = new LatLng(55.737645, 37.433969);
        googleMap.addMarker(new MarkerOptions().position(VkusVill14).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill14));

        LatLng VkusVill15 = new LatLng(55.817379, 37.368374);
        googleMap.addMarker(new MarkerOptions().position(VkusVill15).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill15));

        LatLng VkusVill151 = new LatLng(55.669983, 37.476756);
        googleMap.addMarker(new MarkerOptions().position(VkusVill151).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill151));

        LatLng VkusVill161 = new LatLng(55.723745, 37.437078);
        googleMap.addMarker(new MarkerOptions().position(VkusVill161).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill161));

        LatLng VkusVill16 = new LatLng(55.743716, 37.863202);
        googleMap.addMarker(new MarkerOptions().position(VkusVill16).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill16));

        LatLng VkusVill17 = new LatLng(55.653454, 37.766513);
        googleMap.addMarker(new MarkerOptions().position(VkusVill17).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill17));

        LatLng VkusVill81 = new LatLng(55.714097, 37.521142);
        googleMap.addMarker(new MarkerOptions().position(VkusVill81).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill81));

        LatLng VkusVill19 = new LatLng(55.675604, 37.505946);
        googleMap.addMarker(new MarkerOptions().position(VkusVill19).title("ВкусВилл"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(VkusVill19));



//Лента
        LatLng Lenta1 = new LatLng(60.039242, 30.239496);
        googleMap.addMarker(new MarkerOptions().position(Lenta1).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta1));

        LatLng Lenta2 = new LatLng(59.998849, 30.234427);
        googleMap.addMarker(new MarkerOptions().position(Lenta2).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta2));

        LatLng Lenta3 = new LatLng(59.798798, 30.399273);
        googleMap.addMarker(new MarkerOptions().position(Lenta3).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta3));

        LatLng Lenta4 = new LatLng(60.022551, 30.293120);
        googleMap.addMarker(new MarkerOptions().position(Lenta4).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta4));

        LatLng Lenta5 = new LatLng(59.807094, 30.162162);
        googleMap.addMarker(new MarkerOptions().position(Lenta5).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta5));

        LatLng Lenta6 = new LatLng(59.808106, 30.3202447);
        googleMap.addMarker(new MarkerOptions().position(Lenta6).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta6));

        LatLng Lenta7 = new LatLng(59.851757, 30.093918);
        googleMap.addMarker(new MarkerOptions().position(Lenta7).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta7));

        LatLng Lenta8 = new LatLng(59.849890, 30.397765);
        googleMap.addMarker(new MarkerOptions().position(Lenta8).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta8));

        LatLng Lenta9 = new LatLng(59.984555, 30.226239);
        googleMap.addMarker(new MarkerOptions().position(Lenta9).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta9));

        LatLng Lenta10 = new LatLng(60.031321, 30.434551);
        googleMap.addMarker(new MarkerOptions().position(Lenta10).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta10));

        LatLng Lenta11 = new LatLng(59.989793, 30.439002);
        googleMap.addMarker(new MarkerOptions().position(Lenta11).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta11));

        LatLng Lenta12 = new LatLng(55.566387, 37.690194);
        googleMap.addMarker(new MarkerOptions().position(Lenta12).title("Лента"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lenta12));
//Просто Ярославль

        LatLng Yaroslavl1 = new LatLng(57.574012, 39.920637);
        googleMap.addMarker(new MarkerOptions().position(Yaroslavl1).title("Пункт Сдачи"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Yaroslavl1));

        LatLng Yaroslavl2 = new LatLng(57.670062, 39.838068);
        googleMap.addMarker(new MarkerOptions().position(Yaroslavl2).title("Пункт Сдачи"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Yaroslavl2));



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
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
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            Intent intent = new Intent(this, DecoderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, ShopActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, LibraryActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
