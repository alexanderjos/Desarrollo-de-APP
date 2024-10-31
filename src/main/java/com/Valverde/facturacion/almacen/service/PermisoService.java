package com.Valverde.facturacion.almacen.service;

import org.springframework.data.domain.Pageable;

import com.Valverde.facturacion.almacen.entity.Permiso;

import java.util.List;

public interface PermisoService {
    public List<Permiso> findAll(Pageable page);
    public List<Permiso> findByNombre(String nombre, Pageable page);
    public Permiso findById(int id);
    public Permiso save(Permiso permiso);
    public void delete(int id);
}
