package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HomeTwoFragment extends Fragment {
    public static HomeTwoFragment newInstance() {
        HomeTwoFragment fragment = new HomeTwoFragment();
        return fragment;
    }

    private ProgressBar mProgressBarLoading;
    private RecyclerView mRecyclerView;
    private ListAdapter mListadapter;
    private static final String URL_CLASSROOMS = "http://adesuaapi.spottyus.com/student/classrooms?uid=4007";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Settling the Model
    ArrayList<HomeOneFragmentDataModel> classroomList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_fragment_two, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mProgressBarLoading = (ProgressBar)view.findViewById(R.id.progressBarLoading);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //Divider Erase it later
        /*
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getContext()
        ));
        */

        // Calling Volley functions to retrieve data from database
        loadClassrooms();

        return view;
    }

    // VOLLEY  =====================================================================================
    private void loadClassrooms() {
        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_CLASSROOMS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    //converting the string to json array object
                    JSONArray array = new JSONArray(response);

                    //adding the product to product list
                    classroomList = new ArrayList<>();
                    ArrayList classroomList = new ArrayList<HomeTwoFragmentDataModel>();

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++)
                    {
                        //getting product object from json array
                        JSONObject classroom = array.getJSONObject(i);
                        JSONObject teacher = classroom.getJSONObject("teacher");

                        //Debugging Purposes
                        Log.d("Response", response);
                        //Log.d("debug", " =======================================================");
                        //Log.d("debug", " ");
                        //Log.d("debug", "Title : " + classroom.getString("vclass_name"));
                        //Log.d("debug", "Title : " + classroom.getString("vclass_grade"));
                        //Log.d("debug", "Title : " + classroom.getString("vclass_desc"));
                        //Log.d("debug", "Title : " + classroom.getString("vclass_numstud"));
                        //Log.d("debug", " ");
                        //Log.d("debug", " =======================================================");

                        classroomList.add(
                                new HomeTwoFragmentDataModel
                                        (
                                                classroom.getString("vcstud_id"),
                                                classroom.getString("stud_id_fk"),
                                                classroom.getString("vcstud_status"),
                                                classroom.getString("vclass_id_fk"),
                                                classroom.getString("vclass_id"),
                                                classroom.getString("vclass_pubid"),
                                                classroom.getString("vclass_name"),
                                                classroom.getString("vclass_grade"),
                                                classroom.getString("vclass_desc"),
                                                classroom.getString("vclass_numstud"),
                                                classroom.getString("vlcass_numbook"),
                                                classroom.getString("vclass_teach_fk"),
                                                classroom.getString("school_id_fk"),
                                                teacher.getString("fulname"),
                                                teacher.getString("username"),
                                                teacher.getString("email"),
                                                teacher.getString("ini")
                                        ));

                        mListadapter = new HomeTwoFragment.ListAdapter(classroomList);
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
    // VOLLEY  =====================================================================================

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<HomeTwoFragmentDataModel> dataList;

        public ListAdapter(ArrayList<HomeTwoFragmentDataModel> data)
        {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView className;
            TextView classGrade;
            TextView classDescription;
            TextView classStudents;
            TextView classBooks;
            TextView classTeacher;
            //ImageView classroomImage;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.className = (TextView) itemView.findViewById(R.id.className);
                this.classGrade = (TextView) itemView.findViewById(R.id.classGrade);
                this.classDescription = (TextView) itemView.findViewById(R.id.classDescription);
                this.classStudents = (TextView) itemView.findViewById(R.id.classStudents);
                this.classBooks = (TextView) itemView.findViewById(R.id.classBooks);
                this.classTeacher = (TextView) itemView.findViewById(R.id.classTeacher);
                //this.classroomImage = (ImageView) itemView.findViewById(R.id.classIdent);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_two_recycler_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.className.setText(dataList.get(position).getVclassName());
            holder.classGrade.setText(dataList.get(position).getVclassGrade());
            holder.classDescription.setText(dataList.get(position).getVclassDesc());
            holder.classStudents.setText(dataList.get(position).getVclassNumStud());
            holder.classBooks.setText(dataList.get(position).getVclassNumBook());
            holder.classTeacher.setText(dataList.get(position).getTeacherFulname());

            // Image Holder Using Glide
            /*
            String url = dataList.get(position).getCover();
            Glide.with(getContext())
                    .load(url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            mProgressBarLoading.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            mProgressBarLoading.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.bookCover);
            */


            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), VirtualClassroomActivity.class);

                    //PASSING VALUES TO INTENT SECTION
                    //intent.putExtra("virtualClassName", dataList.get(position).getVclassName());
                    //intent.putExtra("virtualClassGrade", dataList.get(position).getVclassGrade());
                    //intent.putExtra("virtualClassroomDesc", dataList.get(position).getVclassDesc());
                    //PASSING VALUES TO INTENT SECTION

                    //intent.putExtra("bookSynopsis", dataList.get(position).getBookSynopsis());
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