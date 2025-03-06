package com.kevin.pharmacyapi.pharmacyapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User newUser, UriComponentsBuilder builder) {
        User savedUser = userRepository.save(newUser);
        URI uri = builder
                .path("/user/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userAct) {
        return userRepository.findById(id)
                .map(userAnt -> {
                    userAct.setId(userAnt.getId());
                    userRepository.save(userAct);
                    return ResponseEntity.noContent().<User>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}