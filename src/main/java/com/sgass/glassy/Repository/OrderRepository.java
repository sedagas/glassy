package com.sgass.glassy.Repository;

import com.sgass.glassy.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    Li.st<Orders> findAllByOrdersDate(String date);
//    List<Orders> findByCustomer(Customer customer, Long id);
    List<Order> findAllByCustomerId(Long id);
//    List<Orders> findProductIdByOrderId(Long id);
}
