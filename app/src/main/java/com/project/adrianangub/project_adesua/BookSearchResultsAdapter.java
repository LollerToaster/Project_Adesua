package com.project.adrianangub.project_adesua;

/**
 * Created by adrian angub on 18/04/2018.
 */

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<BookSearchResultsDataModel> bookList;

    public BookSearchResultsAdapter(Context mCtx, List<BookSearchResultsDataModel> bookList) {
        this.mCtx = mCtx;
        this.bookList = bookList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_book_search_results, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        BookSearchResultsDataModel book = bookList.get(position);

        Glide.with(mCtx)
                .load(book.getCover())
                .apply(
                        new RequestOptions()
                                .centerCrop()
                                    /*.error(R.drawable.spung)*/
                                //.placeholder(R.drawable.progress_animation))
                                .placeholder(new ColorDrawable(ContextCompat.getColor(mCtx, R.color.primary))))
                //.placeholder(R.drawable.process_image))
                .transition(withCrossFade())
                .into(holder.bookCover);

        holder.textViewTitle.setText(String.valueOf(book.getT()));
        holder.textViewName.setText(String.valueOf(book.getAuth()));

        //ButtonClick to check the book
        holder.buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Toast.makeText(mCtx,"ENTRY NUMBER " + position ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mCtx, BookInformationActivity.class);

                //PASSING VALUES TO INTENT SECTION
                intent.putExtra("bookTitle", bookList.get(position).getT());
                intent.putExtra("bookAuthor", bookList.get(position).getAuth());
                intent.putExtra("bookImage", bookList.get(position).getCover());
                intent.putExtra("bookId", bookList.get(position).getId());
                intent.putExtra("bookHasPdf", bookList.get(position).getHaspdf());
                intent.putExtra("bookTotalBooks", bookList.get(position).getTotalbooks());
                intent.putExtra("bookPubName", bookList.get(position).getPubname());
                intent.putExtra("bookPubDate", bookList.get(position).getPubdate());
                intent.putExtra("bookIsbn", bookList.get(position).getIsbn());
                intent.putExtra("bookIssn", bookList.get(position).getIssn());
                intent.putExtra("bookPdfUrl", bookList.get(position).getPdfurl());
                intent.putExtra("lbs", bookList.get(position).getLbs());

                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewName;
        ImageView bookCover;
        Button buttonCheck;

        public ProductViewHolder(View itemView) {
            super(itemView);

            this.bookCover = itemView.findViewById(R.id.bookCover);
            textViewTitle = itemView.findViewById(R.id.bookTitle);
            textViewName = itemView.findViewById(R.id.bookAuthor);
            buttonCheck = (Button) itemView.findViewById(R.id.Check);
        }
    }
}