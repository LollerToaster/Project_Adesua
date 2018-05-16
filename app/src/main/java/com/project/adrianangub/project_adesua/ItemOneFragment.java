package com.project.adrianangub.project_adesua;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemOneFragment extends Fragment {
    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    private TextView seeMore, seeMore2;
    private ProgressBar mProgressBarLoading;
    private RecyclerView mRecyclerView,mRecyclerView2;
    private ListAdapter mListadapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //REFERENCE
    //https://medium.com/@Pang_Yao/android-fragment-use-recyclerview-cardview-4bc10beac446

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_item_one, container, false);
        mProgressBarLoading = (ProgressBar)view.findViewById(R.id.progressBarLoading);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView2.setLayoutManager(layoutManager2);


        //SEE MORE SECTION =========================================================================
        seeMore = (TextView)view.findViewById(R.id.seeMore);
        seeMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchResults.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getContext().startActivity(intent);
                Toast.makeText(getActivity(), "See more was clicked", Toast.LENGTH_SHORT).show();
            }
        });

        seeMore2 = (TextView)view.findViewById(R.id.seeMore2);
        seeMore2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchResults.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getContext().startActivity(intent);
                Toast.makeText(getActivity(), "See more was clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //SEE MORE SECTION =========================================================================

        ArrayList data = new ArrayList<ItemOneFragmentDataModel>();
        for (int i = 0; i < ItemOneFragmentDataInformation.bookNumber.length; i++)
        {
            data.add(
                    new ItemOneFragmentDataModel
                            (
                                    ItemOneFragmentDataInformation.bookNumber[i],
                                    ItemOneFragmentDataInformation.bookTitleArray[i],
                                    ItemOneFragmentDataInformation.bookAuthorArray[i],
                                    ItemOneFragmentDataInformation.bookSynopsisArray[i],
                                    ItemOneFragmentDataInformation.bookRatingArray[i]
                            ));
        }



        mListadapter = new ListAdapter(data);
        mRecyclerView2.setAdapter(mListadapter);
        mRecyclerView.setAdapter(mListadapter);
        return view;
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<ItemOneFragmentDataModel> dataList;

        public ListAdapter(ArrayList<ItemOneFragmentDataModel> data)
        {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textViewBookNumber;
            TextView textViewBookTitle;
            TextView textViewBookAuthor;
            TextView textViewBookSynopsis;
            TextView textViewBookRating;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewBookNumber = (TextView) itemView.findViewById(R.id.bookNumber);
                this.textViewBookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
                this.textViewBookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
                this.textViewBookSynopsis = (TextView) itemView.findViewById(R.id.bookSynopsis);
                this.textViewBookRating = (TextView) itemView.findViewById(R.id.bookRating);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_one_recycler_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.textViewBookNumber.setText(dataList.get(position).getBookNumber());
            holder.textViewBookTitle.setText(dataList.get(position).getBookTitle());
            holder.textViewBookAuthor.setText(dataList.get(position).getBookAuthor());
            holder.textViewBookSynopsis.setText(dataList.get(position).getBookSynopsis());
            holder.textViewBookRating.setText(dataList.get(position).getBookRating());

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), "Item  is clicked.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), bookInfoPage_v2.class);
                    //PASSING VALUES TO INTENT SECTION
                    intent.putExtra("bookSynopsis", dataList.get(position).getBookSynopsis());
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