package com.wbsistemas.controle_estoque_jdbc.model.dao.impl;

import com.wbsistemas.controle_estoque_jdbc.db.DB;
import com.wbsistemas.controle_estoque_jdbc.db.DbException;
import com.wbsistemas.controle_estoque_jdbc.model.entities.Product;
import com.wbsistemas.controle_estoque_jdbc.model.dao.ProductDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private final Connection conn;

    public ProductDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Product obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "INSERT INTO estoque.controlestoque (Produto, Preco, Quantidade) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS // Para obter a chave gerada, se necessário
            );

            st.setString(1, obj.getProduct());
            st.setDouble(2, obj.getPrice());
            st.setInt(3, obj.getQuantity());

            // Executando a inserção
            int rowsAffected = st.executeUpdate();

            // Verificando se a inserção foi bem sucedida

            if(rowsAffected > 0) {
                // se precisar da chave gerada pode obtela assim
                rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1); // Obtem a chave gerada
                    obj.setId(id); // Definindo o Id no objeto Product
                }
            } else {
                throw new DbException("Erro ao inserir o produto, nenhuma linha afetada.");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public void update(Product obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(

                    "UPDATE estoque.controlestoque SET Produto = ?, Preco = ?, Quantidade = ? WHERE Id = ? ");

            st.setString(1,obj.getProduct());
            st.setDouble(2, obj.getPrice());
            st.setInt(3, obj.getQuantity());
            st.setInt(4, obj.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
               throw new DbException("No rows affected, Product ID: " + obj.getId());
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void deleteByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;

        // Verifica se o nome é nulo ou vazio
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio.");
        }

        try {

            //Verifica se o produto existe
            st = conn.prepareStatement(
                    "SELECT COUNT(*) FROM estoque.controlestoque WHERE Produto = ? ");
            st.setString(1, name);
            rs = st.executeQuery();

            if(rs.next() && rs.getInt(1) == 0) {
                System.out.println("Produto não encontrado: " + name);
            }

            // Deleta Produto
            st = conn.prepareStatement(
                    "DELETE FROM estoque.controlestoque WHERE Produto = ? ");
            st.setString(1, name);

            int rowsAffected = st.executeUpdate(); // executa a exclusao e obtem o numero de linhas

            if (rowsAffected > 0) {
                    System.out.println("Produto deletado com sucesso: " + name);
            } else {
                System.out.println("Nenhuma linha afetada! O produto pode não existir.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao tentar deletar o produto: " + e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public void findByInitialLetter(char initialLetter) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM estoque.controlestoque WHERE Produto LIKE ? ");

            st.setString(1, initialLetter + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Product prod = instantiate(rs);
                System.out.println(prod);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

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

    @Override
    public List<Product> findByName(String name, boolean parcial) {
        List<Product> prod = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // Ajusta a consulta com base no parâmetro 'parcial'
            if (parcial) {
                st = conn.prepareStatement("SELECT * FROM estoque.controlestoque WHERE Produto LIKE ?");
                st.setString(1, "%" + name + "%"); // Busca parcial
            } else {
                st = conn.prepareStatement("SELECT * FROM estoque.controlestoque WHERE Produto = ?");
                st.setString(1, name); // Busca exata
            }

            rs = st.executeQuery(); // Executa a consulta

            // Itera pelos resultados
            while (rs.next()) {
                Product produto = instantiate(rs); // Instancia um novo produto
                prod.add(produto); // Adiciona o produto à lista
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }

        return prod; // Retorna a lista de produtos (pode ser vazia)
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
