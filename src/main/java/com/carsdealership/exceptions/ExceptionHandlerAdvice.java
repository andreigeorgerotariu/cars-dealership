package com.carsdealership.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private final ObjectMapper objectMapper;

    @Autowired
    public ExceptionHandlerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> customerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return getExceptionResponse(customerNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Object> carNotFoundException(CarNotFoundException carNotFoundException) {
        return getExceptionResponse(carNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<Object> purchaseNotFoundException(PurchaseNotFoundException purchaseNotFoundException) {
        return getExceptionResponse(purchaseNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<ObjectError> globalErrors = bindingResult.getGlobalErrors();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<String> errors = new ArrayList<>();

        for (ObjectError error : globalErrors) {
            errors.add(error.getDefaultMessage());
        }

        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("errors", errors);

        return new ResponseEntity<>(objectToString(response), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getExceptionResponse(RuntimeException runtimeException, HttpStatus httpStatus) {
        Map<String, Object> result = new HashMap<>();
        result.put("Message: ", runtimeException.getMessage());
        return new ResponseEntity<>(objectToString(result), httpStatus);
    }

    private String objectToString(Object response) {
        try {
            objectMapper.findAndRegisterModules();
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.info("Json processing exception");
        }
        return null;
    }
}