package th.mfu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    CustomerRepository customerRepo;

    @PostMapping("/customers/{customerId}/users")
    public ResponseEntity<String> createUser(@PathVariable Integer customerId, @RequestBody User user) {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if (customer == null) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }

        user.setCustomer(customer);
        userRepo.save(user);
        return new ResponseEntity<>("User created!", HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        if (!userRepo.existsById(id)) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        userRepo.deleteById(id);
        return new ResponseEntity<>("User deleted!", HttpStatus.NO_CONTENT);
    }
}
