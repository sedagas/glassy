package com.sgass.glassy.Controller;

import com.sgass.glassy.Model.Product;
import com.sgass.glassy.Model.ProductType;
import com.sgass.glassy.Repository.ProductRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    public  static String uploadDirectory = System.getProperty("user.dir") + "/uploads";


    @RequestMapping(value="/products")
    public String products(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "product/products";
    }

    @RequestMapping(value = "/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "product/newProduct";
    }

    @RequestMapping(value="/saveProduct", method = RequestMethod.POST)
    public String saveCustomer(@Valid Product product, BindingResult result,
                               @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if(result.hasErrors()){
            return "product/newProduct";
        }
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            String fileType = file.getContentType();
            long size = file.getSize();
            String fileSize = String.valueOf(size);
            String encoded = Base64.encodeBase64String(file.getBytes());
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

//        Product product = new Product();

            product.setProductName(product.getProductName());
            product.setProductType(ProductType.Watch);
            product.setFileName(fileName);
            product.setFileType(fileType);
            product.setProductPicPath(file.getBytes());

//        productService.save(product);
            productRepository.save(product);
        }
        model.addAttribute("products", productRepository.findAll());
        return "product/products";
    }

    @RequestMapping(value = "/editProduct/{id}")
    public String editCustomer(@PathVariable long id, Model model){
        Product product = productRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid Customer id" + id));
        model.addAttribute("product", product);
        return "product/editProduct";
    }

    @RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.POST)
    public String updateCustomer(@PathVariable long id, @Valid Product product,
                                 @RequestParam("file") MultipartFile file,Model model, BindingResult result){

        if(result.hasErrors()){
            product.setProductId(id);
            return "redirect:editProduct";
        }
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/products";
    }

    @RequestMapping(value = "/deleteProduct/{id}")
    public String deleteCustomer(@PathVariable long id, Model model){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer id : "+id));

        productRepository.delete(product);
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/products";

    }
}
