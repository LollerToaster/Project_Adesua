package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

public class HomeOneFragment extends Fragment {
    public static HomeOneFragment newInstance() {
        HomeOneFragment fragment = new HomeOneFragment();
        return fragment;
    }

    private TextView seeMore, seeMore2;
    private RecyclerView mRecyclerView, mRecyclerView2;
    private ListAdapter mListadapter;
    private static final String URL_PRODUCTS = "http://adesuaapi.spottyus.com/book/search?uid=4007";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Settling the Model
    ArrayList<HomeOneFragmentDataModel> bookList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_fragment_one, container, false);

        // ROW 1
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        // Calling Volley functions to retrieve data from database
        loadRow1();

        //SEE MORE SECTION =========================================================================
        seeMore = (TextView)view.findViewById(R.id.seeMore);
        seeMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchResults.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getContext().startActivity(intent);
                //Toast.makeText(getActivity(), "See more was clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // VOLLEY  =====================================================================================
    private void loadRow1()
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
                    ArrayList bookList = new ArrayList<HomeOneFragmentDataModel>();

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++)
                    {
                        //getting product object from json array
                        JSONObject product = array.getJSONObject(i);

                        //Debugging Purposes
                        /*
                        Log.d("debug", " ");
                        Log.d("debug", "Title : " + product.getString("t"));
                        Log.d("debug", "Cover : " + product.getString("cover"));
                        Log.d("debug", "ID : " + product.getString("id"));
                        Log.d("debug", "Author : " + product.getString("auth"));
                        Log.d("debug", "Call Card : " + product.getString("callcrd"));
                        Log.d("debug", "Has Pdf : " + product.getString("haspdf"));
                        Log.d("debug", " ");
                        Log.d("debug", " =======================================================");
                        */

                        bookList.add(
                                new HomeOneFragmentDataModel
                                        (
                                                product.getString("t"),
                                                product.getString("cover"),
                                                product.getString("id"),
                                                product.getString("auth"),
                                                product.getString("callcrd"),
                                                product.getString("haspdf"),
                                                product.getString("totalbooks"),
                                                product.getString("pubname"),
                                                product.getString("pubdate"),
                                                product.getString("isbn"),
                                                product.getString("issn"),
                                                product.getString("lbs")
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
        private ArrayList<HomeOneFragmentDataModel> dataList;
        public ListAdapter(ArrayList<HomeOneFragmentDataModel> data)
        {
            this.dataList = data;
        }
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            ProgressBar Progress;
            TextView textViewBookTitle;
            ImageView bookCover;
            TextView textViewBookAuthor;
            TextView textViewBookSynopsis;
            TextView textViewBookRating;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.Progress = (ProgressBar)itemView.findViewById(R.id.progressBar);
                this.textViewBookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
                this.bookCover = itemView.findViewById(R.id.bookCover);
                this.textViewBookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
                this.textViewBookSynopsis = (TextView) itemView.findViewById(R.id.bookSynopsis);
                this.textViewBookRating = (TextView) itemView.findViewById(R.id.bookRating);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_one_recycler_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.textViewBookTitle.setText(dataList.get(position).getT());

            // Image Holder Using Glide
            String url = dataList.get(position).getCover();
            Glide.with(getContext())
                    .load(url)
                    .apply(
                            new RequestOptions()
                                    .centerCrop()
                                    /*.error(R.drawable.spung)*/
                                    //.placeholder(R.drawable.progress_animation))
                                    .placeholder(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.primary))))
                    //.placeholder(R.drawable.process_image))
                    .transition(withCrossFade())
                    .into(holder.bookCover);

            holder.textViewBookAuthor.setText(dataList.get(position).getAuth());
            holder.textViewBookSynopsis.setText(dataList.get(position).getAuth());
            holder.textViewBookRating.setText(dataList.get(position).getCallcrd());

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), "Item  is clicked.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), BookInformationActivity.class);

                    //PASSING VALUES TO INTENT SECTION
                    intent.putExtra("bookTitle", dataList.get(position).getT());
                    intent.putExtra("bookAuthor", dataList.get(position).getAuth());
                    intent.putExtra("bookImage", dataList.get(position).getCover());
                    //PASSING VALUES TO INTENT SECTION

                    getContext().startActivity(intent);

                    //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

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