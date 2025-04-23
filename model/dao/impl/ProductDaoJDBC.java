package com.wbsistemas.controle_estoque_jdbc.model.dao.impl;

import com.wbsistemas.controle_estoque_jdbc.db.DB;
import com.wbsistemas.controle_estoque_jdbc.db.DbException;
import com.wbsistemas.controle_estoque_jdbc.model.entities.Product;
import com.wbsistemas.controle_estoque_jdbc.model.dao.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private Connection conn;

    public ProductDaoJDBC(Connection conn) {
        this.conn = conn;
    }

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

        List<Product> produtos = new ArrayList<>(); // Lista para armazenas os produtos
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM estoque.controlestoque");
            rs = st.executeQuery(); // executa e obtem os resultados

            // Itera pelos resultados
            while (rs.next()) {
                // Cria um novo objeto Product para cada linha
               Product prod = instantiate(rs);
               produtos.add(prod);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
        return produtos;
    }

    private Product instantiate(ResultSet rs) throws SQLException {
        Product prod = new Product();
        prod.setId(rs.getInt("Id"));
        prod.setProduct(rs.getString("Produto"));
        prod.setPrice(rs.getDouble("Preco"));
        prod.setDate(rs.getDate("Data_Cadastro"));
        prod.setQuantity(rs.getInt("Quantidade"));
        return prod;
    }
}
