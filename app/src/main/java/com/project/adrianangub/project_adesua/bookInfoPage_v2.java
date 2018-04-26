package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class bookInfoPage_v2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_page_v2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //INTENT PASSED BY ANOTHER ACTIVITY TEST
        final String bookSynopsis = getIntent().getExtras().getString("bookSynopsis");
        //INTENT PASSED BY ANOTHER ACTIVITY TEST

        // ACTION BAR CUSTOMIZATION
        setTitle("Book Information");
        // ACTION BAR CUSTOMIZATION

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //BUTTONS =========================================================================================================

        final Button buttonDownloadPDF = (Button)findViewById(R.id.DownloadPDFButton);
        buttonDownloadPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buttonDownloadPDF.setText("Bookmarked!");
                Snackbar.make(findViewById(android.R.id.content), "Feature not Available Yet!", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.RED)
                        .show();
            }
        });


        final Button buttonBookmark = (Button)findViewById(R.id.BookmarkButton);
        buttonBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonBookmark.setText("Bookmarked!");
                buttonBookmark.setTextColor(Color.BLACK);
                buttonBookmark.setBackgroundColor(Color.YELLOW);

                //RECIEVING THE INTENT TEST
                //Toast.makeText(getApplicationContext(), bookSynopsis ,Toast.LENGTH_SHORT).show();
                //RECIEVING THE INTENT TEST

                /*
                int favorite = 0;
                if(favorite == 0)
                {
                    buttonBookmark.setText("Bookmark");
                    //Toast.makeText(getApplicationContext(), favorite, Toast.LENGTH_SHORT).show();
                    //buttonBookmark.setTextColor(Color.WHITE);
                    //buttonBookmark.setBackgroundColor(Color.YELLOW);
                }
                else{
                    buttonBookmark.setText("Bookmarked!");
                    buttonBookmark.setTextColor(Color.WHITE);
                    buttonBookmark.setBackgroundColor(Color.YELLOW);
                }
                */
            }
        });

        Button orderButton = (Button) findViewById(R.id.GoogleMapsButton);

        orderButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookInfoPage_v2.this, MapsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }

        });

        //BUTTONS =========================================================================================================

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
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_button) {

            startActivity(new Intent(bookInfoPage_v2.this, SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(bookInfoPage_v2.this, home.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            return true;
        } else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_search) {
            startActivity(new Intent(bookInfoPage_v2.this, SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
