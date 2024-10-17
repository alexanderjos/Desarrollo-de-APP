package com.valverde.facturacion.almacen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valverde.facturacion.almacen.converter.PermisoConverter;
import com.valverde.facturacion.almacen.dto.CategoriaDto;
import com.valverde.facturacion.almacen.dto.PermisoDto;
import com.valverde.facturacion.almacen.entity.Permiso;
import com.valverde.facturacion.almacen.service.PermisoService;
import com.valverde.facturacion.almacen.util.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/permisos")
public class PermisoController {
    @Autowired
    private PermisoService service;

    @Autowired
    private PermisoConverter converter;

    @GetMapping
    public ResponseEntity<List<PermisoDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<PermisoDto> permisos = converter.fromEntities(service.findAll(page));

        return new WrapperResponse(true, "success", permisos).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> create (@RequestBody PermisoDto permiso) {
        Permiso entity = converter.fromDTO(permiso);
        PermisoDto dto = converter.fromEntity(service.save(entity));

        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update (@PathVariable("id") int id, @RequestBody PermisoDto permiso) {
        Permiso entity = converter.fromDTO(permiso);
        PermisoDto dto = converter.fromEntity(service.save(entity));

        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id) {
        service.delete(id);

        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById (@PathVariable("id") int id) {
        PermisoDto dto = converter.fromEntity(service.findById(id));

        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}
