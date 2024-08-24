package dev.midhin.productservice.Exceptions;

import dev.midhin.productservice.Dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ControllerAdviseException {
    @ExceptionHandler(NotFontException.class)
    private ResponseEntity<ExceptionDto> handleNotFondException(
            NotFontException notFontException
    ){
        ExceptionDto exceptionDto = new ExceptionDto (HttpStatus.NOT_FOUND, notFontException.getMessage ());
        return new ResponseEntity<> (exceptionDto,HttpStatus.NOT_FOUND);
    }

}

