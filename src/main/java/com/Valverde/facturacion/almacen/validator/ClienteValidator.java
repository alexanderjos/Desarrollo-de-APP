package com.valverde.facturacion.almacen.validator;

import com.valverde.facturacion.almacen.entity.Cliente;
import com.valverde.facturacion.almacen.exception.ValidateException;

public class ClienteValidator {
    public static void save(Cliente registro) {
        if(registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }
        if(registro.getNombre().length() > 70) {
            throw new ValidateException("El nombre no debe exceder los 70 caracteres");
        }
        if(registro.getTipoDocumento() == null || registro.getTipoDocumento() == "" || registro.getTipoDocumento().trim().isEmpty()) {
            throw new ValidateException("El tipo de documento es requerido");
        }
        if(registro.getNumeroDocumento() == null || registro.getNumeroDocumento().trim().isEmpty()) {
            throw new ValidateException("El número de documento es requerido");
        }
        if (registro.getNumeroDocumento().length() > 15) {
            throw new ValidateException("El número de documento no debe exceder los 15 caracteres");
            
        }
        if (registro.getTelefono() == null || registro.getTelefono().trim().isEmpty()) {
            throw new ValidateException("El teléfono es requerido");
        }
        if (registro.getTelefono().length() > 15) {
            throw new ValidateException("El teléfono no debe exceder los 15 caracteres");
        }
        if (registro.getEmail() == null || registro.getEmail().trim().isEmpty()) {
            throw new ValidateException("El email es requerido");
        }
    }
}
