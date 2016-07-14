package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Output;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthCalendar {
    private LocalDate date;
    private Output outputGenerator = new ConsoleOutput();
    private List<DayOfWeek> weekends = new ArrayList<>();
    private DayOfWeek firstDayOfWeek;

    public MonthCalendar() {
        setWeekendsAsUsual();
    }

    private void setWeekendsAsUsual() {
        weekends = new ArrayList<>();
        weekends.add(DayOfWeek.SUNDAY);
        weekends.add(DayOfWeek.SATURDAY);
        setWeekStart(DayOfWeek.MONDAY);
    }

    public MonthCalendar(LocalDate today) {
        setLocalDate(today);
        setWeekendsAsUsual();
        setWeekStart(DayOfWeek.MONDAY);
    }

    public MonthCalendar(DayOfWeek day) {
        setWeekendsAsUsual();
        setWeekStart(day);
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
        return DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == firstDayOfWeek.minus(1)
                || date.lengthOfMonth() == dayOfMonthNumber;
    }

    private boolean isDayBeginsWeek(int dayOfMonthNumber) {
        return DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == firstDayOfWeek;
    }

    private String getHighlightedDay(int dayOfMonthNumber) {
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            return outputGenerator.getHighlightedDayToday(dayOfMonthNumber);
        } else {
            if (isWeekend(DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)))) {
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
                + getEmptyPartOfCalendar();
    }

    private String getEmptyPartOfCalendar() {
        String forTabs = "";
        for (int i = 1; i < DayOfWeek.from(date.withDayOfMonth(1)).getValue() - (firstDayOfWeek.getValue() - 1); i++) {
            forTabs += outputGenerator.getEmptyPartOfCalendar(date);
        }
        return forTabs;
    }

    private String getDaysOfWeek() {
        return outputGenerator.getOpenLineTag()
                + getDaysOfWeekNames(firstDayOfWeek)
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
        if (isWeekend(day))
            return outputGenerator.getDayOfWeekWeekendName(day);
        return outputGenerator.getDayOfWeekWorkName(day);
    }

    private boolean isWeekend(DayOfWeek day) {
        for (DayOfWeek weekend : weekends) {
            if (day.equals(weekend)) return true;
        }
        return false;
    }

    public void setLocalDate(LocalDate date) {
        this.date = date;
    }

    public void setOutputGenerator(Output outputGenerator) {
        this.outputGenerator = outputGenerator;
    }

    public void setWeekendDays(List<DayOfWeek> weekendDays) {
        this.weekends = weekendDays;
    }

    public void setWeekStart(DayOfWeek day) {
        this.firstDayOfWeek = day;
    }

    public LocalDate getDate() {
        return date;
    }
}
