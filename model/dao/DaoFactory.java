package com.wbsistemas.controle_estoque_jdbc.model.dao;

import com.wbsistemas.controle_estoque_jdbc.db.DB;
import com.wbsistemas.controle_estoque_jdbc.model.dao.impl.ProductDaoJDBC;

public class DaoFactory {

    public static ProductDaoJDBC createProductDao() {
        return new ProductDaoJDBC(DB.getConnection());
    }

}
