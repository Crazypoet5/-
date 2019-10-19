package dao;

import domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao{
  List findAll() throws SQLException;

    List<Product> getProductByCid(String cid,  int currentPage, int size) throws SQLException;

    long getTotalCount(String cid) throws SQLException;
}

