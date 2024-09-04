package com.assessment.service;

import com.assessment.entity.Tool;
import com.assessment.repository.ToolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolsService {

    @Autowired
    private ToolsRepository toolsRepository;
    public List<Tool> getTool(String toolCode) {

        return toolsRepository.findByToolCode(toolCode);
    }
}
