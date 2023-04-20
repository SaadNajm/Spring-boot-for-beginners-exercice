package com.saadcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class Main {
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);

    }
    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
   record newCustomer(String name,String email,Integer age){}
   @PostMapping
    public void addCustomer(@RequestBody newCustomer request){
        Customer customer=new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);

   }
@DeleteMapping("{customerid}")
   public void deletebyId(@PathVariable("customerid") Integer id){
        customerRepository.deleteById(id);
   }
    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody Customer customer){
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAge(customer.getAge());
        customerRepository.save(existingCustomer);
    }


}
