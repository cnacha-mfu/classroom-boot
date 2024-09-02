package th.mfu;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    HashMap<Integer, Customer> db = new HashMap<Integer, Customer>();
    private int nextId = 1;
    @GetMapping("/customers")
    public ResponseEntity<Collection> getAllCustomers(){
        if(db.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Collection>(db.values(), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer cust){
        cust.setId(nextId);
        db.put(nextId, cust);
        nextId++;
        return new ResponseEntity<String>("Customer created!", HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id){
        db.remove(id);
        return new ResponseEntity<String>("Customer deleted!", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
        Customer cust = db.get(id);
        if( cust == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(cust, HttpStatus.OK);
    }
}
