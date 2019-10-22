package dao_impl;

import dao.OrderDao;
import domain.Order;
import domain.OrderItem;
import org.apache.commons.dbutils.QueryRunner;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.Date;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void add(Order order) throws SQLException {
        QueryRunner qr=new QueryRunner ();
        String sql="insert into orders values(?,?,?,?,?,?,?,?)";
        qr.update(DataSourceUtils.getConnection (),sql, order.getOid(),order.getOrderTime(),order.getTotal(),order.getState(),
                order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());

    }

    @Override
    public void addItem(OrderItem oi) throws SQLException {

        QueryRunner qr=new QueryRunner (DataSourceUtils.getDataSource ());
        String sql="insert into orderitem values(?,?,?,?,?)";
        qr.update(DataSourceUtils.getConnection (),sql, oi.getItemid(),oi.getCount(),oi.getSubTotal (),oi.getProduct().getPid(),oi.getOrder().getOid());

    }
}
