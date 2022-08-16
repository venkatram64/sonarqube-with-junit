package com.venkat.service;

import com.venkat.model.Topic;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LibraryServiceTest {

    @Test
    void testHasBookWithId(){
        final LibraryService.DAO dao = mock(LibraryService.DAO.class);
        final LibraryService service = new LibraryService(dao); //system under test (SUT)

        service.hasBookWithId(67);
        //above method calls the dao.fetchBookById(...) method
        verify(dao).fetchBookById(anyInt());  //verification

        //verify(dao).fetchBookByTitle(anyString()); //fails, b/c I did not invoke
    }

    @Test
    void testHasBookWithIdUsingLambdas(){
        final LibraryService.DAO dao = mock(LibraryService.DAO.class);
        final LibraryService service = new LibraryService(dao); //system under test (SUT)

        service.hasBookWithId2(67);
        verify(dao).fetchBookById2(argThat(argument -> argument.equals(67)));//lambda argument matcher
    }

    @Test
    void testHasBookWithIdUsingLogicalOp(){
        final LibraryService.DAO dao = mock(LibraryService.DAO.class);
        final LibraryService service = new LibraryService(dao); //system under test (SUT)

        service.hasBookWithId2(67);
        verify(dao).fetchBookById2(eq(67));// built in argument matcher
    }

    @Test
    void testArgMatcherWithNull(){
        final LibraryService service = mock(LibraryService.class);
        //here anyInt() does not work
        when(service.hasBookWithId2(isNull())).thenThrow(IllegalArgumentException.class); //any()    stubbing

        final IllegalArgumentException exception =
                assertThrows(new IllegalArgumentException("id must be a positive integer").getClass(),() ->{
                    service.hasBookWithId2(null);
                });
        MatcherAssert.assertThat(exception.getClass(), CoreMatchers.is(CoreMatchers.equalTo(IllegalArgumentException.class)));
    }

    @Test
    void testArgMatcherAllArgs(){
        final String author = "Joshua Block";
        final LibraryService service = mock(LibraryService.class);
        //below line does not work
        //when(service.hasBookWithTopicAndAuthor(any(Topic.class), author)).thenReturn(true); //is the error
        when(service.hasBookWithTopicAndAuthor(any(Topic.class), eq(author))).thenReturn(true); //stubbing
        assertTrue(service.hasBookWithTopicAndAuthor(Topic.COMPUTING, author));
    }

    @Test
    void testFetchBookByTitle(){
        final LibraryService.DAO dao = mock(LibraryService.DAO.class);
        final LibraryService service = new LibraryService(dao); //system under test (SUT)

        service.hasBookWithTitle("The Core Java");
        //when above method is called, internally which calls the dao's fetchBookByTitle
        //so to verify below is the verification call
        verify(dao).fetchBookByTitle(anyString());
    }

    @Test
    void testMyCustomArgMatcher(){
        final LibraryService service = mock(LibraryService.class);
        service.hasBookWithId2(67);
        //verifying the argument with custom one
        verify(service).hasBookWithId2(argThat(new MyArgMatcher()));
    }

    @Test
    void testMyCustomArgMatcher2(){ //other way of above test
        final LibraryService service = mock(LibraryService.class);
        when(service.hasBookWithId2(argThat(new MyArgMatcher()))).thenReturn(true); //stubbing

        assertTrue(service.hasBookWithId2(67));
        assertFalse(service.hasBookWithId2(-67));
    }

    @Test
    void testArgumentCaptorTest(){
        final LibraryService service = mock(LibraryService.class);

        //capturing the argument
        final ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        service.hasBookWithId2(67);
        verify(service).hasBookWithId2(captor.capture()); //verification
        //when(service.hasBookWithId2(captor.capture())).thenReturn(true)

        MatcherAssert.assertThat(captor.getValue(), CoreMatchers.is(CoreMatchers.equalTo(67)));
    }

    public static class MyArgMatcher implements ArgumentMatcher<Integer>{
        Integer value;
        @Override
        public boolean matches(Integer value) {
            this.value = value;
            return value != null && value > 0;
        }

        @Override
        public String toString(){
            return String.format("%id must be a positive integer", value);
        }
    }

}