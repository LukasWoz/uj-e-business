package com.example.shopbackend
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/")

class ApiController {
    @GetMapping("/products")
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Desk", 201.99),
            Product(2, "Carpet", 89.00)
        )
    }

    @PostMapping("/payments")
    fun createPayment(@RequestBody payment: Payment): String {
        return "Payment received"
    }
}

data class Product(val id: Int, val name: String, val price: Double)
data class Payment(val amount: Double, val method: String)
