package com.Valverde.facturacion.almacen.service;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Valverde.facturacion.almacen.entity.Permiso;
public interface PermisoService {
    public List<Permiso> findAll(Pageable page);
	public List<Permiso> finByNombre(String nombre,Pageable page);
	public Permiso findById(int id);
	public Permiso save(Permiso permiso);
	public void delete(int id);
}