package com.sgass.glassy.Service;

import com.sgass.glassy.Model.Product;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    void uploadFile(MultipartFile file, Product product) ;
}
