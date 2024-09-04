package com.assessment.service;

import com.assessment.dto.HolidayDTO;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
@Service
public class HolidayService {

    public HolidayDTO findChargeDayFromDate(LocalDate date) {
        var holidayDTO = HolidayDTO
                .builder()
                .weekDay(0)
                .weekEnd(0)
                .holiday(0)
                .build();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfTheMonth = date.getDayOfMonth();
        Month monthOfYear = date.getMonth();

        switch(monthOfYear) {
            case JULY:
                if (dayOfTheMonth == 4) {
                    switch(dayOfWeek) {
                        case MONDAY:
                        case TUESDAY:
                        case WEDNESDAY:
                        case THURSDAY:
                        case FRIDAY: holidayDTO.setHoliday(1);
                            break;
                        case SATURDAY:
                        case SUNDAY: holidayDTO.setWeekEnd(1);
                            holidayDTO.setWeekDay(-1);
                            holidayDTO.setHoliday(1);
                            break;
                    }
                    break;
                } else {
                    switch(dayOfWeek) {
                        case MONDAY:
                        case TUESDAY:
                        case WEDNESDAY:
                        case THURSDAY:
                        case FRIDAY: holidayDTO.setWeekDay(1);
                            break;
                        case SATURDAY:
                        case SUNDAY: holidayDTO.setWeekEnd(1);
                            break;
                    }
                }
                break;
            case SEPTEMBER:
                switch(dayOfWeek) {
                    case MONDAY:
                        if (date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).equals(date)) {
                            holidayDTO.setHoliday(1);
                        }
                        break;
                    case TUESDAY:
                    case WEDNESDAY:
                    case THURSDAY:
                    case FRIDAY: holidayDTO.setWeekDay(1);
                        break;
                    case SATURDAY:
                    case SUNDAY: holidayDTO.setWeekEnd(1);
                        break;
                }
                break;
            default:
                switch(dayOfWeek) {
                    case MONDAY:
                    case TUESDAY:
                    case WEDNESDAY:
                    case THURSDAY:
                    case FRIDAY: holidayDTO.setWeekDay(1);
                        break;
                    case SATURDAY:
                    case SUNDAY: holidayDTO.setWeekEnd(1);
                        break;
                }
        }
        return holidayDTO;
    }
}
