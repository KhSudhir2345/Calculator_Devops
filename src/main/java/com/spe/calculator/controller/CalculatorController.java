package com.spe.calculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CalculatorController {

    @GetMapping("/add")
    public ResponseEntity<?> add(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(Map.of("operation", "add", "a", a, "b", b, "result", a + b));
    }

    @GetMapping("/subtract")
    public ResponseEntity<?> subtract(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(Map.of("operation", "subtract", "a", a, "b", b, "result", a - b));
    }

    @GetMapping("/multiply")
    public ResponseEntity<?> multiply(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(Map.of("operation", "multiply", "a", a, "b", b, "result", a * b));
    }

    @GetMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Division by zero is not allowed"));
        }
        return ResponseEntity.ok(Map.of("operation", "divide", "a", a, "b", b, "result", a / b));
    }
}