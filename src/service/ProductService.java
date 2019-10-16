package service;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List getNewProduct() throws SQLException;
    List getHostProduct() throws SQLException;
}
