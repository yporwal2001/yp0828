package com.assessment.controller;

import com.assessment.service.CheckoutService;
import com.assessment.dto.CheckOutInputDTO;
import com.assessment.dto.CheckoutOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
public class CheckoutController {

    @Autowired
    private CheckoutService service;

    @PostMapping("/RentalContracts")
    public ResponseEntity<CheckoutOutputDTO> createContract(@Validated @RequestBody CheckOutInputDTO request) {

        if (request.getRentalDayCount() < 1) {
            return ResponseEntity.badRequest().build();
        }
        if (request.getDiscountPercent() < 0 || request.getDiscountPercent() > 100) {
            return ResponseEntity.badRequest().build();
        }
        var checkoutOutputDto = service.checkOut(request);
        if (checkoutOutputDto!= null) {
            print(checkoutOutputDto);
        }
        return ResponseEntity.ok().body(checkoutOutputDto);
    }

    private void print(CheckoutOutputDTO checkoutOutputDTO) {
        System.out.println("Tool code: "+checkoutOutputDTO.getToolCode()+"\n"+
                "Tool type: "+checkoutOutputDTO.getToolType()+"\n"+
                "Tool brand: "+checkoutOutputDTO.getBrand()+"\n"+
                "Rental days: "+checkoutOutputDTO.getRentalDays()+"\n"+
                "Check out date: "+checkoutOutputDTO.getCheckoutDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))+"\n"+
                "Due date: "+checkoutOutputDTO.getDueDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))+"\n"+
                "Daily rental charge: $"+checkoutOutputDTO.getDailyRentalCharge()+"\n"+
                "Charge days: "+checkoutOutputDTO.getChargeDays()+"\n"+
                "Pre-discount charge: $"+checkoutOutputDTO.getPreDiscountCharge()+"\n"+
                "Discount percent: "+checkoutOutputDTO.getDiscountPercent()+"%"+"\n"+
                "Discount amount: $"+checkoutOutputDTO.getDiscountAmount()+"\n"+
                "Final charge: $"+checkoutOutputDTO.getFinalCharge());
    }

}
