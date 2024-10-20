package com.kevinzamora;

import com.kevinzamora.model.Customer;
import com.kevinzamora.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {

		// OPCIONAL: insertar damos demo

		// 1. Guardar el contexto de Spring en una variable
		// el contexto es como un contenedor de objetos de toda la aplicación
		var context = SpringApplication.run(Main.class, args);

		// 2. Con el metodo getBean() puedo obtener cualquier objeto de la aplicación
		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

		// Comprobar si existe algún customero:
		long numeroCustomeros = customerRepository.count();
		if (numeroCustomeros > 0)
			return;

		// Crear dos o tres customeros y guardarlos en la base de datos
		var custom1 = Customer.builder().nombre("Alan").apellido("Sastre").email("alan@certidevs.com").edad(30).build();
		var custom2 = Customer.builder().nombre("Kevin").apellido("Zamora").email("contactaconkev@gmail.com").build();
		customerRepository.save(custom1);
		customerRepository.save(custom2);
		

	}

}
