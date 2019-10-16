package service_impl;

import dao.ProductDao;
import dao_impl.ProductDaoImpl;
import domain.Product;
import service.ProductService;

import java.beans.PropertyDescriptor;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List getNewProduct() throws SQLException {
        ProductDao pd= new ProductDaoImpl ();
        List <Product>list  = pd.getNewProduct ();
        return list;
    }

    @Override
    public List getHostProduct() throws SQLException {

        ProductDao pd=new ProductDaoImpl();

        List hostProduct = pd.getHostProduct ();

        return hostProduct;
    }
}
