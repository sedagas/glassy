package com.sgass.glassy.Repository;

import com.sgass.glassy.Model.PrescriptionData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionDataRepository extends JpaRepository<PrescriptionData, Long> {
//    List<Oculus> findAllByOrdersId(long id);
}
