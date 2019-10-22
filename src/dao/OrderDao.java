package dao;

import domain.Order;
import domain.OrderItem;

import java.sql.SQLException;

public interface OrderDao {
    void add(Order order) throws SQLException;
    void addItem(OrderItem order) throws SQLException;
}
