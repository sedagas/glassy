package com.sgass.glassy.Controller;

import com.sgass.glassy.Model.Customer;
import com.sgass.glassy.Model.Prescription;
import com.sgass.glassy.Model.PrescriptionData;
import com.sgass.glassy.Model.Product;
import com.sgass.glassy.Repository.CustomerRepository;
import com.sgass.glassy.Repository.PrescriptionDataRepository;
import com.sgass.glassy.Repository.PrescriptionRepository;
import com.sgass.glassy.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PrescriptionController {

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Autowired
    PrescriptionDataRepository prescriptionDataRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/newPrescription/{customerId}")
    public String prescription(@PathVariable long customerId, Model model){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->new IllegalArgumentException("Invalid Customer id" + customerId));
        List<Product> products = (List<Product>) productRepository.findAll();
        model.addAttribute("customer" , customer);
        model.addAttribute("products", products);
        model.addAttribute("prescriptionData", new PrescriptionData());
        model.addAttribute("prescription", new Prescription());
        return "order/prescription";
    }


    @RequestMapping(value = "/savePrescription/{customerId}", method = RequestMethod.POST)
    public String savePrescription(@Valid Prescription prescription,@Valid PrescriptionData data, @Valid Product product, @PathVariable long customerId, Model model){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->new IllegalArgumentException("Invalid Customer id" + customerId));
        data.setPrescription(prescription);
        prescription.setCustomer(customer);
        prescription.setProduct(productRepository.findById(product.getProductId()).orElseThrow(()-> new IllegalArgumentException("Wrong id")));
        prescription.setPrescriptionData(data);
        prescriptionDataRepository.save(data);
        prescriptionRepository.save(prescription);
        return"redirect:/order/orders";
    }


}
