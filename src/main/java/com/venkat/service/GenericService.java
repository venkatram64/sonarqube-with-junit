package com.venkat.service;

import com.venkat.model.Book;

/**
 * Mockito will do in this order
 * 1. Constructor based injection
 * 2. Setter based injection
 * 3. Field based injection
 */

public class GenericService {

    private LibraryService.DAO dao;

    private LoggerService outLogger;
    private LoggerService errorLogger;

    /*public GenericService(LibraryService.DAO dao, LoggerService logger) {
        this.dao = dao;
        this.logger = logger;
    }*/

    public Book fetchBookById2(Integer id){
        System.out.println("GenericService.fetBookById2");
        final Book book = dao.fetchBookById2(id);
        outLogger.log(book.getTitle());
        outLogger.log(book.getTitle());
        errorLogger.logToErr(book.getTitle());
        return book;
    }

    public Book fetchBook(Integer id, String title){
        System.out.println("GenericService.fetchBook.");
        Book book = dao.fetchBookById2(id);
        if(book == null){
            book = dao.fetchBookByTitle(title);
        }
        outLogger.log(book.getTitle());
        outLogger.log(book.getTitle());
        errorLogger.logToErr(book.getTitle());
        return book;
    }
}
