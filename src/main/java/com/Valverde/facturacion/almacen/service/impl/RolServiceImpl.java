package com.Valverde.facturacion.almacen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Valverde.facturacion.almacen.entity.Rol;
import com.Valverde.facturacion.almacen.repository.RolRepository;
import com.Valverde.facturacion.almacen.service.RolService;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository repository;

    @Override
    public List<Rol> findAll(Pageable page) {
        try {
            List<Rol> registros = repository.findAll(page).toList();
            return registros;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Rol> findByNombre(String nombre, Pageable page) {
        try {
            List<Rol> registros = repository.findByNombreContaining(nombre, page);
            return registros;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Rol findById(int id) {
        try {
            Rol registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Rol save(Rol rol) {
        try {
            if(rol.getId() == 0) {
                Rol nuevo = repository.save(rol);
                return nuevo;
            }
            Rol registro = repository.findById(rol.getId()).orElseThrow();
            registro.setNombre(rol.getNombre());
            registro.setPassword(rol.getPassword());
            repository.save(registro);
            return registro;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        try {
            Rol registro = repository.findById(id).orElseThrow();
            repository.delete(registro);
        } catch (Exception e) {
            throw e;
        }
    }
}
