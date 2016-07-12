package com.in6k.MonthCalendar.OutputStrategy;

import java.time.LocalDate;

/**
 * Created by employee on 7/12/16.
 */
public interface Output {
    String getDaysOfWeekNames();
    String getEmptyPartOfCalendar(LocalDate date);
    String getHighlightedDay(LocalDate date, int dayOfMonthNumber);
}
