package com.Valverde.facturacion.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Valverde.facturacion.almacen.entity.Permiso;
import com.Valverde.facturacion.almacen.exception.GeneralException;
import com.Valverde.facturacion.almacen.exception.NoDataFoundException;
import com.Valverde.facturacion.almacen.exception.ValidateException;
import com.Valverde.facturacion.almacen.repository.PermisoRepository;
import com.Valverde.facturacion.almacen.service.PermisoService;
import com.Valverde.facturacion.almacen.validator.PermisoValidator;


@Service
public class PermisoServiceImpl implements PermisoService{
	@Autowired
	private PermisoRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Permiso> findAll(Pageable page) {
		try {
			List<Permiso> registros=repository.findAll(page).toList();
			return registros;
		}  catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error en el servidor");
		}
			
	}

	
	@Transactional(readOnly = true)
	public List<Permiso> finByNombre(String nombre, Pageable page) {
		try {
			List<Permiso> registros=repository.findByNombreContaining(nombre,page);
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
	public Permiso findById(int id) {
		try {
			Permiso registro=repository.findById(id)
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
	public Permiso save(Permiso permiso) {
		try {
			//Nuevo registro
			PermisoValidator.validate(permiso);
			if (permiso.getId()==0) {
				Permiso nuevo=repository.save(permiso);
				return nuevo;
			}
			//editar registro
			Permiso registro=repository.findById(permiso.getId()).orElseThrow(()-> new NoDataFoundException("No se encontro el registro"));;
			registro.setNombre(permiso.getNombre());
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
			Permiso registro=repository.findById(id).orElseThrow(()-> new NoDataFoundException("No se encontro el registro con ese ID"));;
			repository.delete(registro);
		}  catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error en el servidor");
		}
	
	}

}
