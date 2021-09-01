package model;

public class Page 
{

    public Page(String url)
    {
       this.url = url;
       this.views = 0;
       this.uniqueViews = 0;
    }

    public String url;
    public int views;
    public int uniqueViews;
}
