package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.BracketsOutput;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.HTML.HTMLOutput;
import com.in6k.MonthCalendar.OutputStrategy.Output;
import com.in6k.MonthCalendar.OutputStrategy.TypeOfRequestedOutput;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class MonthCalendar {
    private LocalDate date;
    private Output outputGenerator = new ConsoleOutput();

    public MonthCalendar() {
    }

    public String getStringCalendar(TypeOfRequestedOutput typeOfRequestedOutput) throws Exception {
        setOutputType(typeOfRequestedOutput);
        String calendar = "";
        calendar += getCalendarBeginning();
        for (int dayOfMonthNumber = 1; dayOfMonthNumber <= date.lengthOfMonth(); dayOfMonthNumber++) {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.MONDAY) {
                calendar += outputGenerator.getOpenLineTag();
            }
            calendar += getHighlightedDay(dayOfMonthNumber);
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                calendar += outputGenerator.getCloseLineTag();
            }
        }
        if (calendar.endsWith(outputGenerator.getOpenLineTag())) {
            calendar += outputGenerator.getCloseLineTag();
        }
        calendar += outputGenerator.getCloseInfo();
        return calendar;
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
                + outputGenerator.getOpenLineTag()
                + getDaysOfWeekNames(DayOfWeek.MONDAY)
                + outputGenerator.getCloseLineTag()
                + outputGenerator.getOpenLineTag()
                + outputGenerator.getEmptyPartOfCalendar(date);
    }

    protected String getDaysOfWeekNames(DayOfWeek firstDayOfWeek) {
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

    private void setOutputType(TypeOfRequestedOutput typeOfRequestedOutput) {
        switch (typeOfRequestedOutput) {
            case CONSOLE_BRACKETS: {
                outputGenerator = new BracketsOutput();
                break;
            }
            case CONSOLE_COLOR: {
                outputGenerator = new ConsoleOutput();
                break;
            }
            case HTML_DOCUMENT: {
                outputGenerator = new HTMLOutput();
                break;
            }
        }
    }

    public void setLocalDate(LocalDate date) {
        this.date = date;
    }
}
