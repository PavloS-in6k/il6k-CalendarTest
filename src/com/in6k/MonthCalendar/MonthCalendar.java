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
            if (isDayBeginsWeek(dayOfMonthNumber)) {
                calendar += outputGenerator.getOpenLineTag();
            }
            calendar += getDay(dayOfMonthNumber);
            if (isDayEndsWeek(dayOfMonthNumber)) {
                calendar += outputGenerator.getCloseLineTag();
            }
        }
        calendar += getCalendarEnding();
        return calendar;
    }

    private String getCalendarEnding() {
        return outputGenerator.getCloseInfo();
    }

    private String getDay(int dayOfMonthNumber) {
        return getHighlightedDay(dayOfMonthNumber);
    }

    private boolean isDayEndsWeek(int dayOfMonthNumber) {
        return DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY
                || date.lengthOfMonth() == dayOfMonthNumber;
    }

    private boolean isDayBeginsWeek(int dayOfMonthNumber) {
        return DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.MONDAY;
    }

    private String getHighlightedDay(int dayOfMonthNumber) {
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            return outputGenerator.getHighlightedDayToday(dayOfMonthNumber);
        } else {
            if (isDayWeekend(dayOfMonthNumber)) {
                return outputGenerator.getHighlightedDayWeekend(dayOfMonthNumber);
            } else {
                return outputGenerator.getHighlightedDayWork(dayOfMonthNumber);
            }
        }
    }

    private boolean isDayWeekend(int dayOfMonthNumber) {
        return DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY;
    }

    private String getCalendarBeginning() {
        return outputGenerator.getOpenInfo()
                + getDaysOfWeek()
                + outputGenerator.getOpenLineTag()
                + getEmptyPartOfCalendar();
    }

    private String getEmptyPartOfCalendar() {
        String forTabs = "";
        for (int i = 1; i < DayOfWeek.from(date.withDayOfMonth(1)).getValue(); i++) {
            forTabs += outputGenerator.getEmptyPartOfCalendar(date);
        }
        return forTabs;
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
