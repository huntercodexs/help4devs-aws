package com.huntercodexs.demo.lambda.config;

import com.huntercodexs.demo.lambda.model.BookModel;
import com.huntercodexs.demo.lambda.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Configuration
public class BookConfig {

    @Autowired
    BookRepository bookRepository;

    @Bean
    public Supplier<List<BookModel>> books() {
        return ()->bookRepository.create();
    }

    @Bean
    public Function<String, List<BookModel>> booksByName() {
        return (filter)->bookRepository.create()
                .stream()
                .filter(book -> book.getName().equals(filter))
                .collect(Collectors.toList());
    }

}
