package com.Valverde.facturacion.almacen.validator;

import com.Valverde.facturacion.almacen.entity.Permiso;
import com.Valverde.facturacion.almacen.exception.ValidateException;

public class PermisoValidator {
    public static void validate(Permiso registro) {
        if (registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre del permiso es requerido");
        }
        if (registro.getNombre().length()>70) {
            throw new ValidateException("El nombre del permiso no debe de tener mas de 70 caracteres");
            
        }
    }
}
