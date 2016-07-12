package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.DayColor;
import com.in6k.MonthCalendar.OutputStrategy.Output;
import com.in6k.MonthCalendar.OutputStrategy.TypeOfHighlighting;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthCalendar {
    private LocalDate date;
    private Output outputGenerator = new ConsoleOutput();

    public MonthCalendar() {
    }

    public String getStringCalendar(TypeOfHighlighting typeOfHighlighting) throws Exception {
        String calendar = "";
        calendar += outputGenerator.getDaysOfWeekNames() + "\n" + outputGenerator.getEmptyPartOfCalendar(date);
        for (int dayOfMonthNumber = 1; dayOfMonthNumber <= date.lengthOfMonth(); dayOfMonthNumber++) {
            calendar += outputGenerator.getHighlightedDay(date, dayOfMonthNumber);
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                calendar += "\n";
            }
        }

        return calendar;
    }



    public void setLocalDate(LocalDate date) {
        this.date = date;
    }
}
