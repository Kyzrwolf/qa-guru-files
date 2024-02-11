package guru.qa.files.utils;

import java.util.List;

public class Book {
    private String title;
    private String author;
    private String published;
    private String precededBy;
    private int pages;
    private List<String> characters;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getPrecededBy() {
        return precededBy;
    }

    public void setPrecededBy(String precededBy) {
        this.precededBy = precededBy;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Json{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published='" + published + '\'' +
                ", precededBy='" + precededBy + '\'' +
                ", pages=" + pages +
                ", characters=" + characters +
                '}';
    }
}
