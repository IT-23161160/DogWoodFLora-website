package com.dogWoodFlora.service;

import com.dogWoodFlora.dto.ProductDTO;
import com.dogWoodFlora.entity.ProductEntity;
import com.dogWoodFlora.mapper.ProductMapper;
import com.dogWoodFlora.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElse(null);
    }

    public List<ProductDTO> searchProducts(String text) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getProductName().toLowerCase()
                        .contains(text.toLowerCase()))
                .map(productMapper::toDTO)
                .toList();
    }

    public List<ProductDTO> getByCategory(String category) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().toLowerCase()
                        .contains(category.toLowerCase()))
                .map(productMapper::toDTO)
                .toList();
    }

    public Set<String> allCategories() {
        return productRepository.findAll()
                .stream()
                .map(ProductEntity::getCategory)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
