package com.in6k.MonthCalendar.OutputStrategy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ConsoleOutput implements Output {
    private String delimiter = "\t";

    @Override
    public String getDayOfWeekWeekendName(DayOfWeek day) {
        return "" + DayColor.WEEKEND + day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + delimiter;
    }

    @Override
    public String getDayOfWeekWorkName(DayOfWeek day) {
        return "" + DayColor.WORK + day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + delimiter;
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
    public String getHighlightedDayToday(int dayOfMonthNumber) {
        return DayColor.TODAY + dayOfMonthNumber + delimiter;
    }

    @Override
    public String getHighlightedDayWork(int dayOfMonthNumber) {
        return DayColor.WORK + dayOfMonthNumber + delimiter;
    }

    @Override
    public String getHighlightedDayWeekend(int dayOfMonthNumber) {
        return DayColor.WEEKEND + dayOfMonthNumber + delimiter;
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
