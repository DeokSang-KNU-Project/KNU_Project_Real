package com.example.myapplication2;

public class FileData {
    private String Viewname;
    private String name;
    private String URLname;

    public void setData(String Viewname, String name, String URLname){
        this.name = name;
        this.Viewname = Viewname;
        this.URLname = URLname;
    }

    public String getName(){
        return this.name;
    }
    public String getViewname(){
        return this.Viewname;
    }
    public String getURLname(){
        return this.URLname;
    }
}
