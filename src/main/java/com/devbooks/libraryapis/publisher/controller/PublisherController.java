package com.devbooks.libraryapis.publisher.controller;

import com.devbooks.libraryapis.publisher.model.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/publishers")
public class PublisherController {

    @GetMapping(path = "/{publisherId}")
    public Publisher getPublisher(@PathVariable String publisherId) {
        return new Publisher(publisherId, "Prentice Hall Publishers", "phall@gmail.com", "801-938-3784");
    }
}
