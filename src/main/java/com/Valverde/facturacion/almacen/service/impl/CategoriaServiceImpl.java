package com.valverde.facturacion.almacen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valverde.facturacion.almacen.entity.Categoria;
import com.valverde.facturacion.almacen.exception.GeneralException;
import com.valverde.facturacion.almacen.exception.NoDataFoundException;
import com.valverde.facturacion.almacen.exception.ValidateException;
import com.valverde.facturacion.almacen.repository.CategoriaRepository;
import com.valverde.facturacion.almacen.service.CategoriaService;
import com.valverde.facturacion.almacen.validator.CategoriaValidator;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll(Pageable page) {
        try {
            List<Categoria> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findByNombre(String nombre, Pageable page) {
        try {
            List<Categoria> registros = repository.findByNombreContaining(nombre, page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findById(int id) {
        try {
            Categoria registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Categoria save(Categoria categoria) {
        try {
            CategoriaValidator.save(categoria);

            if(categoria.getId() == 0) {
                Categoria nuevo = repository.save(categoria);
                return nuevo;
            }

            Categoria registro = repository.findById(categoria.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            registro.setNombre(categoria.getNombre());
            registro.setDescripcion(categoria.getDescripcion());
            repository.save(registro);

            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        try {
            Categoria registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }
}
