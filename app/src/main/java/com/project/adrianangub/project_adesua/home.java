package com.project.adrianangub.project_adesua;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity
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
        // ACTION BAR CUSTOMIZATION

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        // LOG IN PART =============================================================================

        // NAVIGATION DRAWER ===============================================================================================

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        //ImageView drawerImage = (ImageView) headerView.findViewById(R.id.profile_image);
        TextView drawerUsername = (TextView) headerView.findViewById(R.id.name);
        TextView drawerAccount = (TextView) headerView.findViewById(R.id.email);
        //drawerImage.setImageDrawable(R.drawable.ic);
        drawerUsername.setText("lol my name jeff");
        drawerAccount.setText("user@gmail.com");

        // NAVIGATION DRAWER ===============================================================================================


        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, home.class));
        }
        //textViewUid = (TextView) findViewById(R.id.textViewUid);
        //textViewDesc = (TextView) findViewById(R.id.textViewDesc);
        //textViewMeta = (TextView) findViewById(R.id.textViewMeta);
        //textViewStat = (TextView) findViewById(R.id.textViewStat);


        //getting the current user
        Users user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        //textViewId.setText(String.valueOf(user.getId()));
        /*
        textViewUid.setText(user.getUid());
        textViewDesc.setText(user.getDesc());
        textViewMeta.setText(user.getMeta());
        textViewStat.setText(user.getStat());
        */
        //Toast.makeText(getApplicationContext(), user.getUsername(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), user.getPassword(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), user.getUid(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), user.getDesc(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), user.getMeta(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), user.getStat(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), user.getSchoolid(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), user.getSchoolname(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), user.getSchooladdress(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), user.getSchoolacryn(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), user.getSchoolnum(), Toast.LENGTH_LONG).show();

        //when the user presses logout button
        //calling the logout method
        /*
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
        */



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
                                selectedFragment = ItemOneFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemThreeFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = ItemThreeFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //You're layering your Fragments one on top of the other.
        //When a config change occurs the old Fragment adds itself to the new Activity when it's recreated. This is a massive pain in the rear most of the time.
        //You can stop errors occurring by using the same Fragment rather than recreating a new one. Simply add this code:
        //https://stackoverflow.com/questions/8474104/android-fragment-lifecycle-over-orientation-changes
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
            transaction.commit();
        }
        else {
            // do nothing - fragment is recreated automatically
        }

        // BOTTOM NAV ==============================================================================
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


    // OVERFLOW MENU, NOT NEEDED // OVERFLOW MENU, NOT NEEDED // OVERFLOW MENU, NOT NEEDED

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }


    //ACTION BAR BUTTONS ===========================================================================
    //ACTION BAR BUTTONS ===========================================================================
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search_button) {

            startActivity(new Intent(home.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Snackbar worked as intended, brah", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();;
            return true;
        }
        /* REFRESH  ==========================================================================
        if(id == R.id.menu_refresh){
            Intent intent = getIntent();
            finish();
            overridePendingTransition( 0, 0);
            startActivity(intent);
            overridePendingTransition( 0, 0);
            return true;
        }
         REFRESH  ============================================================================*/

        return super.onOptionsItemSelected(item);
    }

    //NAVIGATION DRAWER SELECTION ==================================================================
    //NAVIGATION DRAWER SELECTION ==================================================================

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(home.this, home.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Returning to home worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();;
            return true;
        } else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(home.this, profileSettingsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_search) {
            startActivity(new Intent(home.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Intent to search worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        } else if (id == R.id.profile_settings) {
            startActivity(new Intent(home.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Intent to search worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        } else if (id == R.id.logout) {
            finish();
            SharedPrefManager.getInstance(getApplicationContext()).logout();
            //startActivity(new Intent(home.this, SearchActivity.class));
            //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Intent to search worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
