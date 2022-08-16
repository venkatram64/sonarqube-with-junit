package com.venkat.model;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

public class Book {

    private final String title;
    private final int pageCount;
    private final Topic topic;
    private final Year publicationDate;
    private final List<String> authors;

    public Book(String title, int pageCount, Topic topic, Year publicationDate, String... authors) {
        this.title = title;
        this.pageCount = pageCount;
        this.topic = topic;
        this.publicationDate = publicationDate;
        this.authors = Arrays.asList(authors);
    }

    public String getTitle() {
        return title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Topic getTopic() {
        return topic;
    }

    public Year getPublicationDate() {
        return publicationDate;
    }

    public List<String> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", pageCount=" + pageCount +
                ", topic=" + topic +
                ", publicationDate=" + publicationDate +
                ", authors=" + authors +
                '}';
    }
}
