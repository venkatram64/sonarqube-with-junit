package com.venkat.service;

import com.venkat.model.Book;
import com.venkat.model.Topic;
import com.venkat.utils.Util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {
    public static Path DEFAULT_PATH = Paths.get("src/resources/books.csv");
    private List<Book> library;
    private final Util utils = new Util();

    public List<Book> getSortedComputingBooksByTitle(){
        return library.stream()
                .filter(b -> b.getTopic() == Topic.COMPUTING)
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public Map<Topic, List<Book>> booksByTopic(){
        return library.stream()
                .collect(Collectors.groupingBy(Book::getTopic));
    }

    public Map<Topic,Long> bookCountByTopic(){
        return library.stream()
                .collect(Collectors.groupingBy(Book::getTopic, Collectors.counting()));
    }

    public Optional<Topic> mostPopularTopic(){
        return library.stream()
                .collect(Collectors.groupingBy(Book::getTopic, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
