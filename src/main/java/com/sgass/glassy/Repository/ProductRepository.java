package com.sgass.glassy.Repository;

import com.sgass.glassy.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
