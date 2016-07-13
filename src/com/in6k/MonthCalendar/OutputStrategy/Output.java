package com.in6k.MonthCalendar.OutputStrategy;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface Output {
    String getDayOfWeekWeekendName(DayOfWeek day);

    String getDayOfWeekWorkName(DayOfWeek day);

    String getEmptyPartOfCalendar(LocalDate date);

    String getHighlightedDayToday(int dayOfMonthNumber);

    String getHighlightedDayWork(int dayOfMonthNumber);

    String getHighlightedDayWeekend(int dayOfMonthNumber);

    String getOpenLineTag();

    String getCloseLineTag();

    String getOpenInfo();

    String getCloseInfo();
}
