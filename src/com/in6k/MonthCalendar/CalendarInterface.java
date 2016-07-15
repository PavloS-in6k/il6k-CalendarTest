package com.in6k.MonthCalendar;

import java.time.YearMonth;

/**
 * Created by employee on 7/14/16.
 */
public interface CalendarInterface {
    String generateCalendar(YearMonth date) throws Exception;
}
