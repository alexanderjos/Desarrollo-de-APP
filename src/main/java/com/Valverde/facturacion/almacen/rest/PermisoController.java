package com.Valverde.facturacion.almacen.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Valverde.facturacion.almacen.converter.PermisoConverter;
import com.Valverde.facturacion.almacen.dto.PermisoDto;
import com.Valverde.facturacion.almacen.entity.Permiso;
import com.Valverde.facturacion.almacen.service.PermisoService;
import com.Valverde.facturacion.almacen.util.WrapperResponse;

@RestController
@RequestMapping("/v1/permisos")
//localhost:8090/v1/categorias versionado en la URI
public class PermisoController {
	@Autowired
	private PermisoService service;
	
	@Autowired
	private PermisoConverter converter;
	
	@GetMapping
	public ResponseEntity<List<PermisoDto>> findAll(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);
				
		List<PermisoDto> permiso = converter.fromEntity(service.findAll(page));
		return new WrapperResponse("success",true,permiso).createResponse(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PermisoDto> create (@RequestBody PermisoDto permiso){
		Permiso PermisoEntity=converter.fromDTO(permiso);
		PermisoDto registro = converter.fromEntity(service.save(PermisoEntity));
		return new WrapperResponse("success",true,permiso).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PermisoDto> update(@PathVariable("id") int id,@RequestBody PermisoDto permiso){
		Permiso PermisoEntity=converter.fromDTO(permiso);
		PermisoDto registro = converter.fromEntity(service.save(PermisoEntity));
		return new WrapperResponse("success",true,permiso).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse("success",true,null).createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PermisoDto> findById(@PathVariable("id") int id){
		PermisoDto registro=converter.fromEntity(service.findById(id));
		return new WrapperResponse("success",true,registro).createResponse(HttpStatus.OK);
	}
	
	
	
	
}
