package com.example.seconddemo.advice;

import com.example.seconddemo.dto.ErrorDTO;
import com.example.seconddemo.exception.CategoryNotFoundException;
import com.example.seconddemo.exception.InvalidArgumentException;
import com.example.seconddemo.exception.ProductNotFoundException;
import com.example.seconddemo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice

// in this class we are handling the exception thrown by the controller class and
// returning the error message and error code to the user in the form of JSON object

public class ControllerAdvice {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsg(ex.getMessage());
        errorDTO.setErrorCode("401");
        return ResponseEntity.badRequest().body(errorDTO);
    }
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ErrorDTO> handleInvalidArgumentException(InvalidArgumentException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsg(ex.getMessage());
        errorDTO.setErrorCode("402");
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsg(ex.getMessage());
        errorDTO.setErrorCode("403");
        return ResponseEntity.badRequest().body(errorDTO);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsg(ex.getMessage());
        errorDTO.setErrorCode("404");
        return ResponseEntity.badRequest().body(errorDTO);
    }


}
