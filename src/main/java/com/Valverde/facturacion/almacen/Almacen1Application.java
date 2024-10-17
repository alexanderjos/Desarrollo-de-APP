package com.Valverde.facturacion.almacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Almacen1Application {

	public static void main(String[] args) {
		SpringApplication.run(Almacen1Application.class, args);
	}

}
