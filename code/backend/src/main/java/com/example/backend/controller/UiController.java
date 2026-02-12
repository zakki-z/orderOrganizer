package com.example.backend.controller;

import com.example.backend.dto.OrderDTO;
import com.example.backend.dto.ProductDto;
import com.example.backend.entity.Product;
import com.example.backend.service.OrderService;
import com.example.backend.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ui")
public class UiController {

    private final ProductService productService;
    private final OrderService orderService;

    public UiController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }
    // Add this method to your existing UiController
    @GetMapping("/pos")
    public String showPos(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "pos"; // This corresponds to pos.html
    }
    // --- Product CRUD Pages ---

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }

    @GetMapping("/products/new")
    public String showCreateProductForm(Model model) {
        // Passing a null ID for a new product
        model.addAttribute("productDto", new ProductDto(null, "", null));
        return "product-form";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("productDto", productService.getProduct(id));
        return "product-form";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute ProductDto productDto) {
        if (productDto.id() != null) {
            productService.updateProduct(productDto);
        } else {
            productService.createProduct(productDto);
        }
        return "redirect:/ui/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/ui/products";
    }

    // --- Order Pages ---

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order-list";
    }

    @GetMapping("/orders/new")
    public String showOrderForm(Model model) {
        model.addAttribute("orderDto", new OrderDTO(null, new ArrayList<>(), null, null, null, null, "", null));
        model.addAttribute("allProducts", productService.getAllProducts());
        return "order-form";
    }

    @PostMapping("/orders")
    public String placeOrder(@ModelAttribute OrderDTO orderDto, @RequestParam(required = false) List<Long> productIds) {
        // Manually fetch products based on IDs selected in the form
        List<Product> selectedProducts = new ArrayList<>();
        if (productIds != null) {
            // Note: Efficient way would be findAllById in repo, but strict service usage requires loop here
            for (Long pid : productIds) {
                // We convert DTO back to Entity roughly or use the product service to get DTO and map manually
                // For simplicity in this demo, we assume the Service/Repo layer handles the object or we re-fetch:
                ProductDto pDto = productService.getProduct(pid);
                Product p = new Product();
                p.setId(pDto.id());
                p.setName(pDto.name());
                p.setPrice(pDto.price());
                selectedProducts.add(p);
            }
        }

        // Reconstruct DTO with selected products
        OrderDTO newOrder = new OrderDTO(
                orderDto.id(),
                selectedProducts,
                orderDto.quantity(),
                orderDto.totalPrice(),
                orderDto.paidAmount(),
                orderDto.remainingAmount(),
                orderDto.description(),
                orderDto.status()
        );

        orderService.createOrder(newOrder);
        return "redirect:/ui/orders";
    }
}