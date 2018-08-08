package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bumptech.glide.*;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class HomeThreeFragment extends Fragment {
    public static HomeThreeFragment newInstance() {
        HomeThreeFragment fragment = new HomeThreeFragment();
        return fragment;
    }

    private RecyclerView mRecyclerView;
    private ListAdapter mListadapter;
    private static final String URL_PRODUCTS = "http://adesuaapi.spottyus.com/book/search?uid=4007";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Settling the Model
    ArrayList<HomeThreeFragmentDataModel> bookList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_fragment_three, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        // Calling Volley functions to retrieve data from database
        loadBookmarks();

        return view;
    }

    // VOLLEY  =====================================================================================
    private void loadBookmarks()
    {
        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    //converting the string to json array object
                    JSONArray array = new JSONArray(response);

                    //adding the product to product list
                    bookList = new ArrayList<>();
                    ArrayList bookList = new ArrayList<HomeThreeFragment>();

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++)
                    {
                        //getting product object from json array
                        JSONObject books = array.getJSONObject(i);

                        //Debugging Purposes
                        Log.d("debug", " =======================================================");
                        Log.d("debug", " ");
                        Log.d("debug", "Title : " + books.getString("t"));
                        Log.d("debug", "Cover : " + books.getString("cover"));
                        Log.d("debug", "ID : " + books.getString("id"));
                        Log.d("debug", "Author : " + books.getString("auth"));
                        Log.d("debug", "Call Card : " + books.getString("callcrd"));
                        Log.d("debug", "Has Pdf : " + books.getString("haspdf"));
                        Log.d("debug", "Total Books : " + books.getString("totalbooks"));
                        Log.d("debug", "Pub Name : " + books.getString("pubname"));
                        Log.d("debug", "Pub Date : " + books.getString("pubdate"));
                        Log.d("debug", "ISBN : " + books.getString("isbn"));
                        Log.d("debug", "ISSN : " + books.getString("issn"));
                        Log.d("debug", "PDF URL : " + books.getString("pdfurl"));
                        Log.d("debug", "LBS : " + books.getString("lbs"));
                        Log.d("debug", " ");
                        Log.d("debug", " =======================================================");

                        bookList.add(
                                new HomeThreeFragmentDataModel
                                        (
                                                books.getString("t"),
                                                books.getString("cover"),
                                                books.getString("id"),
                                                books.getString("auth"),
                                                books.getString("callcrd"),
                                                books.getString("haspdf"),
                                                books.getString("totalbooks"),
                                                books.getString("pubname"),
                                                books.getString("pubdate"),
                                                books.getString("isbn"),
                                                books.getString("issn"),
                                                books.getString("pdfurl"),
                                                books.getString("lbs")
                                        ));

                        mListadapter = new ListAdapter(bookList);
                        mRecyclerView.setAdapter(mListadapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do Something
                    }
                }) {
            //Header Responses
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String credentials = "admin:12345";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }};
        //adding our stringrequest to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    // ADAPTER SECTION =============================================================================
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<HomeThreeFragmentDataModel> dataList;
        public ListAdapter(ArrayList<HomeThreeFragmentDataModel> data)
        {
            this.dataList = data;
        }
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            //ProgressBar Progress;
            ImageView bookCover;
            TextView textViewBookTitle;
            TextView textViewBookAuthor;
            //TextView textViewBookSynopsis;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.bookCover = itemView.findViewById(R.id.bookCover);
                this.textViewBookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
                this.textViewBookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
                //this.textViewBookSynopsis = (TextView) itemView.findViewById(R.id.bookSynopsis);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_three_recycler_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {

            // Image Holder Using Glide
            String url = dataList.get(position).getCover();
            Glide.with(getContext())
                    .load(url)
                    .apply(
                            new RequestOptions()
                                    .centerCrop()
                                    /*.error(R.drawable.spung)*/
                                    .placeholder(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.primary))))
                    //.placeholder(R.drawable.process_image))
                    .transition(withCrossFade())
                    .into(holder.bookCover);

            holder.textViewBookTitle.setText(dataList.get(position).getT());
            holder.textViewBookAuthor.setText(dataList.get(position).getAuth());

            //======================================================================================

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Toast.makeText(mCtx,"ENTRY NUMBER " + position ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), BookInformationActivity.class);

                    //PASSING VALUES TO INTENT SECTION
                    intent.putExtra("bookTitle", dataList.get(position).getT());
                    intent.putExtra("bookAuthor", dataList.get(position).getAuth());
                    intent.putExtra("bookImage", dataList.get(position).getCover());
                    intent.putExtra("bookId", dataList.get(position).getId());
                    intent.putExtra("bookHasPdf", dataList.get(position).getHaspdf());
                    intent.putExtra("bookTotalBooks", dataList.get(position).getTotalbooks());
                    intent.putExtra("bookPubName", dataList.get(position).getPubname());
                    intent.putExtra("bookPubDate", dataList.get(position).getPubdate());
                    intent.putExtra("bookIsbn", dataList.get(position).getIsbn());
                    intent.putExtra("bookIssn", dataList.get(position).getIssn());
                    intent.putExtra("bookPdfUrl", dataList.get(position).getPdfurl());
                    intent.putExtra("lbs", dataList.get(position).getLbs());

                    getContext().startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }

}