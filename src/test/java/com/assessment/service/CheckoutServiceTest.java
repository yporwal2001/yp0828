package com.assessment.service;

import com.assessment.dto.CheckOutInputDTO;
import com.assessment.dto.HolidayDTO;
import com.assessment.entity.Tool;
import com.assessment.entity.ToolCharges;
import com.assessment.repository.CheckoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CheckoutServiceTest {

    @Mock
    ToolsService toolsService;
    @Mock
    ToolChargesService toolChargesService;
    @Mock
    HolidayService holidayService;
    @Mock
    CheckoutRepository checkoutRepository;
    @InjectMocks
    private CheckoutService checkoutService;

    private static final String JACK_HAMMER_RIDGID_BRAND = "JAKR";
    private static final String JACK_HAMMER_DEWALT_BRAND = "JAKD";
    private static final String CHAIN_SAW_STIHL_BRAND = "CHNS";
    private static final String LADDER_WERBER_BRAND = "LADW";
    private static final String JACK_HAMMER = "Jackhammer";
    private static final String LADDER = "Ladder";
    private static final String CHAIN_SAW = "Chainsaw";
    private static final String STIHL_BRAND = "Stihl";
    private static final String DEWALT_BRAND = "DeWalt";
    private static final String WERNER_BRAND = "Werner";
    private static final String RIDGID_BRAND = "Ridgid";


    @BeforeEach
    public void setup() {
        var holidayDtoWeekday = HolidayDTO
                .builder()
                .weekDay(1)
                .weekEnd(0)
                .holiday(0)
                .build();
        var holidayDtoWeekend = HolidayDTO
                .builder()
                .weekDay(0)
                .weekEnd(1)
                .holiday(0)
                .build();
        var holidayDtoHoliday = HolidayDTO
                .builder()
                .weekDay(0)
                .weekEnd(0)
                .holiday(1)
                .build();
        var holidayDtoPreviousDayHoliday = HolidayDTO
                .builder()
                .weekDay(-1)
                .weekEnd(1)
                .holiday(1)
                .build();
        var holidayDtoNextDayHoliday = HolidayDTO
                .builder()
                .weekDay(-1)
                .weekEnd(1)
                .holiday(1)
                .build();


        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2020,7,2)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2020,7,3)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2020,7,4)))).thenReturn(holidayDtoPreviousDayHoliday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2020,7,5)))).thenReturn(holidayDtoWeekend);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2020,7,6)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2020,7,7)))).thenReturn(holidayDtoWeekday);

        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,2)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,3)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,4)))).thenReturn(holidayDtoPreviousDayHoliday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,5)))).thenReturn(holidayDtoWeekend);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,6)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,7)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,8)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,9)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,7,10)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(LocalDate.of(2015,7,11))).thenReturn(holidayDtoWeekend);

        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,9,3)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,9,4)))).thenReturn(holidayDtoWeekday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,9,5)))).thenReturn(holidayDtoWeekend);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,9,6)))).thenReturn(holidayDtoWeekend);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,9,7)))).thenReturn(holidayDtoHoliday);
        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2015,9,8)))).thenReturn(holidayDtoWeekday);

        when(holidayService.findChargeDayFromDate(eq(LocalDate.of(2021,7,4)))).thenReturn(holidayDtoNextDayHoliday);


    }
    @Test
    public void testCheckoutJackHammerWithDiscountLaborDayFirstWeekNoMonday() {

        var input = CheckOutInputDTO.builder()
                .toolCode(JACK_HAMMER_RIDGID_BRAND)
                .checkoutDate(LocalDate.of(2015,9,3))
                .rentalDayCount(5)
                .discountPercent(100)
                .build();
        var tool = Tool.builder()
                .toolCode(JACK_HAMMER_RIDGID_BRAND)
                .toolType(JACK_HAMMER)
                .brand(RIDGID_BRAND)
                .build();
        when(toolsService.getTool(anyString())).thenReturn(Collections.singletonList(tool));

        var toolCharges = ToolCharges.builder()
                .toolType(JACK_HAMMER)
                .dailyCharge(2.99)
                .weekdayCharge(Boolean.TRUE)
                .weekendCharge(Boolean.FALSE)
                .holidayCharge(Boolean.FALSE)
                .build();
        when(toolChargesService.getToolChargesByToolType(anyString())).thenReturn(toolCharges);

        var checkoutDto = checkoutService.checkOut(input);
        assert checkoutDto.getChargeDays() == 2;
        assert checkoutDto.getDiscountAmount().equals(new BigDecimal("5.98"));
        assert checkoutDto.getDueDate().equals(LocalDate.of(2015,9,3)
                        .plusDays(input.getRentalDayCount()));
        assert checkoutDto.getPreDiscountCharge().equals(new BigDecimal("5.98"));
        assert checkoutDto.getFinalCharge().equals(new BigDecimal("0.00"));
    }

    @Test
    public void testCheckoutLadderWithDiscountJuly4thSaturday() {
        var input = CheckOutInputDTO.builder()
                .toolCode(LADDER_WERBER_BRAND)
                .checkoutDate(LocalDate.of(2020,7,2))
                .rentalDayCount(3)
                .discountPercent(10)
                .build();

        var tool = Tool.builder()
                .toolCode(LADDER_WERBER_BRAND)
                .toolType(LADDER)
                .brand(WERNER_BRAND)
                .build();
        when(toolsService.getTool(anyString())).thenReturn(Collections.singletonList(tool));

        var toolCharges = ToolCharges.builder()
                .toolType(LADDER)
                .dailyCharge(1.99)
                .weekdayCharge(Boolean.TRUE)
                .weekendCharge(Boolean.TRUE)
                .holidayCharge(Boolean.FALSE)
                .build();
        when(toolChargesService.getToolChargesByToolType(anyString())).thenReturn(toolCharges);

        var checkoutDto = checkoutService.checkOut(input);
        assert checkoutDto.getChargeDays() == 2;
        assert checkoutDto.getDiscountAmount().equals(new BigDecimal("0.40"));
        assert checkoutDto.getDueDate().equals(LocalDate.of(2020,7,2)
                .plusDays(input.getRentalDayCount()));
        assert checkoutDto.getPreDiscountCharge().equals(new BigDecimal("3.98"));
        assert checkoutDto.getFinalCharge().equals(new BigDecimal("3.58"));
    }

    @Test
    public void testCheckoutChainsawWithDiscountJuly4thJuly4thSaturday() {
        var input = CheckOutInputDTO.builder()
                .toolCode(CHAIN_SAW_STIHL_BRAND)
                .rentalDayCount(5)
                .discountPercent(25)
                .checkoutDate(LocalDate.of(2015,7,2))
                .build();

        var tool = Tool.builder()
                .toolCode(CHAIN_SAW_STIHL_BRAND)
                .toolType(CHAIN_SAW)
                .brand(STIHL_BRAND)
                .build();
        when(toolsService.getTool(anyString())).thenReturn(Collections.singletonList(tool));

        var toolCharges = ToolCharges.builder()
                .toolType(CHAIN_SAW)
                .dailyCharge(1.49)
                .weekdayCharge(Boolean.TRUE)
                .weekendCharge(Boolean.FALSE)
                .holidayCharge(Boolean.TRUE)
                .build();
        when(toolChargesService.getToolChargesByToolType(anyString())).thenReturn(toolCharges);

        var checkoutDto = checkoutService.checkOut(input);
        assert checkoutDto.getChargeDays() == 3;
        assert checkoutDto.getDiscountAmount().equals(new BigDecimal("1.12"));
        assert checkoutDto.getDueDate().equals(LocalDate.of(2015,7,2)
                .plusDays(input.getRentalDayCount()));
        assert checkoutDto.getPreDiscountCharge().equals(new BigDecimal("4.47"));
        assert checkoutDto.getFinalCharge().equals(new BigDecimal("3.35"));
    }

    @Test
    public void testCheckoutJackHammerDeWaltNoDiscountLaborDayFirstWeekNoMonday() {
        var input = CheckOutInputDTO.builder()
                .toolCode(JACK_HAMMER_DEWALT_BRAND)
                .checkoutDate(LocalDate.of(2015,9,3))
                .rentalDayCount(6)
                .discountPercent(0)
                .build();

        var tool = Tool.builder()
                .toolCode(JACK_HAMMER_DEWALT_BRAND)
                .toolType(JACK_HAMMER)
                .brand(DEWALT_BRAND)
                .build();
        when(toolsService.getTool(anyString())).thenReturn(Collections.singletonList(tool));

        var toolCharges = ToolCharges.builder()
                .toolType(JACK_HAMMER)
                .dailyCharge(2.99)
                .weekdayCharge(Boolean.TRUE)
                .weekendCharge(Boolean.FALSE)
                .holidayCharge(Boolean.FALSE)
                .build();
        when(toolChargesService.getToolChargesByToolType(anyString())).thenReturn(toolCharges);
        var checkoutDto = checkoutService.checkOut(input);
        assert checkoutDto.getChargeDays() == 3;
        assert checkoutDto.getDiscountAmount().equals(new BigDecimal("0.00"));
        assert checkoutDto.getDueDate().equals(LocalDate.of(2015,9,3)
                .plusDays(input.getRentalDayCount()));
        assert checkoutDto.getPreDiscountCharge().equals(new BigDecimal("8.97"));
        assert checkoutDto.getFinalCharge().equals(new BigDecimal("8.97"));
    }

    @Test
    public void testCheckoutJackHammerNoDiscountJuly4thSaturday() {
        var input = CheckOutInputDTO.builder()
                .toolCode(JACK_HAMMER_RIDGID_BRAND)
                .checkoutDate(LocalDate.of(2015,7,2))
                .rentalDayCount(9)
                .discountPercent(0)
                .build();

        var tool = Tool.builder()
                .toolCode(JACK_HAMMER_RIDGID_BRAND)
                .toolType(JACK_HAMMER)
                .brand(RIDGID_BRAND)
                .build();
        when(toolsService.getTool(anyString())).thenReturn(Collections.singletonList(tool));

        var toolCharges = ToolCharges.builder()
                .toolType(JACK_HAMMER)
                .dailyCharge(2.99)
                .weekdayCharge(Boolean.TRUE)
                .weekendCharge(Boolean.FALSE)
                .holidayCharge(Boolean.FALSE)
                .build();
        when(toolChargesService.getToolChargesByToolType(anyString())).thenReturn(toolCharges);

        var checkoutDto = checkoutService.checkOut(input);
        assert checkoutDto.getChargeDays() == 6;
        assert checkoutDto.getDiscountAmount().equals(new BigDecimal("0.00"));
        assert checkoutDto.getDueDate().equals(LocalDate.of(2015,7,2)
                .plusDays(input.getRentalDayCount()));
        assert checkoutDto.getPreDiscountCharge().equals(new BigDecimal("17.94"));
        assert checkoutDto.getFinalCharge().equals(new BigDecimal("17.94"));
    }

    @Test
    public void testCheckoutJackHammerWithDiscountJuly4thSaturday() {
        var input = CheckOutInputDTO.builder()
                .toolCode(JACK_HAMMER_RIDGID_BRAND)
                .checkoutDate(LocalDate.of(2020,7,3))
                .rentalDayCount(4)
                .discountPercent(50)
                .build();

        var tool = Tool.builder()
                .toolCode(JACK_HAMMER_RIDGID_BRAND)
                .toolType(JACK_HAMMER)
                .brand(RIDGID_BRAND)
                .build();
        when(toolsService.getTool(anyString())).thenReturn(Collections.singletonList(tool));

        var toolCharges = ToolCharges.builder()
                .toolType(JACK_HAMMER)
                .dailyCharge(2.99)
                .weekdayCharge(Boolean.TRUE)
                .weekendCharge(Boolean.FALSE)
                .holidayCharge(Boolean.FALSE)
                .build();
        when(toolChargesService.getToolChargesByToolType(anyString())).thenReturn(toolCharges);

        var checkoutDto = checkoutService.checkOut(input);
        assert checkoutDto.getChargeDays() == 1;
        assert checkoutDto.getDiscountAmount().equals(new BigDecimal("1.50"));
        assert checkoutDto.getDueDate().equals(LocalDate.of(2020,7,3)
                .plusDays(input.getRentalDayCount()));
        assert checkoutDto.getPreDiscountCharge().equals(new BigDecimal("2.99"));
        assert checkoutDto.getFinalCharge().equals(new BigDecimal("1.49"));
    }
}
