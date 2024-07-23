package com.huntercodexs.demo.lambda.repository;

import com.huntercodexs.demo.lambda.model.BookModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class BookRepository {

    public List<BookModel> create() {
        return Stream.of(
                new BookModel(100, "Java 8 for all", 1990),
                new BookModel(101, "Java 17 for all", 2000),
                new BookModel(102, "Java 21 for all", 2018)
        ).collect(Collectors.toList());
    }

}
