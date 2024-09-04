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
@Table(name="Tool_Charges")
public class ToolCharges {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tool_charge_id")
    private Long toolChargeId;
    @Column(name="tool_type")
    private String toolType;
    @Column(name="daily_charge")
    private Double dailyCharge;
    @Column(name="weekday_charge")
    private Boolean weekdayCharge;
    @Column(name="weekend_charge")
    private Boolean weekendCharge;
    @Column(name="holiday_charge")
    private Boolean holidayCharge;
}
