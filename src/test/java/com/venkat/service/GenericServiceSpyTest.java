package com.venkat.service;

import com.venkat.model.Book;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GenericServiceSpyTest {

    //spy is used for third party API or legacy code

    @Spy //spy is wrapper around the real object, is a partial mock
    private LibraryService.DAO dao;

    @Mock(name="outLogger")
    private LoggerService logger1;

    @Mock(name="errorLogger")
    private LoggerService logger2;

    @InjectMocks
    private GenericService service;  //System Under Test (SUT)

    @Test
    void testSpy(){
        final Book book = service.fetchBook(67, "Effective Java");
        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.equalTo(LibraryService.DEFAULT_BOOK)));
    }

    @Test
    void testSpyWithPartial(){
        //when(dao.fetchBookById2(anyInt())).thenReturn(null); //stud
        //stubbing one of the methods of LibraryService.DAO in Spy dao object
        doReturn(null).when(dao).fetchBookById2(anyInt()); //for Spy this is the way
        final Book book = service.fetchBook(67, "Effective Java");
        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.equalTo(LibraryService.DEFAULT_BOOK)));
        verify(dao).fetchBookByTitle(anyString()); //verified
    }

    @Test
    void testRealSpy(){

        final LibraryService.DAO real = new LibraryService.DAO();
        final LibraryService.DAO spy = spy(real);
        //real.fetchBookById2(67); //this one does not work
        spy.fetchBookById2(67);
        verify(spy).fetchBookById2(anyInt());
    }

    @Test
    void testRealSpy2(){
        final LibraryService.DAO real = new LibraryService.DAO();
        final LibraryService.DAO spy = spy(real);
        //spy.setId(1);
        doReturn(1).when(spy).getId();
        MatcherAssert.assertThat(1, CoreMatchers.is(CoreMatchers.equalTo(spy.getId())));
    }

    @Test
    void testRealSpy3(){
        final LibraryService.DAO real = new LibraryService.DAO();
        final LibraryService.DAO spy = spy(real);
        doReturn(null).when(spy).fetchBookById2(67);
        final Book book = spy.fetchBookById2(67);
        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.nullValue()));
    }

}