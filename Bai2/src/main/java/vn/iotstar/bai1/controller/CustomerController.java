package vn.iotstar.bai1.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.iotstar.bai1.model.Customer;

import java.util.List;

@RestController
@EnableMethodSecurity
public class CustomerController {
    final private List<Customer> customers = List.of(
            Customer.builder().id("001").name("Huỳnh Duy Nguyễn").email("nguyen@abc.com").build(),
            Customer.builder().id("002").name("Nguyễn Hữu Trung").email("trunghuu@gmail.com").build(),
            Customer.builder().id("003").name("Trần Thị Thu Hà").email("abc@gmail.com").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello Spring Boot");
    }

    @GetMapping("/customers/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        var customer = customers.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }
}
