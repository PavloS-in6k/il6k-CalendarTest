package com.in6k.MonthCalendar.OutputStrategy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by employee on 7/12/16.
 */
public class BracketsOutput implements Output {

    String delimiter = "\n";

    @Override
    public String getDaysOfWeekNames() {
        String result = "";
        for (int i = 0; i <= 6; i++) {
            result += (DayOfWeek.values())[i].getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + delimiter;
        }
        return result;
    }

    @Override
    public String getEmptyPartOfCalendar(LocalDate date) {
        String forTabs = "";
        for (int i = 1; i < DayOfWeek.from(date.withDayOfMonth(1)).getValue(); i++) {
            forTabs += delimiter;
        }
        return forTabs;
    }

    @Override
    public String getHighlightedDay(LocalDate date, int dayOfMonthNumber) {
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            return "{" + dayOfMonthNumber + "}" + delimiter;
        } else {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                    || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                return "[" + dayOfMonthNumber + ']' + delimiter;
            } else {
                return "" + dayOfMonthNumber + delimiter;
            }
        }
    }
}
