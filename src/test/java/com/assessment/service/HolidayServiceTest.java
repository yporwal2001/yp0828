package com.assessment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTest {

    @InjectMocks
    private HolidayService service;

//    private HolidayDTO holidayDto = HolidayDTO.builder()
//            .nextDayHoliday(Boolean.FALSE)
//            .previousDayHoliday(Boolean.FALSE)
//            .weekDay(0)
//            .weekEnd(0)
//            .holiday(0)
//            .build();
    @Test
    public void testFindDayFromDateWeekDay() {
        var date = LocalDate.of(2015,10,2);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 1;
        assert holidayDTO.getWeekEnd() == 0;
        assert holidayDTO.getHoliday() == 0;
    }

    @Test
    public void testFindDayFromDateSaturday() {
        var date = LocalDate.of(2024,8,3);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 0;
        assert holidayDTO.getWeekEnd() == 1;
        assert holidayDTO.getHoliday() == 0;
    }

    @Test
    public void testFindDayFromDateSunday() {
        var date = LocalDate.of(2024,8,4);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 0;
        assert holidayDTO.getWeekEnd() == 1;
        assert holidayDTO.getHoliday() == 0;
    }
    @Test
    public void testFindDayFromDateOtherThanJuly4thWeekday() {
        var date = LocalDate.of(2019,7,3);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 1;
        assert holidayDTO.getWeekEnd() == 0;
        assert holidayDTO.getHoliday() == 0;
    }
    @Test
    public void testFindDayFromDateOtherThanJuly4thWeekend() {
        var date = LocalDate.of(2019,7,6);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 0;
        assert holidayDTO.getWeekEnd() == 1;
        assert holidayDTO.getHoliday() == 0;
    }
    @Test
    public void testFindDayFromDateJuly4thWeekday() {
        var date = LocalDate.of(2019,7,4);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 0;
        assert holidayDTO.getWeekEnd() == 0;
        assert holidayDTO.getHoliday() == 1;
    }

    @Test
    public void testFindDayFromDateJuly4thSaturday() {
        var date = LocalDate.of(2020,7,4);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == -1;
        assert holidayDTO.getWeekEnd() == 1;
        assert holidayDTO.getHoliday() == 1;

    }

    @Test
    public void testFindDayFromDateJuly4thSunday() {
        var date = LocalDate.of(2021,7,4);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == -1;
        assert holidayDTO.getWeekEnd() == 1;
        assert holidayDTO.getHoliday() == 1;

    }
    @Test
    public void testFindDayFromDateLaborDayHolidayFirstWeekNoMonday() {
        var date = LocalDate.of(2015,9,7);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 0;
        assert holidayDTO.getWeekEnd() == 0;
        assert holidayDTO.getHoliday() == 1;
    }

    @Test
    public void testFindDayFromDateLaborDayHolidayFirstWeekMonday() {
        var date = LocalDate.of(2014,9,1);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 0;
        assert holidayDTO.getWeekEnd() == 0;
        assert holidayDTO.getHoliday() == 1;
    }
    @Test
    public void testFindDayFromDateLaborDayFirstWeekDay() {
        var date = LocalDate.of(2014,9,2);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 1;
        assert holidayDTO.getWeekEnd() == 0;
        assert holidayDTO.getHoliday() == 0;
    }
    @Test
    public void testFindDayFromDateLaborDayFirstWeekEnd() {
        var date = LocalDate.of(2014,9,6);
        var holidayDTO = service.findChargeDayFromDate(date);
        assert holidayDTO.getWeekDay() == 0;
        assert holidayDTO.getWeekEnd() == 1;
        assert holidayDTO.getHoliday() == 0;
    }
}
