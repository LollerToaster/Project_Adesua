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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BookInformationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //SETTING TEXTVIEWS
        TextView bookTitleTextView = (TextView)findViewById(R.id.bookTitle);
        TextView bookAuthorTextView = (TextView)findViewById(R.id.bookAuthor);
        ImageView bookCoverTextView = (ImageView)findViewById(R.id.bookCover);

        //INTENT PASSED BY ANOTHER ACTIVITY TEST
        final String bookTitle = getIntent().getExtras().getString("bookTitle");
        final String bookAuthor = getIntent().getExtras().getString("bookAuthor");
        final String bookImage = getIntent().getExtras().getString("bookImage");
        final String bookId = getIntent().getExtras().getString("bookId");
        final String bookHasPdf = getIntent().getExtras().getString("bookHasPdf");
        final String bookTotalBooks = getIntent().getExtras().getString("bookTotalBooks");
        final String bookPubName = getIntent().getExtras().getString("bookPubName");
        final String bookPubDate = getIntent().getExtras().getString("bookPubDate");
        final String bookIsbn = getIntent().getExtras().getString("bookIsbn");
        final String bookIssn = getIntent().getExtras().getString("bookIssn");
        final String bookPdfUrl = getIntent().getExtras().getString("bookPdfUrl");
        final String lbs = getIntent().getExtras().getString("lbs");
        //INTENT PASSED BY ANOTHER ACTIVITY TEST

        // ACTION BAR CUSTOMIZATION
        setTitle("Book Information");
        // ACTION BAR CUSTOMIZATION

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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

        Log.d("debug", "==============================================================");
        Log.d("debug", "Title : " + bookTitle);
        Log.d("debug", "Author : " + bookAuthor);
        Log.d("debug", "Image : " + bookImage);
        Log.d("debug", "ID : " + bookId);
        Log.d("debug", "HasPdf : " + bookHasPdf);
        Log.d("debug", "Total Books : " + bookTotalBooks);
        Log.d("debug", "Pub Name : " + bookPubName);
        Log.d("debug", "Pub Date : " + bookPubDate);
        Log.d("debug", "Book ISBN : " + bookIsbn);
        Log.d("debug", "Book ISSN : " + bookIssn);
        Log.d("debug", "Book Pdf URL : " + bookPdfUrl);
        Log.d("debug", "Libraries : " + lbs);

        //SETTING INTENT
        //String url = dataList.get(position).getCover();
        Glide.with(this)
                .load(bookImage)
                .apply(
                        new RequestOptions()
                                .centerCrop()
                                    /*.error(R.drawable.spung)*/
                                .placeholder(R.drawable.book_sample_1))
                //.placeholder(R.drawable.process_image))
                .into(bookCoverTextView);
        bookTitleTextView.setText(bookTitle);
        bookAuthorTextView.setText(bookAuthor);

        //BUTTONS ==================================================================================
        final Button buttonDownloadPDF = (Button)findViewById(R.id.DownloadPDFButton);

        if(bookHasPdf.equals("NOT AVAILABLE")){
            buttonDownloadPDF.setText("PDF Not Available");
        }
        else
        {
            buttonDownloadPDF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), bookHasPdf ,Toast.LENGTH_SHORT).show();
                    //buttonDownloadPDF.setText("Bookmarked!");
                    //Snackbar.make(findViewById(android.R.id.content), "Feature not Available Yet!", Snackbar.LENGTH_LONG)
                    //        .setActionTextColor(Color.RED)
                    //        .show();
                }
            });
        }

        final Button buttonBookmark = (Button)findViewById(R.id.BookmarkButton);
        buttonBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonBookmark.setText("Bookmarked!");
                buttonBookmark.setTextColor(Color.BLACK);
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
