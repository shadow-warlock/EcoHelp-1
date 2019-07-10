package com.example.Activities;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecohelp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.Objects;

public class BaseActivity extends AppCompatActivity {
    public String Activity;
    Drawer result;

    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Загрузка");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    public String getUid() {
        return Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    }
    int avatar;



    public void  Drawer(Toolbar toolbar, Context context, android.app.Activity activity){


        DatabaseReference rootRef2 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef2 = rootRef2.child("users").child(getUid());
        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child("account").getValue(String.class);
                String Avatar = dataSnapshot.child("Avatar").getValue(String.class);
                Long coinsAmount = dataSnapshot.child("coinsAmount").getValue(Long.class);

                String coinsView = "Баланс: " + coinsAmount;




                    if (Avatar.equals("1")) {
                        avatar = (R.drawable.a1);
                    }
                    if (Avatar.equals("2")) {
                        avatar = (R.drawable.a2);
                    }


                IProfile profile = new ProfileDrawerItem()
                        .withName(email)
                        .withEmail(coinsView)
                        .withIcon(avatar);

                AccountHeader headerResult = new AccountHeaderBuilder()
                        .withActivity(activity)
                        .withHeaderBackground(R.drawable.account_header_background)
                        .addProfiles(
                                profile
                        )
                        .build();


                   result = new DrawerBuilder()
                        .withActivity(activity)
                        .withToolbar(toolbar)
                        .withAccountHeader(headerResult)
                        .addDrawerItems(
                                new PrimaryDrawerItem()
                                        .withName("Устройства")
                                        .withIcon(R.drawable.device)
                                        .withIdentifier(1)
                        )
                        .addDrawerItems(
                                new PrimaryDrawerItem()
                                        .withName("Сканер")
                                        .withIcon(R.drawable.scaner)
                                        .withIdentifier(2)
                        )
                        .addDrawerItems(
                                new PrimaryDrawerItem()
                                        .withName("Магазины")
                                        .withIcon(R.drawable.shop)
                                        .withIdentifier(3)
                        )
                        .addDrawerItems(
                                new PrimaryDrawerItem()
                                        .withName("Купоны")
                                        .withIcon(R.drawable.coupons)
                                        .withIdentifier(4)
                        )
                        .addDrawerItems(
                                new PrimaryDrawerItem()
                                        .withName("Архив Купонов")
                                        .withIcon(R.drawable.oldcoupons)
                                        .withIdentifier(5)
                        )
                        .withOnDrawerItemClickListener((view, i, iDrawerItem) -> {
                            if(iDrawerItem.getIdentifier() == 1 && !Activity.equals("Menu")){
                                Intent intent = new Intent(context,MenuActivity.class);
                                startActivity(intent);
                            }
                            else if(iDrawerItem.getIdentifier() == 2 && !Activity.equals("Scaner")){
                                Intent intent = new Intent(context,DecoderActivity.class);
                                startActivity(intent);
                            }
                            else if(iDrawerItem.getIdentifier() == 3 && !Activity.equals("Shop")){
                                Intent intent = new Intent(context,ShopActivity.class);
                                startActivity(intent);
                            }
                            else if(iDrawerItem.getIdentifier() == 4 && !Activity.equals("oldCoupons")){
                                Intent intent = new Intent(context,oldCouponsActivity.class);
                                startActivity(intent);
                            }

                            return false;
                        })
                        .build();

                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
                Objects.requireNonNull(result.getActionBarDrawerToggle()).setDrawerIndicatorEnabled(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        uidRef2.addValueEventListener(valueEventListener2);




    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();

        }

    }
    @Override
    public void onBackPressed() {

        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }




    @SuppressLint("StaticFieldLeak")
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        DownloadImageTask(ImageView bmImage) {
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

    public boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo == null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Оповещение")
                    .setMessage("Нет связи с сервером")
                    .setCancelable(true)
                    .setNegativeButton(":(",
                            (dialog, id) -> dialog.cancel());
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }
        return false;
    }

    public void showDialog(String message,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Оповещение")
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        (dialog, id) -> {
                           dialog.dismiss();

                        });
        AlertDialog alert = builder.create();
        alert.show();
    }




}

