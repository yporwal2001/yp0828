package com.assessment.service;

import com.assessment.entity.ToolCharges;
import com.assessment.repository.ToolChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolChargesService {
    @Autowired private ToolChargeRepository toolChargeRepository;

    public ToolCharges getToolChargesByToolType(String toolType) {
        return toolChargeRepository.findByToolType(toolType).orElse(null);
    }

    public ToolCharges saveToolCharges(ToolCharges toolCharges) {
        return toolChargeRepository.save(toolCharges);
    }
}
