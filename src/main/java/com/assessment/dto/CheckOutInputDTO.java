package com.assessment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutInputDTO {
    private String toolCode;
    private Integer rentalDayCount;
    private Integer discountPercent;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate checkoutDate;
}
