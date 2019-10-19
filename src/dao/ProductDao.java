package dao;

import domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    List getNewProduct() throws SQLException;

     List getHostProduct() throws SQLException;

   Product getById(String id) throws SQLException;
}
