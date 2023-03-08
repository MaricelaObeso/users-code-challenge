package main.java.interview;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> list() {
        return service.listAll().stream().filter(user -> user.getStatus().equals("ACTIVE")).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        try {
            User user = service.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public void add(@RequestBody User user){
        user.setStatus("ACTIVE");
        service.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable int id){
        try {
            User existingUser = service.get(id);
            if(user.getFirstName() !=null && !user.getFirstName().isEmpty()){
                existingUser.setFirstName(user.getFirstName());
            }
            if(user.getLastName() !=null && !user.getLastName().isEmpty()){
                existingUser.setLastName(user.getLastName());
            }
            if(user.getDateOfBirth()!=null){
                existingUser.setDateOfBirth(user.getDateOfBirth());
            }
            service.save(existingUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?>  delete(@PathVariable int id){
        try {
            User existingUser = service.get(id);
            existingUser.setStatus("INACTIVE");
            service.save(existingUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
