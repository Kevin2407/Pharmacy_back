package com.kevin.pharmacyapi.pharmacyapi.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Role>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roleRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role newRole, UriComponentsBuilder builder) {
        Role savedRole = roleRepository.save(newRole);
        URI uri = builder
                .path("/role/{id}")
                .buildAndExpand(savedRole.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody Role roleAct) {
        return roleRepository.findById(id)
                .map(roleAnt -> {
                    roleAct.setId(roleAnt.getId());
                    roleRepository.save(roleAct);
                    return ResponseEntity.noContent().<Role>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return roleRepository.findById(id)
                .map(role -> {
                    roleRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}