package com.Valverde.facturacion.almacen.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class WrapperResponse <T> {
    private String message;
    private boolean ok;
    private T body;
    public ResponseEntity<WrapperResponse<T>> createResponse(){
        return new ResponseEntity<>(this, HttpStatus.OK);
    }
    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status){
        return  new ResponseEntity<>(this, status);
    }
}
