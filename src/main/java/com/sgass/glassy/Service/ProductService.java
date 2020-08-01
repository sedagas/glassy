package com.sgass.glassy.Service;

import com.sgass.glassy.Model.Product;
import com.sgass.glassy.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService implements IProductService{

    @Autowired
    ProductRepository productRepository;
    
    @Override
    public void uploadFile(MultipartFile file, Product product) {

    }
}
