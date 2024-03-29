package com.example.mintobackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CannotFindElementException extends RuntimeException{
    String errorMessage;
}