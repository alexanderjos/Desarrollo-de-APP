package com.Valverde.facturacion.almacen.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Valverde.facturacion.almacen.entity.Categoria;

public interface CategoriaService {
	public List<Categoria> findAll(Pageable page);
	public List<Categoria> finByNombre(String nombre,Pageable page);
	public Categoria findById(int id);
	public Categoria save(Categoria categoria);
	public void delete(int id);
}
