package com.kevinzamora.controller;

import com.kevinzamora.model.Customer;
import com.kevinzamora.repository.CustomerRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

public class CustomerController {

    private CustomerRepository customerRepository;


    // http://localhost:8080/customers
    @GetMapping("customers")
    public String findAll(Model model) {
        model.addAttribute("titulo", "Lista de customers");
        model.addAttribute("customers", customerRepository.findAll());
        return "customer-list"; // vista
    }

    // http://localhost:8080/customers/1
    @GetMapping("customers/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        customerOptional.ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer-detail";
    }

    // http://localhost:8080/customers/crear  Obtener el formulario vacío para poder crear un customero desde cero
    @GetMapping("customers/crear")
    public String obtenerFormularioParaNuevoCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    // http://localhost:8080/customers/editar/1  Obtener el formulario relleno
    @GetMapping("customers/editar/{id}")
    public String obtenerFormularioParaEditarCustomero(Model model, @PathVariable Long id) {
        customerRepository.findById(id)
                .ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer-form";
    }

    // POST /customers  Guardar el customero
    @PostMapping("customers")
    public String guardarCustomer(@ModelAttribute Customer customer) {
        boolean exists = false;
        if (customer.getId() != null) {
            exists = customerRepository.existsById(customer.getId());
        }
        if (! exists) {
            // Crear un nuevo customer
            customerRepository.save(customer);
        } else {
            // Actualizar un customer existente
            customerRepository.findById(customer.getId()).ifPresent(customeroDB -> {
                customeroDB.setNombre(customer.getNombre());
                customeroDB.setApellido(customer.getApellido());
                customeroDB.setEdad(customer.getEdad());
                customeroDB.setEmail(customer.getEmail());
                customerRepository.save(customeroDB);
            });
        }

        return "redirect:/customers";
    }

    // METODO BORRAR
    // http://localhost:8080/customer/borrar/1
    // http://localhost:8080/customer/borrar/2
    @GetMapping("customers/borrar/{id}")
    public String borrarCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }

}
