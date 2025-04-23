package com.wbsistemas.controle_estoque_jdbc.model.dao.impl;

import com.wbsistemas.controle_estoque_jdbc.model.Product;
import com.wbsistemas.controle_estoque_jdbc.model.dao.ProductDao;

import java.util.List;

public class ProductDaoJDBC implements ProductDao {
    @Override
    public void insert(Product obj) {

    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void findByInitialLetter(char initialLetter) {

    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }
}
