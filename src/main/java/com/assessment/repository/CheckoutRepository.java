package com.assessment.repository;

import com.assessment.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Contract, Long> {

}