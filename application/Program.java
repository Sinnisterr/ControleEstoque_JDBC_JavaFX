package com.wbsistemas.controle_estoque_jdbc.application;

import com.wbsistemas.controle_estoque_jdbc.db.DbException;
import com.wbsistemas.controle_estoque_jdbc.model.entities.Product;
import com.wbsistemas.controle_estoque_jdbc.model.dao.DaoFactory;
import com.wbsistemas.controle_estoque_jdbc.model.dao.ProductDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDao productDao = DaoFactory.createProductDao();
        List<Product> list = new ArrayList<>();

        System.out.println("==== TEST 1: Products findAll ====");
        list = productDao.findAll();
        for (Product prod : list) {
            System.out.println(prod);
        }

        System.out.println("\n==== TEST 2: Products findNameParcial ====");
        list = productDao.findByName("sam", true);
        for (Product prod : list) {
            System.out.println(prod);
        }

        System.out.println("\n==== TEST 3: Products findByInitialLetter ====");
        productDao.findByInitialLetter('M');

        System.out.println("\n==== TEST 4: Deleted byName ====");
        productDao.deleteByName("Produto Exemplo");
        System.out.println("Done.");


        System.out.println("\n==== TEST 5: Update ====");
        Product updateProd = new Product();
        updateProd.setId(4);
        updateProd.setProduct("Novo Nome");
        updateProd.setPrice(99.99);
        updateProd.setQuantity(50);

        // Chamando o método update
        try {
            productDao.update(updateProd);
            System.out.println("Produto atualizado com sucesso!");
        } catch (DbException e) {
            System.err.println("Erro ao atualizar o produto: " + e.getMessage());
        }

        System.out.println("\n==== TEST 6: Insert ====");
        Product newProd = new Product();
        newProd.setProduct("Produto Exemplo");
        newProd.setPrice(19.99);
        newProd.setQuantity(100);

        // Chamando o método insert
        try {
            productDao.insert(newProd);
            System.out.println("Produto inserido com sucesso! ID gerado: " + newProd.getId());
        } catch (DbException e) {
            System.err.println("Erro ao inserir o produto: " + e.getMessage());

        }
    }
}


//        try {
//            Connection conn = DB.getConnection();
//            System.out.println("Conexão estabelecida com sucesso!");
//        } catch (DbException e) {
//            System.err.println("Erro ao conectar: " + e.getMessage());
//        } finally {
//            DB.closeConnection();
//        }
//    }
