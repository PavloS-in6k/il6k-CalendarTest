package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Output;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class MonthCalendar {
    private LocalDate date;
    private Output outputGenerator = new ConsoleOutput();

    public MonthCalendar() {
    }

    public String getStringCalendar() throws Exception {
        String calendar = "";
        calendar += getCalendarBeginning();
        for (int dayOfMonthNumber = 1; dayOfMonthNumber <= date.lengthOfMonth(); dayOfMonthNumber++) {
            calendar += getDay(dayOfMonthNumber);
        }
        calendar += getCalendarEnding();
        return calendar;
    }

    private String getCalendarEnding() {
        return outputGenerator.getCloseInfo();
    }

    private String getDay(int dayOfMonthNumber) {
        String result = "";
        if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.MONDAY) {
            result += outputGenerator.getOpenLineTag();
        }
        result += getHighlightedDay(dayOfMonthNumber);
        if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY
                || date.lengthOfMonth() == dayOfMonthNumber) {
            result += outputGenerator.getCloseLineTag();
        }
        return result;
    }

    private String getHighlightedDay(int dayOfMonthNumber) {
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            return outputGenerator.getHighlightedDayToday(dayOfMonthNumber);
        } else {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                    || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                return outputGenerator.getHighlightedDayWeekend(dayOfMonthNumber);
            } else {
                return outputGenerator.getHighlightedDayWork(dayOfMonthNumber);
            }
        }
    }

    private String getCalendarBeginning() {
        return outputGenerator.getOpenInfo()
                + getDaysOfWeek()
                + outputGenerator.getOpenLineTag()
                + outputGenerator.getEmptyPartOfCalendar(date);
    }

    private String getDaysOfWeek() {
        return outputGenerator.getOpenLineTag()
                + getDaysOfWeekNames(DayOfWeek.MONDAY)
                + outputGenerator.getCloseLineTag();
    }

    private String getDaysOfWeekNames(DayOfWeek firstDayOfWeek) {
        String result = "";
        result += getDayOfWeekName(firstDayOfWeek);
        for (DayOfWeek day = firstDayOfWeek.plus(1); !day.equals(firstDayOfWeek); day = day.plus(1)) {
            result += getDayOfWeekName(day);
        }
        return result;
    }

    private String getDayOfWeekName(DayOfWeek day) {
        if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY))
            return outputGenerator.getDayOfWeekWeekendName(day);
        return outputGenerator.getDayOfWeekWorkName(day);
    }


    public void setLocalDate(LocalDate date) {
        this.date = date;
    }

    public void setOutputGenerator(Output outputGenerator) {
        this.outputGenerator = outputGenerator;
    }
}
