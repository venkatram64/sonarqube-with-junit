package com.venkat.service;

import com.venkat.model.Book;
import com.venkat.model.Topic;

import java.time.Year;

public class LibraryService {
    public static Book DEFAULT_BOOK = new Book("Effective Java", 280,
            Topic.COMPUTING, Year.of(2000), "Joshua Block");
    private final DAO dao;

    public LibraryService(DAO dao){
        this.dao = dao;
    }

    public boolean hasBookWithId(int id){
        return this.dao.fetchBookById(id) != null;
    }

    public boolean hasBookWithId2(Integer id){
        if(id == null){
            throw new IllegalArgumentException("id must be a positive integer");
        }
        return this.dao.fetchBookById2(id) != null;
    }

    public boolean hasBookWithTopicAndAuthor(Topic topic, String author){
        System.out.println("LibraryService.hasBookWithTopicAndAuthor");
        return true;
    }

    public Book hasBookWithTitle(String title){
        return this.dao.fetchBookByTitle(title);
    }

    public static class DAO {
        private Integer id;

        public void setId(Integer id){
            this.id = id;
        }

        public Integer getId(){
            return this.id;
        }

        Book fetchBookById(int id){
            System.out.println("DAO.fetchBookById");
            return DEFAULT_BOOK;
        }

        Book fetchBookById2(Integer id){
            System.out.println("DAO.fetchBookById2");
            return DEFAULT_BOOK;
        }

        Book fetchBookByTitle(String title){
            System.out.println("DAO.fetchBookByTitle");
            return DEFAULT_BOOK;
        }
    }


}
