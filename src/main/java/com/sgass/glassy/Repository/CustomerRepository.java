package com.sgass.glassy.Repository;

import com.sgass.glassy.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
