package com.assessment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CONTRACT_SEQ")
    @SequenceGenerator(name="CONTRACT_SEQ", allocationSize = 1)
    @Column(name="contract_id")
    private Long contractId;
    @Column(name="tool_code")
    private String toolCode;
    @Column(name="tool_type")
    private String toolType;
    @Column(name="brand")
    private String brand;
    @Column(name="rental_days")
    private Integer rentalDays;
    @Column(name="checkout_date")
    private LocalDate checkoutDate;
    @Column(name="due_date")
    private LocalDate dueDate;
    @Column(name="daily_rental_charge")
    private Double dailyRentalCharge;
    @Column(name="charge_days")
    private Integer chargeDays;
    @Column(name="pre_discount_charge")
    private BigDecimal preDiscountCharge;
    @Column(name="discount_percent")
    private Integer discountPercent;
    @Column(name="discount_amount")
    private BigDecimal discountAmount;
    @Column(name="final_charge")
    private BigDecimal finalCharge;

}
