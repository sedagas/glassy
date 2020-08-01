package com.sgass.glassy.Controller;

import com.sgass.glassy.Model.Customer;
import com.sgass.glassy.Model.Order;
import com.sgass.glassy.Model.PrescriptionData;
import com.sgass.glassy.Model.Product;
import com.sgass.glassy.Repository.CustomerRepository;
import com.sgass.glassy.Repository.OrderRepository;
import com.sgass.glassy.Repository.ProductRepository;
import com.sgass.glassy.Service.IOrderService;
import com.sgass.glassy.Util.GeneratePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IOrderService orderService;
    @Autowired
    ServletContext context;

    @RequestMapping(value = "/orders/{customerId}")
    public  String orders(@PathVariable Long customerId, Model model){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->new IllegalArgumentException("Invalid Customer id" + 1));
        model.addAttribute("orders", orderRepository.findAllByCustomerId(customerId));
        model.addAttribute("customer", customer);
        return "order/orders";
    }


    @RequestMapping(value="/newOrder/{id}")
    public String newOrder(@PathVariable long id, Model model){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Wrong customer id"+ id));
        model.addAttribute("customer", customer);
        List<Product> productList = (List<Product>) productRepository.findAll();
        model.addAttribute("products", productList);
        model.addAttribute("order", new Order());
        model.addAttribute("oculus", new PrescriptionData());

        return "order/newOrder";
    }

    @RequestMapping(value="/saveOrder/{customerId}", method = RequestMethod.POST)
    public String saveOrder(@Valid Order order, @PathVariable Long customerId, @Valid Product product, BindingResult result, Model model){
        Customer customer =customerRepository.findById(customerId).orElseThrow(()->new IllegalArgumentException("Wrong id"));
        order.setCustomer(customer);
        order.setProduct(productRepository.findById(product.getProductId()).orElseThrow(()-> new IllegalArgumentException("Wrong id")));
        orderRepository.save(order);

        return "redirect:/customer/customer";
    }

    @RequestMapping(value = "/deneme")
    public String deneme(){
        return "order/deneme";
    }

    @RequestMapping(value = "/pdfReport/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> ordersReport(@PathVariable long id){
        Order orders =  orderRepository.findById(id).
                orElseThrow(() ->new IllegalArgumentException("Invalid Customer id" + id));;

        ByteArrayInputStream bis = GeneratePdf.ordersReport(orders);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ordersreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @RequestMapping(value="/createPdf")
    public void createPdf(HttpServletRequest req, HttpServletResponse res){
        List<Order> orders = orderRepository.findAll();
        boolean isFlag = orderService.createPdf(orders,context,req,res);
        if(isFlag){
            String fullPath = req.getServletContext().getRealPath("/resources/reports/"+"orders"+".pdf");
            fileDownload(fullPath,res, "orders.pdf");
        }
    }

    private void fileDownload(String fullPath, HttpServletResponse res, String s) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if(file.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = context.getMimeType(fullPath);
                res.setContentType(mimeType);
                res.setHeader("content=disposition", "attachment; filename="+s);
                OutputStream outputStream = res.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while((bytesRead = inputStream.read(buffer))!=-1){
                    outputStream.write(buffer, 0 ,bytesRead);
                }
                inputStream.close();
                outputStream.close();
                file.delete();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    @RequestMapping(value="/newOrder", method = RequestMethod.GET)
//    public String newOrder(){
//        Order orders = new Order("My first order", "20.02.2001","100.0");
//        Customer customer = new Customer("Seda","Gas","15638");
//        Product product = new Product("rayban","sunglasses","blabla");
//        customerRepository.save(customer);
//        productRepository.save(product);
//
//        Prescription prescription = new Prescription("First prescription","20.02.2011", "100.0", GlassIndex.FIRST,GlassType.BLUE,Lens.BIO);
//        prescription.setCustomer(customer);
//        prescription.setProduct(product);
//        PrescriptionData prescriptionData =new PrescriptionData("1.5","1.5","1.5","1.5","1.5","1.5","1.5","1.5","454");
//        prescriptionData.setPrescription(prescription);
//        prescription.setPrescriptionData(prescriptionData);
//        prescriptionDataRepository.save(prescriptionData);
//        prescriptionRepository.save(prescription);
//
//
//        orders.setProduct(product);
//        orders.setCustomer(customer);
//
//        orderRepository.save(orders);
//        return "order/orders";
//    }
}
