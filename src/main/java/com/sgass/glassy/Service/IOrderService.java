package com.sgass.glassy.Service;

import com.sgass.glassy.Model.Order;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IOrderService {

//    List<Order> findOrderById(long id);
    boolean createPdf(List<Order> orders, ServletContext context, HttpServletRequest req, HttpServletResponse res);
}
