package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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

import static android.widget.LinearLayout.VERTICAL;

public class ItemTwoFragment extends Fragment {
    public static ItemTwoFragment newInstance() {
        ItemTwoFragment fragment = new ItemTwoFragment();
        return fragment;
    }

    private ProgressBar mProgressBarLoading;
    private RecyclerView mRecyclerView;
    private ListAdapter mListadapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_item_two, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mProgressBarLoading = (ProgressBar)view.findViewById(R.id.progressBarLoading);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getContext()
        ));

        ArrayList data = new ArrayList<ItemTwoFragmentDataInformation>();
        for (int i = 0; i < ItemTwoFragmentDataInformation.bookNumber.length; i++)
        {
            data.add(
                    new ItemTwoFragmentDataModel
                            (
                                    ItemTwoFragmentDataInformation.bookNumber[i],
                                    ItemTwoFragmentDataInformation.bookTitleArray[i],
                                    ItemTwoFragmentDataInformation.bookAuthorArray[i],
                                    ItemTwoFragmentDataInformation.bookSynopsisArray[i],
                                    ItemTwoFragmentDataInformation.bookRatingArray[i]
                            ));
        }

        mListadapter = new ListAdapter(data);
        mRecyclerView.setAdapter(mListadapter);

        return view;
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<ItemTwoFragmentDataModel> dataList;

        public ListAdapter(ArrayList<ItemTwoFragmentDataModel> data)
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_two_recycler_item, parent, false);

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
                    Intent intent = new Intent(getContext(), bookInfoPage_v2.class);
                    intent.putExtra("bookSynopsis", dataList.get(position).getBookSynopsis());
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