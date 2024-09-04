package com.assessment.repository;

import com.assessment.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolsRepository extends JpaRepository<Tool, Long> {

    public List<Tool> findByToolCode(String toolCode);

}
