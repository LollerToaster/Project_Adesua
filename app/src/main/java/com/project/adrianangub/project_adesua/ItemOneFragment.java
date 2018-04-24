package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

    private TextView mTextViewEmpty, seeMore;
    private ProgressBar mProgressBarLoading;
    private ImageView mImageViewEmpty;
    private RecyclerView mRecyclerView,mRecyclerView2;
    private ListAdapter mListadapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //a list to store all the products
    //List<ItemOneFragmentModel> productList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_item_one, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //DANGER====================================================================================
        //mRecyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        //DANGER====================================================================================
        //mTextViewEmpty = (TextView)view.findViewById(R.id.textViewEmpty);
        //mImageViewEmpty = (ImageView)view.findViewById(R.id.imageViewEmpty);
        mProgressBarLoading = (ProgressBar)view.findViewById(R.id.progressBarLoading);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //DANGER====================================================================================
        //mRecyclerView2.setLayoutManager(layoutManager);
        //DANGER====================================================================================

        //==========================================================================================

        seeMore = (TextView)view.findViewById(R.id.seeMore);
        seeMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchResults.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getContext().startActivity(intent);
                Toast.makeText(getActivity(), "See more was clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //==========================================================================================

        ArrayList data = new ArrayList<ItemOneFragmentDataModel>();
        for (int i = 0; i < ItemOneFragmentDataInformation.id.length; i++)
        {
            data.add(
                    new ItemOneFragmentDataModel
                            (
                                    ItemOneFragmentDataInformation.id[i],
                                    ItemOneFragmentDataInformation.textArray[i],
                                    ItemOneFragmentDataInformation.dateArray[i]
                            ));
        }

        mListadapter = new ListAdapter(data);
        mRecyclerView.setAdapter(mListadapter);

        //DANGER====================================================================================
        //mRecyclerView2.setAdapter(mListadapter);
        //DANGER====================================================================================

        return view;
    }

    /*
    public void onClick(View v) {
        Toast.makeText(getActivity(), "See more was clicked", Toast.LENGTH_SHORT).show();
    }
    */

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<ItemOneFragmentDataModel> dataList;

        public ListAdapter(ArrayList<ItemOneFragmentDataModel> data)
        {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textViewText;
            TextView textViewComment;
            TextView textViewDate;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewText = (TextView) itemView.findViewById(R.id.text);
                this.textViewComment = (TextView) itemView.findViewById(R.id.comment);
                this.textViewDate = (TextView) itemView.findViewById(R.id.date);
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
            holder.textViewText.setText(dataList.get(position).getText());
            holder.textViewComment.setText(dataList.get(position).getComment());
            holder.textViewDate.setText(dataList.get(position).getDate());

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), bookInfoPage_v2.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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