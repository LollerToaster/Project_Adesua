package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    //REFERENCE
    //https://www.simplifiedcoding.net/android-recyclerview-cardview-tutorial/
    //https://stackoverflow.com/questions/44454797/pull-to-refresh-recyclerview-android

    SwipeRefreshLayout mSwipeRefreshLayout;
    List<BookSearchResultsDataModel> dataList;
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

        // SwipeRefreshLayout ======================================================================
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // SwipeRefreshLayout ======================================================================

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager (new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));
        dataList.add(
                new BookSearchResultsDataModel(
                        "1",
                        "The Smoke is Rising",
                        "Mashesh Rao",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam at consequat lectus. Donec in massa quis massa vulputate venenatis eu sed nisi. Mauris ut sollicitudin nulla. Sed et urna gravida, mollis nunc vel, accumsan tellus. Duis erat dolor, facilisis porttitor mollis quis, bibendum sit amet leo. Morbi accumsan purus euismod faucibus convallis. Donec urna est, vulputate sed mollis quis, pellentesque. asdasdasdasdasdasdasd",
                        "5"));


        BookSearchResultsAdapter adapter = new BookSearchResultsAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
    }

    //Swipe Up to Refresh===========================================================================
    @Override
    public void onRefresh() {
        //Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
    //Swipe Up to Refresh===========================================================================

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
            startActivity(new Intent(SearchResults.this, SearchActivity.class));
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
