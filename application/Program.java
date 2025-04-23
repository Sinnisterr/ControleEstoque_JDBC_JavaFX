package com.wbsistemas.controle_estoque_jdbc.application;

import com.wbsistemas.controle_estoque_jdbc.db.DB;
import com.wbsistemas.controle_estoque_jdbc.db.DbException;
import com.wbsistemas.controle_estoque_jdbc.model.Product;

import java.sql.Connection;
import java.util.Date;

public class Program {

    public static void main(String[] args) {

        Product prod = new Product(1, "TV", 33.00, new Date(), 3);

        System.out.println(prod);




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
}