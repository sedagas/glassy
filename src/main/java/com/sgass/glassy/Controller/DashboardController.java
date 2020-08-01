package com.sgass.glassy.Controller;

import com.sgass.glassy.Repository.CustomerRepository;
import com.sgass.glassy.Repository.OrderRepository;
import com.sgass.glassy.Repository.PrescriptionRepository;
import com.sgass.glassy.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/dashboard")
    public String dashboard(Model model){
        int totalCustomers = customerRepository.findAll().size();
        int totalOrders = orderRepository.findAll().size() + prescriptionRepository.findAll().size();
        int totalProducts = productRepository.findAll().size();
        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalProducts", totalProducts);
        return "dashboard";
    }
}
