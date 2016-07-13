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
    private Output outputGenerator;

    public MonthCalendar() {
    }

    public String getStringCalendar(TypeOfRequestedOutput typeOfRequestedOutput) throws Exception {
        setOutputType(typeOfRequestedOutput);
        String calendar = "";
        calendar += getCalendarBegining();
        for (int dayOfMonthNumber = 1; dayOfMonthNumber <= date.lengthOfMonth(); dayOfMonthNumber++) {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.MONDAY) {
                calendar += outputGenerator.getOpenLineTag();
            }
            calendar += outputGenerator.getHighlightedDay(date, dayOfMonthNumber);
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

    private String getCalendarBegining() {
        return outputGenerator.getOpenInfo()
                + outputGenerator.getOpenLineTag()
                + outputGenerator.getDaysOfWeekNames()
                + outputGenerator.getCloseLineTag()
                + outputGenerator.getOpenLineTag()
                + outputGenerator.getEmptyPartOfCalendar(date);
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
