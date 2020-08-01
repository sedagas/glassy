package com.sgass.glassy.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sgass.glassy.Model.Order;
import com.sgass.glassy.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;
//
//    @Override
//    public List<Order> findOrderById(long id) {
//        return orderRepository.findById(id);
//    }

    @Override
    public boolean createPdf(List<Order> orders, ServletContext context, HttpServletRequest req, HttpServletResponse res) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("prescription.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World",font);

        try {
            document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
        return false;
    }
}
