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

import com.Valverde.facturacion.almacen.converter.CategoriaConverter;
import com.Valverde.facturacion.almacen.dto.CategoriaDto;
import com.Valverde.facturacion.almacen.entity.Categoria;
import com.Valverde.facturacion.almacen.service.CategoriaService;
import com.Valverde.facturacion.almacen.util.WrapperResponse;

@RestController
@RequestMapping("/v1/categorias")
//localhost:8090/v1/categorias versionado en la URI
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@Autowired
	private CategoriaConverter converter;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDto>> findAll(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);
				
		List<CategoriaDto> categorias = converter.fromEntity(service.findAll(page));
		return new WrapperResponse("success",true,categorias).createResponse(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDto> create (@RequestBody CategoriaDto categoria){
		Categoria categoriaEntity=converter.fromDTO(categoria);
		CategoriaDto registro = converter.fromEntity(service.save(categoriaEntity));
		return new WrapperResponse("success",true,categoria).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> update(@PathVariable("id") int id,@RequestBody CategoriaDto categoria){
		Categoria categoriaEntity=converter.fromDTO(categoria);
		CategoriaDto registro = converter.fromEntity(service.save(categoriaEntity));
		return new WrapperResponse("success",true,categoria).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse("success",true,null).createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> findById(@PathVariable("id") int id){
		CategoriaDto registro=converter.fromEntity(service.findById(id));
		return new WrapperResponse("success",true,registro).createResponse(HttpStatus.OK);
	}
	
	
	
	
}
