package com.venkat.utils;

import com.venkat.model.Book;
import com.venkat.model.Topic;
import com.venkat.service.BookService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UtilTest {

    @Test
    void testParseLibraryFrom(){
        final Util util = mock(Util.class);
        List<Book> expect = Arrays.asList(new Book[]{new Book("Effective Java",300, Topic.COMPUTING, Year.of(2000), "Joshua Block")});
        when(util.parseLibraryFrom(any(Path.class))).thenReturn(expect); //stubbed
        //System.out.println(util.parseLibraryFrom(BookService.DEFAULT_PATH));
        MatcherAssert.assertThat(util.parseLibraryFrom(BookService.DEFAULT_PATH),
                CoreMatchers.is(CoreMatchers.equalTo(expect)));
    }

    @Test
    void testGetBookException(){
        final Util util = mock(Util.class);
        when(util.getBook(anyString())).thenThrow(RuntimeException.class); //stubbed
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            util.getBook("");
        });
        MatcherAssert.assertThat(exception.getClass(),
                CoreMatchers.is(CoreMatchers.equalTo(RuntimeException.class)));
    }
}