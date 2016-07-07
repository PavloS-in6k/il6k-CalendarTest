package com.in6k.MonthCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthCalendar {
    private LocalDate date;

    public MonthCalendar() {
    }


    public String getStringCalendar(Boolean getVersionWithBrackets) {
        String calendar = "";
        for (int i = 0; i <= 6; i++) {
            calendar += (DayOfWeek.values())[i].getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t";
        }

        calendar += "\n";
        for (int i = 1; i < DayOfWeek.from(this.date.withDayOfMonth(1)).getValue(); i++) {
            calendar += "\t";
        }

        for (int dayOfMonthNumber = 1; dayOfMonthNumber <= this.date.lengthOfMonth(); dayOfMonthNumber++) {

            if (!getVersionWithBrackets) {
                calendar += getColoredDate(this.date, dayOfMonthNumber) + "\t";
            }
            else
            {
                calendar += getFormedWithBracketsDay(this.date, dayOfMonthNumber) + "\t";
            }

            if (DayOfWeek.from(this.date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                calendar += "\n";
            }
        }
        return calendar;
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

    public String getFormedWithBracketsDay(LocalDate date, int dayOfMonthNumber)
    {
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
