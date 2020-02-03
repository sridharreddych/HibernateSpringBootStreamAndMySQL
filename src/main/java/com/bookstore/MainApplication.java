package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            bookstoreService.populateDatabase();

            System.out.println("\nStreaming: ");
            bookstoreService.streamDatabase();
        };
    }
}

/*
 * 
 * 
 * 
 * How To Stream Result Set Via Spring Data In MySQL
Note: For web-applications, pagination should be the way to go, not streaming. But, if you choose streaming then keep in mind the golden rule: keep th result set as small as posible. Also, keep in mind that the Execution Plan might not be as efficient as when using SQL-level pagination.

Description: This application is an example of streaming the result set via Spring Data and MySQL. This example can be adopted for databases that fetches the entire result set in a single roundtrip causing performance penalties.

Key points:

rely on forward-only result set (default in Spring Data)
rely on read-only statement (add @Transactional(readOnly=true))
set the fetch-size set (e.g. 30, or row-by-row; Integer.MIN_VALUE (recommended in MySQL))
 * 
 */
