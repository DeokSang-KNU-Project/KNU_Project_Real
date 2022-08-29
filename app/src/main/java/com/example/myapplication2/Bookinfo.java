package com.example.myapplication2;

public class Bookinfo {
    private String title;
    private String link;
    private String image;
    private String autor;
    private String publisher;
    private String description;
    private String discount;

    public void setVal(String title, String link, String image, String autor,
                       String publisher, String description, String discount){
        this.title = title;
        this.link = link;
        this.image = image;
        this.autor = autor;
        this.publisher =publisher;
        this.description = description;
        this.discount = discount;
    }

    public String getTitle(){
        return title;
    }
    public String getLink(){
        return link;
    }
    public String getImage(){
        return image;
    }
    public String getAutor(){
        return autor;
    }
    public String getPublisher(){
        return publisher;
    }
    public String getDescription(){
        return description;
    }
    public String getDiscount(){
        return discount;
    }
}
