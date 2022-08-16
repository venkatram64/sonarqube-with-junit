package com.venkat.service;

import com.venkat.model.Book;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenericServiceTest {

    @Mock
    private LibraryService.DAO dao;

    @Mock(name="outLogger")
    private LoggerService logger1;

    @Mock(name="errorLogger")
    private LoggerService logger2;

    @InjectMocks
    private GenericService service;  //System Under Test (SUT)

    @Test
    void testFetchBookById(){
        int myP = 42;
        /*

        Order o = new Order();
        o.setItem(123)
         */
        when(dao.fetchBookById2(myP)).thenReturn(LibraryService.DEFAULT_BOOK); //stub the method
        Book book = service.fetchBookById2(42);
        MatcherAssert.assertThat(book, CoreMatchers.is(CoreMatchers.equalTo(LibraryService.DEFAULT_BOOK)));
        /*verify(logger1).log(anyString());          //whether logger1.log() is called will be verified
        verify(logger2).logToErr(anyString());*/    //whether logger1.log() is called will be verified
    }


}