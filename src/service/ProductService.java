package service;

import domain.PageBean;
import domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List getNewProduct() throws SQLException;
    List getHostProduct() throws SQLException;
    Product getById(String id) throws SQLException;


}
