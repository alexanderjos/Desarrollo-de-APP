package com.Valverde.facturacion.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Valverde.facturacion.almacen.entity.Categoria;
import com.Valverde.facturacion.almacen.exception.GeneralException;
import com.Valverde.facturacion.almacen.exception.NoDataFoundException;
import com.Valverde.facturacion.almacen.exception.ValidateException;
import com.Valverde.facturacion.almacen.repository.CategoriaRepository;
import com.Valverde.facturacion.almacen.service.CategoriaService;
import com.Valverde.facturacion.almacen.validator.CategoriaValidator;


@Service
public class CategoriaServiceImpl implements CategoriaService{
	@Autowired
	private CategoriaRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll(Pageable page) {
		try {
			List<Categoria> registros=repository.findAll(page).toList();
			return registros;
		}  catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error en el servidor");
		}
			
	}

	
	@Transactional(readOnly = true)
	public List<Categoria> finByNombre(String nombre, Pageable page) {
		try {
			List<Categoria> registros=repository.findByNombreContaining(nombre,page);
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error en el servidor");
		}
	}

	@Transactional
	@Override
	public Categoria findById(int id) {
		try {
			Categoria registro=repository.findById(id)
					.orElseThrow(()-> new NoDataFoundException("No se encontro el registro con ese ID"));
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error en el servidor");
		}
	
	}

	@Transactional
	@Override
	public Categoria save(Categoria categoria) {
		try {
			//Nuevo registro
			CategoriaValidator.validate(categoria);
			if (categoria.getId()==0) {
				Categoria nuevo=repository.save(categoria);
				return nuevo;
			}
			//editar registro
			Categoria registro=repository.findById(categoria.getId()).orElseThrow(()-> new NoDataFoundException("No se encontro el registro"));;
			registro.setNombre(categoria.getNombre());
			registro.setDescripcion(categoria.getDescripcion());
			repository.save(registro);
			return registro;
		}  catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error en el servidor");
		}
	
	}

	@Override
	public void delete(int id) {
		try {
			Categoria registro=repository.findById(id).orElseThrow(()-> new NoDataFoundException("No se encontro el registro con ese ID"));;
			repository.delete(registro);
		}  catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error en el servidor");
		}
	
	}

}
