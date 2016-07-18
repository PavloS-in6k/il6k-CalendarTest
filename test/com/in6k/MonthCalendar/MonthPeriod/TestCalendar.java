package com.in6k.MonthCalendar.MonthPeriod;

import com.in6k.MonthCalendar.CalendarInterface;

import java.time.YearMonth;

public class TestCalendar implements CalendarInterface {

    @Override
    public String generateCalendar(YearMonth date) throws Exception {
        return date.toString();
    }
}
