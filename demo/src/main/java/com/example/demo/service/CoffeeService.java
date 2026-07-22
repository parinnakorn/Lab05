package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.demo.model.Coffee;

@Service
public class CoffeeService {

    private final List<Coffee> coffeeList = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public CoffeeService() {
        // ข้อมูลตัวอย่างเริ่มต้น
        coffeeList.add(new Coffee(idCounter.incrementAndGet(), "Espresso", 45.0));
        coffeeList.add(new Coffee(idCounter.incrementAndGet(), "Latte", 55.0));
    }

    public List<Coffee> getAll() {
        return coffeeList;
    }

    public Coffee getById(Integer id) {
        return coffeeList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Coffee add(Coffee coffee) {
        coffee.setId(idCounter.incrementAndGet());
        coffeeList.add(coffee);
        return coffee;
    }

    public Coffee update(Integer id, Coffee updated) {
        Coffee existing = getById(id);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
        }
        return existing;
    }

    public boolean delete(Integer id) {
        return coffeeList.removeIf(c -> c.getId().equals(id));
    }
}