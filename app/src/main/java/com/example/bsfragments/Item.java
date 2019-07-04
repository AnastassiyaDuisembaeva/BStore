package com.example.bsfragments;

public class Item {

    public Item(){}

    private String name;
    private String author;
    private String bookGanre;
    private String price;

    private String imageBook;
    //  private int imageBook;

    public Item(String name, String author, String bookGanre, String price, String imageBook){

        this.name = name;
        this.author = author;
        this.bookGanre = bookGanre;
        this.price = price;
        this.imageBook = imageBook;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", bookGanre='" + bookGanre + '\'' +
                ", price='" + price + '\'' +
                ", imageBook='" + imageBook + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookGanre() {
        return bookGanre;
    }

    public void setBookGanre(String bookGanre) {
        this.bookGanre = bookGanre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }
}
