package com.in6k.MonthCalendar.OutputStrategy;

import java.time.LocalDate;

public interface Output {
    String getDaysOfWeekNames();

    String getEmptyPartOfCalendar(LocalDate date);

    String getHighlightedDay(LocalDate date, int dayOfMonthNumber);

    String getOpenLineTag();

    String getCloseLineTag();

    String getOpenInfo();

    String getCloseInfo();
}
