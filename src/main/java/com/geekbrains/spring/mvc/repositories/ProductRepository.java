package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1l, "Apple", "Very sweet apple", "MuseCo", 10.0));
        this.products.add(new Product(2l, "Orange", "Very hard orange", "SSdCo", 15.0));
        this.products.add(new Product(3l, "Lemon", "Very bad lemon", "SSdCo", 12.0));
    }

    /**
     * Получение полного списка продуктов
     *
     * @return
     */
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    /**
     * Получение продукта по ID
     *
     * @param id
     * @return
     */
    public Product findById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new RuntimeException("Product not found");
    }

    /**
     * Обновление или добавление продукта
     *
     * @param product
     * @return
     */
    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(products.size() + 1L);
            products.add(product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Error save or update product");
    }
}