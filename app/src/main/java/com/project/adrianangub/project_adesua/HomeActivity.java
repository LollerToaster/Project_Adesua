package com.project.adrianangub.project_adesua;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ACTION BAR CUSTOMIZATION
        setTitle("Home");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Create Folder
        File folder = new File(Environment.getExternalStorageDirectory().toString()+"/Adesua/");
        folder.mkdirs();

        // CALLING SHARED PREFERENCES
        Users user = SharedPrefManager.getInstance(this).getUser();

        // NAVIGATION DRAWER
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        //ImageView drawerImage = (ImageView) headerView.findViewById(R.id.profile_image);
        TextView drawerUsername = (TextView) headerView.findViewById(R.id.name);
        TextView drawerAccount = (TextView) headerView.findViewById(R.id.email);
        //drawerImage.setImageDrawable(R.drawable.ic);
        drawerUsername.setText(user.getFullname());
        drawerAccount.setText(user.getSchoolname());

        //loading Initial Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeOneFragment.newInstance());
        transaction.commit();

        // SHARED PREFERENCE, IF USER IS NOT SAVED IN PHONE, AUTO INTENT TO LOGIN PAGE
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }

        //CountDownTimer / Notification Block ======================================================
        new CountDownTimer(5000, 1000) {

            public int testes = 0; //test counter

            public void onTick(long millisUntilFinished) {
                //every second or every countDownInterval happens on onTick
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //PUT API HERE

                //PUT API HERE

                //REMOVE ON REAL STUFF
                testes++;
                //REMOVE ON REAL STUFF

                //sendNotification(testes);
                /*
                if (testes == 2 || testes == 4) {
                    sendNotification(testes);
                    testes++;
                }
                */

                if ( testes == 4) {
                    sendNotification(testes);
                    testes++;
                }
                //loops the whole countdown
                this.start();
            }
        }.start();

        // BOTTOM NAV ==============================================================================
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = HomeOneFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = HomeTwoFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = HomeThreeFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = HomeFourFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });


        /*
        if(getIntent().getExtras() != null)
        {
            String intentFragment = getIntent().getExtras().getString("fragmentCall");

            switch (intentFragment) {
                case "2": HomeTwoFragment.newInstance();
                    break;
                case "3: HomeThreeFragment.newInstance();
                    break;
            }
        }
        */
    }

    //CountDownTimer / Notification Block ==========================================================
    public void sendNotification(int testes) {

        //Get an instance of NotificationManager//
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_error_outline_black_24dp)
                .setContentTitle("This is a Notification Tester!")
                //.setContentText("Hello ! loop counter: " + testes)
                .setContentText("Hello ! This Notification is for Dry Run Purposes Only!!")
                //.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationManager.IMPORTANCE_HIGH);

        // Gets an instance of the NotificationManager service//
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001

        //NotificationManager.notify().

        mNotificationManager.notify(1, mBuilder.build());
    }
    //==============================================================================================

    //load Initial Fragment
    //https://www.simplifiedcoding.net/bottom-navigation-android-example/
    /*
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, HomeOneFragment)
                    .commit();
            return true;
        }
        return false;
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    //ACTION BAR BUTTONS ===========================================================================
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_button) {
            startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //==============================================================================================

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle bottom_navigator_menu view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, HomeActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Returning to HomeActivity worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();;
            return true;
        } else if (id == R.id.nav_profile_settings) {
            //startActivity(new Intent(this, profileSettingsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            new MaterialDialog.Builder(this)
                    .title("Whoops!")
                    .content("Under development for dry run stage.")
                    .positiveText("Understood")
                    //.negativeText("no")
                    .show();

        } else if (id == R.id.nav_virtual_classroom) {

            //https://stackoverflow.com/questions/36063704/how-to-launch-activity-and-show-specific-fragment
            Intent i = new Intent(this, HomeActivity.class);
            String Fragment = "2";
            i.putExtra("fragmentCall", Fragment);

            // Now start your activity
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        } else if (id == R.id.nav_downloaded) {
            startActivity(new Intent(this, profileSettingsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        } else if (id == R.id.nav_search) {
            startActivity(new Intent(this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Intent to search worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        } else if (id == R.id.logout) {
            finish();
            SharedPrefManager.getInstance(getApplicationContext()).logout();
            //startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Intent to search worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        } else if (id == R.id.announcements) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            new MaterialDialog.Builder(this)
                    .title("Whoops!")
                    .content("Under development for dry run stage.")
                    .positiveText("Understood")
                    //.negativeText("no")
                    .show();
            //Toast.makeText(getApplicationContext(), "Still In Development! :)" ,Toast.LENGTH_SHORT).show();
            //        .setAction("Action", null).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
