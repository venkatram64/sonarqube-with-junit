package com.venkat.service;

import com.venkat.model.Topic;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceWithAnnotationTest {

    @Mock
    private LibraryService service;

    @Mock
    private LibraryService.DAO dao;

    /*@BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }*/


    @Test
    void testHasBookWithId(){
        final LibraryService service2 = new LibraryService(dao); //system under test (SUT)

        service2.hasBookWithId(67);
        verify(dao).fetchBookById(anyInt());

        //verify(dao).fetchBookByTitle(anyString()); //fails, b/c I did not invoke
    }

    @Test
    void testHasBookWithIdUsingLambdas(){
        final LibraryService service2 = new LibraryService(dao); //system under test (SUT)

        service2.hasBookWithId2(67);
        verify(dao).fetchBookById2(argThat(argument -> argument.equals(67)));//argument matcher
    }

    @Test
    void testHasBookWithIdUsingLogicalOp(){
        final LibraryService service2 = new LibraryService(dao); //system under test (SUT)

        service2.hasBookWithId2(67);
        verify(dao).fetchBookById2(eq(67));//argument matcher
    }

    @Test
    void testArgMatcherWithNull(){
        when(service.hasBookWithId2(isNull())).thenThrow(IllegalArgumentException.class); //any()

        final IllegalArgumentException exception =
                assertThrows(new IllegalArgumentException("id must be a positive integer").getClass(),() ->{
                    service.hasBookWithId2(null);
                });
        MatcherAssert.assertThat(exception.getClass(), CoreMatchers.is(CoreMatchers.equalTo(IllegalArgumentException.class)));
    }

    @Test
    void testArgMatcherAllArgs(){
        final String author = "Joshua Block";

        //when(service.hasBookWithTopicAndAuthor(any(Topic.class), author)).thenReturn(true);
        when(service.hasBookWithTopicAndAuthor(any(Topic.class), eq(author))).thenReturn(true);
        assertTrue(service.hasBookWithTopicAndAuthor(Topic.COMPUTING, author));
    }

    @Test
    void testFetchBookByTitle(){
        final LibraryService service2 = new LibraryService(dao); //system under test (SUT)

        service2.hasBookWithTitle("The Core Java");
        //when above method is called, internally which calls the dao's fetchBookByTitle
        //so to verify below is the verification call
        verify(dao).fetchBookByTitle(anyString());
    }

    @Test
    void testMyCustomArgMatcher(){
        service.hasBookWithId2(67);
        verify(service).hasBookWithId2(argThat(new MyArgMatcher()));
    }

    @Test
    void testMyCustomArgMatcher2(){
        when(service.hasBookWithId2(argThat(new MyArgMatcher()))).thenReturn(true);

        assertTrue(service.hasBookWithId2(67));
        assertFalse(service.hasBookWithId2(-67));
    }

    @Test
    void testArgumentCaptorTest(){

        final ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        service.hasBookWithId2(67);
        verify(service).hasBookWithId2(captor.capture());

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