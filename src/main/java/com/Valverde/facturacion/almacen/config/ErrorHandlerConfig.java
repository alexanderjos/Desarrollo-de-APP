package com.Valverde.facturacion.almacen.config;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Valverde.facturacion.almacen.exception.GeneralException;
import com.Valverde.facturacion.almacen.exception.NoDataFoundException;
import com.Valverde.facturacion.almacen.exception.ValidateException;
import com.Valverde.facturacion.almacen.util.WrapperResponse;

@ControllerAdvice
public class ErrorHandlerConfig  extends ResponseEntityExceptionHandler{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request){
        WrapperResponse<?> response = new WrapperResponse<>("Internal server error", false, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<?> validate(ValidateException e, WebRequest request){
        WrapperResponse<?> response = new WrapperResponse<>(e.getMessage(), false, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFound(NoDataFoundException e, WebRequest request){
        WrapperResponse<?> response = new WrapperResponse<>(e.getMessage(), false, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> general(GeneralException e, WebRequest request){
        WrapperResponse<?> response = new WrapperResponse<>("Insternal server error", false, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
