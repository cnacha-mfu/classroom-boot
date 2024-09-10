package th.mfu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @GetMapping("/customers")
    public ResponseEntity<Collection> getAllCustomers(){
        if(repo.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Collection>(repo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
        Optional<Customer> cust = repo.findById(id);
        if( !cust.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(cust.get(), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer cust){
        repo.save(cust);
        return new ResponseEntity<String>("Customer created!", HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable int id, @RequestBody Customer cust){
        if(!repo.findById(id).isPresent())
            return new ResponseEntity<String>("Customer not found!", HttpStatus.NOT_FOUND);
        cust.setId(id);
        repo.save(cust);
        return new ResponseEntity<String>("Customer updated!", HttpStatus.OK);
    }


    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id){
        repo.deleteById(id);
        return new ResponseEntity<String>("Customer deleted!", HttpStatus.NO_CONTENT);
    }

    
}
