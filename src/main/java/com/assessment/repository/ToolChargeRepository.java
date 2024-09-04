package com.assessment.repository;

import com.assessment.entity.ToolCharges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToolChargeRepository extends JpaRepository<ToolCharges, Long> {

    public Optional<ToolCharges> findByToolType(String toolType);
}
