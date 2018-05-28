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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookInformationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SETTING TEXTVIEWS
        TextView bookTitleTextView = (TextView)findViewById(R.id.classIdent);
        TextView bookAuthorTextView = (TextView)findViewById(R.id.classDescription);
        ImageView bookCoverTextView = (ImageView)findViewById(R.id.bookImage);

        //INTENT PASSED BY ANOTHER ACTIVITY TEST
        final String bookTitle = getIntent().getExtras().getString("bookTitle");
        final String bookAuthor = getIntent().getExtras().getString("bookAuthor");
        final String bookImage = getIntent().getExtras().getString("bookImage");
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

        //Log.d("debug", "Title : " + bookTitle);
        //Log.d("debug", "Author : " + bookAuthor);
        //Log.d("debug", "Image : " + bookImage);

        //Toast.makeText(getApplicationContext(), bookTitle ,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), bookAuthor ,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), bookImage ,Toast.LENGTH_SHORT).show();

        //SETTING INTENT
        //String url = dataList.get(position).getCover();
        Glide.with(this)
                .load(bookImage)
                .into(bookCoverTextView);

        bookTitleTextView.setText(bookTitle);
        bookAuthorTextView.setText(bookAuthor);

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
                //buttonBookmark.setBackgroundColor("#FFC536");
                buttonBookmark.setBackgroundColor(Color.parseColor("#FFC536"));



                //RECIEVING THE INTENT TEST
                //Toast.makeText(getApplicationContext(), bookTitle ,Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), bookAuthor ,Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), bookImage ,Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(BookInformationActivity.this, MapsActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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

            startActivity(new Intent(BookInformationActivity.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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
            startActivity(new Intent(this, profileSettingsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_search) {
            startActivity(new Intent(this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Intent to search worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        } else if (id == R.id.logout) {
            finish();
            SharedPrefManager.getInstance(getApplicationContext()).logout();
            //startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Intent to search worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
