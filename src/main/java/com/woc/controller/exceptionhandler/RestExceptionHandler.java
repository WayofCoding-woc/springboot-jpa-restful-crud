package com.woc.controller.exceptionhandler;

import com.woc.exception.AddressNotFoundException;
import com.woc.exception.EmployeeNotFoundException;
import com.woc.exception.PartialInputDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<?> addressNotFoundException(AddressNotFoundException e){
        logger.error("AddressNotFoundException occurred", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartialInputDataException.class)
    public ResponseEntity<?> partialInputDataException(PartialInputDataException e){
        logger.error("PartialInputDataException occurred, requestedData={}", e.getData(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException e){
        logger.error("IllegalArgumentException occurred", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> employeeNotFoundException(EmployeeNotFoundException e){
        logger.error("EmployeeNotFoundException occurred", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> otherRuntimeException(RuntimeException e){
        logger.error("RuntimeException occurred", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
