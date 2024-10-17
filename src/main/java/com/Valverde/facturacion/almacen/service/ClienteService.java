package com.valverde.facturacion.almacen.service;

import org.springframework.data.domain.Pageable;

import com.valverde.facturacion.almacen.entity.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> findAll(Pageable page);
    public List<Cliente> findAll();
    public List<Cliente> findByNombre(String nombre, Pageable page);
    public Cliente findById(int id);
    public Cliente save(Cliente cliente);
    public void delete(int id);
}