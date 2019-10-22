package service;

import domain.Order;

import java.sql.SQLException;

public interface OrderService {

    void add(Order order) throws SQLException;
}
