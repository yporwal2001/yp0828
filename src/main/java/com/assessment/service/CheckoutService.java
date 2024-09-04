package com.assessment.service;

import com.assessment.entity.Contract;
import com.assessment.entity.Tool;
import com.assessment.repository.CheckoutRepository;
import com.assessment.dto.CheckOutInputDTO;
import com.assessment.dto.CheckoutOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class CheckoutService {
    @Autowired private  HolidayService holidayService;
    @Autowired private  ToolsService toolsService;
    @Autowired private ToolChargesService toolChargesService;
    @Autowired private CheckoutRepository checkoutRepository;

    public CheckoutOutputDTO checkOut(CheckOutInputDTO input) {

        List<Tool> tools = toolsService.getTool(input.getToolCode().toUpperCase());
        if (!tools.isEmpty()) {
            var toolCharges = toolChargesService.getToolChargesByToolType(tools.get(0).getToolType());
            LocalDate datetoEvaluateChargeDays = input.getCheckoutDate();
            var weekDays = 0;
            var holidays = 0;
            var weekends = 0;
            var chargableDays = 0;
            BigDecimal preDiscountCharge;
            BigDecimal discountAmount;
            for (int i = 0; i<input.getRentalDayCount();i++) {
                var holidayDto = holidayService.findChargeDayFromDate(datetoEvaluateChargeDays);
                        if (weekDays > 0) {
                            weekDays += holidayDto.getWeekDay();
                        } else if (weekDays == 0 && holidayDto.getWeekDay() != -1) {
                            weekDays += holidayDto.getWeekDay();
                        }
                        holidays += holidayDto.getHoliday();
                        weekends += holidayDto.getWeekEnd();
                        datetoEvaluateChargeDays = datetoEvaluateChargeDays.plusDays(1);
                }
                if (toolCharges.getWeekendCharge()) {
                    chargableDays = weekDays + weekends;
                } else if (toolCharges.getHolidayCharge()) {
                    chargableDays = weekDays + holidays;
                } else {
                    chargableDays = weekDays;
                }
                preDiscountCharge = new BigDecimal(chargableDays * toolCharges.getDailyCharge())
                        .setScale(2, RoundingMode.HALF_UP);
                discountAmount = (preDiscountCharge
                        .multiply(new BigDecimal(input.getDiscountPercent())))
                        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_UP);

                var contract = Contract.builder()
                        .toolCode(tools.get(0).getToolCode())
                        .toolType(tools.get(0).getToolType())
                        .brand(tools.get(0).getBrand())
                        .chargeDays(chargableDays)
                        .checkoutDate(input.getCheckoutDate())
                        .dueDate(input.getCheckoutDate().plusDays(input.getRentalDayCount()))
                        .dailyRentalCharge(toolCharges.getDailyCharge())
                        .rentalDays(input.getRentalDayCount())
                        .discountPercent(input.getDiscountPercent())
                        .discountAmount(discountAmount)
                        .preDiscountCharge(preDiscountCharge)
                        .finalCharge(preDiscountCharge.subtract(discountAmount))
                        .build();
                checkoutRepository.save(contract);
                return covertContractRoOutput(contract);
            } else {
            return null;
        }
    }

    public CheckoutOutputDTO covertContractRoOutput(Contract contract) {
        return CheckoutOutputDTO
                .builder()
                .toolCode(contract.getToolCode())
                .toolType(contract.getToolType())
                .brand(contract.getBrand())
                .rentalDays(contract.getRentalDays())
                .checkoutDate(contract.getCheckoutDate())
                .dueDate(contract.getDueDate())
                .dailyRentalCharge(contract.getDailyRentalCharge())
                .chargeDays(contract.getChargeDays())
                .preDiscountCharge(contract.getPreDiscountCharge())
                .discountPercent(contract.getDiscountPercent())
                .discountAmount(contract.getDiscountAmount())
                .finalCharge(contract.getFinalCharge())
                .build();
    }
}
