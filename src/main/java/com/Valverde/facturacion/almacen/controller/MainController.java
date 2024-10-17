package com.valverde.facturacion.almacen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/categorias")
    public String categorias() {
        return "categorias";
    }
    @GetMapping("/clientes")
    public String clientes() {
        return "clientes";
    }
}
