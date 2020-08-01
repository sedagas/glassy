package com.sgass.glassy.Repository;

import com.sgass.glassy.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrderBaseRepository<T extends Order> extends JpaRepository<T, Long> {
}
