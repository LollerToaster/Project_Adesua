package com.project.adrianangub.project_adesua;

public class BookSearchResultsDataModel {

    String bookNumber;
    String bookTitle;
    String bookAuthor;
    String bookSynopsis;
    String bookRating;

    public BookSearchResultsDataModel(String bookNumber, String bookTitle, String bookAuthor, String bookSynopsis, String bookRating)
    {
        this.bookNumber = bookNumber;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookSynopsis = bookSynopsis;
        this.bookRating = bookRating;
    }

    public String getBookNumber()
    {
        return bookNumber;
    }
    public String getBookTitle()
    {
        return bookTitle;
    }
    public String getBookAuthor()
    {
        return bookAuthor;
    }
    public String getBookSynopsis()
    {
        return bookSynopsis;
    }
    public String getBookRating()
    {
        return bookRating;
    }
}