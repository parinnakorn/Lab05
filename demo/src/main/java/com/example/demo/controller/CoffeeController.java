package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Coffee;
import com.example.demo.service.CoffeeService;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<Coffee> getAll() {
        return coffeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getById(@PathVariable Integer id) {
        Coffee coffee = coffeeService.getById(id);
        if (coffee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coffee);
    }

    @PostMapping
    public ResponseEntity<Coffee> add(@RequestBody Coffee coffee) {
        Coffee created = coffeeService.add(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Integer id, @RequestBody Coffee coffee) {
        Coffee updated = coffeeService.update(id, coffee);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = coffeeService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}