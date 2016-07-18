package com.in6k.MonthCalendar.OutputStrategy;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class BracketsOutput implements Output {
    private String delimiter = "\t";

    @Override
    public String getDayOfWeekWeekendName(DayOfWeek day) {
        return "[" + day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "]" + delimiter;
    }

    @Override
    public String getDayOfWeekWorkName(DayOfWeek day) {
        return day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + delimiter;
    }

    @Override
    public String getEmptyPartOfCalendar() {
        return delimiter;
    }

    @Override
    public String getHighlightedDayToday(int dayOfMonthNumber) {
        return "{" + dayOfMonthNumber + "}" + delimiter;
    }

    @Override
    public String getHighlightedDayWork(int dayOfMonthNumber) {
        return "" + dayOfMonthNumber + delimiter;
    }

    @Override
    public String getHighlightedDayWeekend(int dayOfMonthNumber) {
        return "[" + dayOfMonthNumber + ']' + delimiter;
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
