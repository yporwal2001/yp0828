package com.assessment.entity;

// Importing required classes

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


// Class
@Table(name="tool")
public class Tool {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="tool_id")
        private Long toolId;
        @Column(name="tool_code", unique = true)
        private String toolCode;
        @Column(name="tool_type")
        private String toolType;
        @Column(name="brand")
        private String brand;
    }
