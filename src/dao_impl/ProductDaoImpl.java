package dao_impl;

import dao.ProductDao;
import domain.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl  implements ProductDao {
    @Override
    public List getNewProduct() throws SQLException {

        QueryRunner qr=new QueryRunner (DataSourceUtils.getDataSource ());

        String sql="select * from product order by pdate limit 9";

        return qr.query ( sql,new BeanListHandler<> (Product.class));
    }

    @Override
    public List getHostProduct() throws SQLException {

        QueryRunner qr=new QueryRunner (DataSourceUtils.getDataSource ());

        String sql="select * from product where is_hot=1 limit 9";

        return qr.query(sql,new BeanListHandler<> (Product.class));
    }
}
