package com.wbsistemas.controle_estoque_jdbc.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    // Método que vai ler o arquivo properties.db

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("src\\db.properties")) {

            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    // Método para fechar conexao com banco

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
    }

    // Método para abrir conexão banco de dados jdbc

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    // Método para fechar Statment

    public static void closeStatment(Statement st) {
        if( st != null) {
            try {
                st.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    // Método para fechar Resultset

    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
