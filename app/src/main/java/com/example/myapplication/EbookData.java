package com.example.myapplication;

public class EbookData {
//    private  int id;
    private  String id ,bookName, title, shortDescp, bookTopic, bookTxt, docEmail;

    EbookData(String id, String bookName, String title, String shortDescp, String bookTopic, String bookTxt, String docEmail) {
        this.id = id;
        this.bookName = bookName;
        this.title = title;
        this.shortDescp = shortDescp;
        this.bookTopic = bookTopic;
        this.bookTxt = bookTxt;
        this.docEmail = docEmail;
    }
    public String getId() {
        return id;
    }


    public String getBookName() {
        return bookName;
    }


    public String getTitle() {
        return title;
    }

    public String getShortDescp() {
        return shortDescp;
    }


    public String getBookTopic() {
        return bookTopic;
    }


    public String getBookTxt() {
        return bookTxt;
    }


    public String getDocEmail() {
        return docEmail;
    }

}

