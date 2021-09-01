package model;

import java.util.Objects;

public class Page {

    public String url;
    public int views;
    public int uniqueViews;

    public Page(String url) {
        this.url = url;
        this.views = 0;
        this.uniqueViews = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
