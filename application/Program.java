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
        for(Product prod : list) {
            System.out.println(prod);
        }

        System.out.println("\n==== TEST 2: Products findNameParcial ====");
        list = productDao.findByName("sam", true);
        for(Product prod : list) {
            System.out.println(prod);
        }

        System.out.println("\n==== TEST 3: Products findByInitialLetter ====");
        productDao.findByInitialLetter('M');

        System.out.println("\n==== TEST 4: Deleted byName ====");
        productDao.deleteByName("Teclado Microsoft Wireless");
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
