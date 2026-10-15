
package com.klu.ms;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(
            @Valid @RequestBody OrderRequest request) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Order created successfully");
        response.put("customerName", request.getCustomerName());
        response.put("productName", request.getProductName());
        response.put("quantity", request.getQuantity());
        response.put("price", request.getPrice());

        BigDecimal totalPrice = request.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        response.put("totalPrice", totalPrice);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
