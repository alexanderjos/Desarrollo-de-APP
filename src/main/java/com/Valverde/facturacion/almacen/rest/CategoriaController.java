package com.Valverde.facturacion.almacen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Valverde.facturacion.almacen.converter.CategoriaConverter;
import com.Valverde.facturacion.almacen.dto.CategoriaDto;
import com.Valverde.facturacion.almacen.entity.Categoria;
import com.Valverde.facturacion.almacen.service.CategoriaService;
import com.Valverde.facturacion.almacen.util.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaConverter converter;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<CategoriaDto> categorias = converter.fromEntities(service.findAll());
//        return ResponseEntity.ok(categorias);
        return new WrapperResponse(true, "success", categorias).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> create (@RequestBody CategoriaDto categoria) {
        Categoria entity = converter.fromDTO(categoria);
        CategoriaDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update (@PathVariable("id") int id, @RequestBody CategoriaDto categoria) {
        Categoria entity = converter.fromDTO(categoria);
        CategoriaDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id) {
        service.delete(id);
//        return ResponseEntity.ok(null);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById (@PathVariable("id") int id) {
        CategoriaDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}
