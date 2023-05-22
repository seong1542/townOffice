package com.nhnacademy.townoffice.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonAdvice {
    @InitBinder
    void initBinder(WebDataBinder binder) {binder.initDirectFieldAccess();}
}
