package com.bookstore.aop;

import com.bookstore.enums.ErrorCodeEnum;
import com.bookstore.exception.CustomRuntimeException;
import com.bookstore.domain.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@RestControllerAdvice
public class ExceptionAdviceController {

    public ResponseEntity<ErrorResponse> handleExceptionInterval(ErrorCodeEnum error) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(error.getCode())
                .message(error.getMessage()).build();

        return new ResponseEntity<>(errorResponse, error.getHttpStatus());
    }

    public ResponseEntity<ErrorResponse> handleExceptionInterval(ErrorResponse error) {
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(CustomRuntimeException ex, WebRequest request) {
        return this.handleExceptionInterval(ex.getErrorCode());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    protected ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException objException, Locale locale) {
        String errorMessage = objException.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(ErrorCodeEnum.FIELD_VALIDATION_ERROR.getMessage());

        String message = errorMessage.concat(" - ").concat(ErrorCodeEnum.FIELD_VALIDATION_ERROR.getMessage());

        return handleExceptionInterval(ErrorResponse.builder().code(-1).message(message).build());
    }
}
