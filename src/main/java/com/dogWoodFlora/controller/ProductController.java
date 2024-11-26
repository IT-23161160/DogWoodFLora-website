package com.dogWoodFlora.controller;

import com.dogWoodFlora.dto.ProductDTO;
import com.dogWoodFlora.dto.ReviewDTO;
import com.dogWoodFlora.entity.ProductEntity;
import com.dogWoodFlora.service.ProductService;
import com.dogWoodFlora.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("product", new ProductEntity());
        return "show";
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "details";
        } else {
            return "redirect:/products";
        }
    }

    @GetMapping("/products/search/{searchText}")
    public String getProductsBySearchText(@PathVariable String searchText, Model model) {
        List<ProductDTO> result = productService.searchProducts(searchText);
        model.addAttribute("products", result);
        return "show";
    }

    @GetMapping("products/categories")
    public String getAllCategories(Model model) {
        Set<String> categories = productService.allCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/products/categories/{category}")
    public String getProductsByCategory(@PathVariable String category, Model model) {
        List<ProductDTO> products = productService.getByCategory(category);
        model.addAttribute("products", products);
        return "show";
    }
    /*
    @GetMapping
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "show";
    } */

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") ProductEntity product,
                              @RequestParam("productImageFile") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            product.setImageDataFromBase64(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        ProductDTO product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "show";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") ProductEntity product,
                                @RequestParam("productImageFile") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            product.setImageDataFromBase64(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        product.setProductId(id);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping("/products/{productId}/reviews")
    public String createReviewForProduct(@PathVariable Long productId, @ModelAttribute ReviewDTO reviewDTO, Model model) {
        reviewDTO.setProductId(productId);
        reviewService.createReview(reviewDTO);
        return "redirect:/products/" + productId;
    }

    @GetMapping("/products/{productId}/reviews")
    public String getReviewsForProduct(@PathVariable Long productId, Model model) {
        List<ReviewDTO> reviews = reviewService.getReviewsByProductId(productId);
        model.addAttribute("reviews", reviews);
        return "products/reviews";
    }
}