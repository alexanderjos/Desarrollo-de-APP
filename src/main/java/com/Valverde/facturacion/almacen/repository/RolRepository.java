package com.valverde.facturacion.almacen.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valverde.facturacion.almacen.entity.Rol;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    List<Rol> findByNombreContaining(String nombre, Pageable pageable);
}
