package com.venkat.service;

import com.venkat.model.Book;
import com.venkat.model.Topic;

import java.time.Year;

/**
 * Mockito will do in this order
 * 1. Constructor based injection
 * 2. Setter based injection
 * 3. Field based injection
 */

public class GenericServiceTwo {

    public static Book DEFAULT_BOOK = new Book("Effective Java", 280,
            Topic.COMPUTING, Year.of(2000), "Joshua Block");

    private final GenericServiceTwo.DAO dao;

    public GenericServiceTwo(GenericServiceTwo.DAO dao){
        this.dao = dao;
    }

    public Book fetchBookById(Integer id){
        System.out.println("GenericServiceTwo.hasBookWithId");
        final Book book = dao.fetchBookById(id);
        final Book book2 = dao.fetchBookByTitle("foo");
        dao.foo(); //will be thrown RuntimeException
        return book;
    }

    public boolean hasBookWithId(Integer id){
        if(id == null){
            throw new RuntimeException("id must be a positive integer");
        }
        return this.dao.fetchBookById(id) != null;
    }

    public boolean hasBookWithTopicAndAuthor(Topic topic, String author){
        System.out.println("LibraryService.hasBookWithTopicAndAuthor");
        return true;
    }

    public Book hasBookWithTitle(String title){
        return this.dao.fetchBookByTitle(title);
    }

    public static class DAO {
        Book fetchBookById(Integer id){
            System.out.println("DAO.fetchDataWith");
            return DEFAULT_BOOK;
        }
        Book fetchBookByTitle(String title){
            System.out.println("DAO.fetchBookByTitle");
            return DEFAULT_BOOK;
        }
        public void foo(){
            throw new RuntimeException();
        }
    }
}
