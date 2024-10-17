package com.valverde.facturacion.almacen.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valverde.facturacion.almacen.entity.Permiso;

import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    List<Permiso> findByNombreContaining(String nombre, Pageable pageable);
}
