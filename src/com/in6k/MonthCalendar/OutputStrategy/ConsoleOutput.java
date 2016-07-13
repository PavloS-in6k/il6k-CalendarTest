package com.in6k.MonthCalendar.OutputStrategy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ConsoleOutput implements Output {
    String delimiter = "\t";

    @Override
    public String getDaysOfWeekNames() {
        String result = "";
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {
                result += DayColor.WEEKEND + day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            } else {
                result += DayColor.WORK + day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            }
            result += delimiter;
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
            return DayColor.TODAY + dayOfMonthNumber + delimiter;
        } else {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                    || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                return DayColor.WEEKEND + dayOfMonthNumber + delimiter;
            } else {
                return DayColor.WORK + dayOfMonthNumber + delimiter;
            }
        }
    }

    @Override
    public String getOpenLineTag() {
        return "";
    }

    @Override
    public String getCloseLineTag() {
        return "\n";
    }

    @Override
    public String getOpenInfo() {
        return "";
    }

    @Override
    public String getCloseInfo() {
        return "";
    }
}
