package com.venkat.service;

import com.venkat.model.Book;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Family of methods
 * 1. doReturn()
 * 2. doThrow()
 * 3. doAnswer()
 * 4. doNothing()
 * 5. doCallRealMethod()
 */


@ExtendWith(MockitoExtension.class)
class GenericServiceTwoTest {

    @Mock
    private GenericServiceTwo.DAO dao;

    @InjectMocks
    private GenericServiceTwo service;  //System Under Test (SUT)

    @Test
    void testDoThrow(){
        //when(util.getBook(anyString())).thenThrow(RuntimeException.class); //for returned something by method and exception
        doThrow(RuntimeException.class).when(dao).foo();  //here foo() is void method
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.fetchBookById(67);
        });
        MatcherAssert.assertThat(exception, CoreMatchers.is(CoreMatchers.instanceOf(RuntimeException.class)));
    }

    @Test
    @Disabled
    void testDoReturn(){  //not working with new jars
        //spy is a partial mock

        final GenericServiceTwo serviceTwo = spy(service);  //spy is on real object
        //below one does not work
        //when(serviceTwo.fetchBookById(67)).thenReturn(GenericServiceTwo.DEFAULT_BOOK);
        //below works
        doReturn(GenericServiceTwo.DEFAULT_BOOK).when(serviceTwo).fetchBookById(67);
    }

    @Test
    void testDoAnswer(){
        //static time, at compile time
        //when(dao.fetchBookById(anyInt())).thenReturn(GenericServiceTwo.DEFAULT_BOOK);

        //runtime
        doAnswer(invocation -> {
            final Integer id = invocation.getArgument(0, Integer.class);
            return id == 67 ? GenericServiceTwo.DEFAULT_BOOK : null;
        }).when(dao).fetchBookById(anyInt());   //stubbing

        Book book = service.fetchBookById(67);
        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.equalTo(GenericServiceTwo.DEFAULT_BOOK)));
    }

    @Test
    void testDoNothing(){

        doNothing()//for void call
                .doThrow(RuntimeException.class)
                .when(dao).foo();

        dao.foo(); //first case i.e doNothing --> for doNothing
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.fetchBookById(67); //--> for second case i.e doThro
        });
        MatcherAssert.assertThat(exception, CoreMatchers.is(CoreMatchers.instanceOf(RuntimeException.class)));
    }

    @Test
    void testDoCallRealMethod(){
        doCallRealMethod().when(dao).fetchBookById(anyInt());
        //when(dao.fetchBookByTitle(anyString())).thenReturn(GenericServiceTwo.DEFAULT_BOOK);
        service.fetchBookById(67);
    }

}