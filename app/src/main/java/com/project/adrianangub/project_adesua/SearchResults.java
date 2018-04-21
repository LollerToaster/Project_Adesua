package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //a list to store all the products
    List<BookSearchResultsDataModel> productList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //recyclerView.setLayoutManager(mLayoutManager);

        // DANGER ==================================================================================

        /*
        ImageButton btn = (ImageButton)findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResults.this, bookInfoPage_v2.class);
                startActivity(intent);
            }
        });

        */

        //

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();


        //adding some items to our list
        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        60000,
                        R.drawable.book_sample_1));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Among the Ten Thousand Things",
                        "Mashesh Rao",
                        60000,
                        R.drawable.book_sample_2));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "Mahesh Rao",
                        60000,
                        R.drawable.book_sample_3));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "",
                        60000,
                        R.drawable.book_sample_4));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "Mahesh Rao",
                        60000,
                        R.drawable.book_sample_5));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "Mahesh Rao",
                        60000,
                        R.drawable.book_sample_1));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "Mahesh Rao",
                        60000,
                        R.drawable.book_sample_2));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "Mahesh Rao",
                        60000,
                        R.drawable.book_sample_3));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "Mahesh Rao",
                        60000,
                        R.drawable.book_sample_4));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "Mahesh Rao",
                        60000,
                        R.drawable.book_sample_5));

        productList.add(
                new BookSearchResultsDataModel(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen",
                        "Mahesh Rao",
                        60000,
                        R.drawable.book_sample_1));

        //creating recyclerview adapter
        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter(this, productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        //DANGER ===================================================================================
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
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the action_bar_menu/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_button) {

            startActivity(new Intent(SearchResults.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Snackbar worked as intended, brah", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();;
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
            startActivity(new Intent(SearchResults.this, home.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Snackbar worked as intended, brah", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();;
            return true;
        } else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_search) {
            startActivity(new Intent(SearchResults.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
