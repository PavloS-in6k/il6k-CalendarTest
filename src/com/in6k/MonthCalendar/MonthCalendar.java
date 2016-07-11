package com.in6k.MonthCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthCalendar {
    private LocalDate date;

    public MonthCalendar() {
    }

    public String getStringCalendar(TypeOfHighlighting typeOfHighlighting) {
        String calendar = "";
        calendar += getDaysOfWeekWithTabs();
        calendar += "\n";
        calendar += getTabsForEmptyDateSlots();

        for (int dayOfMonthNumber = 1; dayOfMonthNumber <= this.date.lengthOfMonth(); dayOfMonthNumber++) {
            calendar += getHighlightedDay(dayOfMonthNumber, typeOfHighlighting);

            if (DayOfWeek.from(this.date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                calendar += "\n";
            }
        }

        return calendar;
    }

    private String getHighlightedDay(int dayOfMonthNumber, TypeOfHighlighting typeOfHighlighting) {
        switch (typeOfHighlighting) {
            case COLOR: {
                return getColoredDate(this.date, dayOfMonthNumber) + "\t";
            }
            case BRACKETS: {
                return getFormedWithBracketsDay(this.date, dayOfMonthNumber) + "\t";
            }
        }
        return "";
    }

    private String getTabsForEmptyDateSlots() {
        String forTabs = "";
        for (int i = 1; i < DayOfWeek.from(this.date.withDayOfMonth(1)).getValue(); i++) {
            forTabs += "\t";
        }
        return forTabs;
    }

    private String getDaysOfWeekWithTabs() {
        String forDays = "";
        for (int i = 0; i <= 6; i++) {
            forDays += (DayOfWeek.values())[i].getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t";
        }
        return forDays;
    }

    public String getColoredDate(LocalDate date, int dayOfMonthNumber) {
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            return DayColor.TODAY + dayOfMonthNumber;
        } else {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                    || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                return DayColor.WEEKEND + dayOfMonthNumber;
            } else {
                return DayColor.WORK + dayOfMonthNumber;
            }
        }
    }

    public String getFormedWithBracketsDay(LocalDate date, int dayOfMonthNumber) {
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            return "{" + dayOfMonthNumber + "}";
        } else {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                    || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                return "[" + dayOfMonthNumber + ']';
            } else {
                return "" + dayOfMonthNumber;
            }
        }
    }


    public void setLocalDate(LocalDate date) {
        this.date = date;
    }
}
