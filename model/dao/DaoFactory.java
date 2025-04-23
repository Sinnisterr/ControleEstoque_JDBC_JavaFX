package com.wbsistemas.controle_estoque_jdbc.model.dao;

import com.wbsistemas.controle_estoque_jdbc.model.dao.impl.ProductDaoJDBC;

public class DaoFactory {

    public static ProductDao createProductDao() {
        return new ProductDaoJDBC();
    }

}
