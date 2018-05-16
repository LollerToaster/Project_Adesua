package com.project.adrianangub.project_adesua;

/**
 * Created by adrian angub on 18/04/2018.
 */

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.dataListViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<BookSearchResultsDataModel> dataList;

    //getting the context and product list with constructor
    public BookSearchResultsAdapter(Context mCtx, List<BookSearchResultsDataModel> dataList) {
        this.mCtx = mCtx;
        this.dataList = dataList;
    }


    @Override
    public dataListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_book_search_results, null);
        return new dataListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(dataListViewHolder holder, final int position) {
        //getting the product of the specified position
        BookSearchResultsDataModel data = dataList.get(position);

        //binding the data with the viewholder views
        holder.textViewBookNumber.setText(data.getBookNumber());
        holder.textViewBookTitle.setText(data.getBookTitle());
        holder.textViewBookAuthor.setText(data.getBookAuthor());
        holder.textViewBookSynopsis.setText(data.getBookSynopsis());
        holder.textViewBookRating.setText(data.getBookRating());

        //ButtonClick to check the book
        holder.buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(mCtx,"ENTRY NUMBER " + position ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mCtx, bookInfoPage_v2.class);
                intent.putExtra("bookSynopsis", dataList.get(position).getBookSynopsis());
                mCtx.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class dataListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewBookNumber;
        TextView textViewBookTitle;
        TextView textViewBookAuthor;
        TextView textViewBookSynopsis;
        TextView textViewBookRating;
        Button buttonCheck;

        public dataListViewHolder(View itemView) {
            super(itemView);

            this.textViewBookNumber = (TextView) itemView.findViewById(R.id.bookNumber);
            this.textViewBookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
            this.textViewBookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
            this.textViewBookSynopsis = (TextView) itemView.findViewById(R.id.bookSynopsis);
            this.textViewBookRating = (TextView) itemView.findViewById(R.id.bookRating);
            buttonCheck = (Button) itemView.findViewById(R.id.Check);
        }
    }
}