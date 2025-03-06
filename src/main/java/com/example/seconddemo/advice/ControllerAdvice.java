package com.example.seconddemo.advice;

import com.example.seconddemo.DTO.ErrorDTO;
import com.example.seconddemo.exception.CategoryNotFoundException;
import com.example.seconddemo.exception.invalidArgumentException;
import com.example.seconddemo.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
@org.springframework.web.bind.annotation.ControllerAdvice

// in this class we are handling the exception thrown by the controller class and
// returning the error message and error code to the user in the form of JSON object
public class ControllerAdvice {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsg("Invalid input ID should not be greater than 200");
        errorDTO.setErrorCode("400");
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsg("Product not found");
        errorDTO.setErrorCode("401");
        return ResponseEntity.badRequest().body(errorDTO);
    }
    @ExceptionHandler(invalidArgumentException.class)
    public ResponseEntity<ErrorDTO> handleInvalidArgumentException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsg("Please provide all argument, nothing can be null");
        errorDTO.setErrorCode("402");
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCategoryNotFoundException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMsg("Category not found");
        errorDTO.setErrorCode("403");
        return ResponseEntity.badRequest().body(errorDTO);
    }


}
