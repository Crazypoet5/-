package service_impl;


import dao.OrderDao;
import dao_impl.OrderDaoImpl;
import domain.Order;
import domain.OrderItem;
import service.OrderService;
import utils.DataSourceUtils;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    @Override
    public void add(Order order) throws SQLException {
        try {
            DataSourceUtils.startTransaction ();
            OrderDao orderDao = new OrderDaoImpl ();

            orderDao.add (order);
            for (OrderItem oi : order.getItemList ()) {
                orderDao.addItem (oi);
            }
           DataSourceUtils.commitAndClose ();
        }
        catch(Exception e){
               DataSourceUtils.rollbackAndClose ();
               e.printStackTrace ();
               throw e;
        }
    }
}
