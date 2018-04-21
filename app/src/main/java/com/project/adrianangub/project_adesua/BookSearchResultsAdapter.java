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

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.ProductViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<BookSearchResultsDataModel> productList;

    //getting the context and product list with constructor
    public BookSearchResultsAdapter(Context mCtx, List<BookSearchResultsDataModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_book_search_results, null);
        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        BookSearchResultsDataModel product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        holder.buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v){
                Toast.makeText(mCtx,"ENTRY NUMBER " + position ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mCtx, bookInfoPage_v2.class);
                mCtx.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
        Button buttonCheck;

        public ProductViewHolder(View itemView) {
            super(itemView);

            buttonCheck = (Button) itemView.findViewById(R.id.Check);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}