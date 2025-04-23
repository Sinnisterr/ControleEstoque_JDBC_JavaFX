package com.wbsistemas.controle_estoque_jdbc.model.dao;

import com.wbsistemas.controle_estoque_jdbc.model.Product;

import java.util.List;

public interface ProductDao {

    void insert(Product obj);
    void update(Product obj);
    void deleteByName(String name);
    void findByInitialLetter(char initialLetter);
    List<Product> findAll();


}
