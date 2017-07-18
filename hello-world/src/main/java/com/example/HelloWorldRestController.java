package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldRestController {
    @RequestMapping(value = "/{studentName}", method = RequestMethod.GET)
    public String greeting(@PathVariable String studentName) {
        return "Hello " + studentName;
    }
    @RequestMapping(value = "/hello/{studentId}", method = RequestMethod.GET)
    public String helloId(@PathVariable int studentId) {
        return "Hello" + studentId;
    }
}

