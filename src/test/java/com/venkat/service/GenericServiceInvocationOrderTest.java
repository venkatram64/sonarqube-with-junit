package com.venkat.service;

import com.venkat.model.Book;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GenericServiceInvocationOrderTest {

    @Mock
    private LibraryService.DAO dao;

    @Mock(name="outLogger")
    private LoggerService logger1;

    @Mock(name="errorLogger")
    private LoggerService logger2;

    @InjectMocks
    private GenericService service;  //System Under Test (SUT)

    @Test
    @Disabled
    void testInvocationOrder(){
        when(dao.fetchBookById2(anyInt())).thenReturn(LibraryService.DEFAULT_BOOK);
        service.fetchBookById2(67);
        service.fetchBookById2(68);
        final InOrder singleInOrder = inOrder(dao);
        singleInOrder.verify(dao).fetchBookById2(67);
        singleInOrder.verify(dao).fetchBookById2(68);

        final InOrder multipleInOrder = inOrder(logger1, logger2);
        multipleInOrder.verify(logger1).log(anyString());
        multipleInOrder.verify(logger2).logToErr(anyString());

        final InOrder order = inOrder(dao, logger1, logger2);
        order.verify(dao).fetchBookById2(67);
        order.verify(dao).fetchBookById2(68);
        order.verify(logger1).log(anyString());
        order.verify(logger2).logToErr(anyString());

    }

    @Test
    void testNumberOfInvocations(){
        when(dao.fetchBookById2(anyInt())).thenReturn(LibraryService.DEFAULT_BOOK); //stubbing
        service.fetchBookById2(67);

        verify(dao).fetchBookById2(67);
        verify(dao, times(1)).fetchBookById2(67);
        verify(dao, atLeast(1)).fetchBookById2(67);
        verify(dao, atLeastOnce()).fetchBookById2(67);
        verify(dao, atMost(1)).fetchBookById2(67);
        verify(dao, atMostOnce()).fetchBookById2(67);

        verify(logger1, never()).logToErr(anyString());
        verify(logger1, times(2)).log(anyString());
        verify(logger1, times(2)).log(anyString());
        verify(logger1, atLeast(2)).log(anyString());
        verify(logger1, atMost(10)).log(anyString());

        verify(logger2, never()).log(anyString());
        verify(logger2, times(1)).logToErr(anyString());

        verifyNoMoreInteractions(dao);
        verifyNoMoreInteractions(logger1);
        verifyNoMoreInteractions(logger2);
    }

}