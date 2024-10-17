package com.valverde.facturacion.almacen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valverde.facturacion.almacen.converter.ClienteConverter;
import com.valverde.facturacion.almacen.dto.ClienteDto;
import com.valverde.facturacion.almacen.entity.Cliente;
import com.valverde.facturacion.almacen.service.ClienteService;
import com.valverde.facturacion.almacen.util.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteConverter converter;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<ClienteDto> cliente = converter.fromEntities(service.findAll());
        return new WrapperResponse(true, "success", cliente).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> create (@RequestBody ClienteDto cliente) {
        Cliente entity = converter.fromDTO(cliente);
        ClienteDto dto = converter.fromEntity(service.save(entity));//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update (@PathVariable("id") int id, @RequestBody ClienteDto cliente) {
        Cliente entity = converter.fromDTO(cliente);
        ClienteDto dto = converter.fromEntity(service.save(entity));
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
    public ResponseEntity<ClienteDto> findById (@PathVariable("id") int id) {
        ClienteDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}
