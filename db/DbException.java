package com.wbsistemas.controle_estoque_jdbc.db;

public class DbException extends RuntimeException{
    public DbException(String msg) {
        super(msg);
    }
}
