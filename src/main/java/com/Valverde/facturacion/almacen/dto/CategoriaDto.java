package com.Valverde.facturacion.almacen.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDto {
    private int id;
    private String nombre;
    private String descripcion;
}
