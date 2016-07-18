package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Output;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MonthCalendar implements CalendarInterface {
    private LocalDate today;
    private YearMonth date;
    private Output outputGenerator = new ConsoleOutput();
    private List<DayOfWeek> weekendDays = new ArrayList<>();
    private DayOfWeek firstDayOfWeek;
    private Supplier<LocalDate> supplier;


    private boolean updatingToday = true;

    public MonthCalendar() {
        setBasicCalendarOptions();
    }

    private void setBasicCalendarOptions() {
        setWeekendDays(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
        setWeekStart(DayOfWeek.MONDAY);
    }

    public MonthCalendar(LocalDate today) {
        setToday(today);
        setBasicCalendarOptions();
    }

    public MonthCalendar(DayOfWeek day) {
        setBasicCalendarOptions();
        setWeekStart(day);
    }

    @Override
    public String generateCalendar(YearMonth date) throws Exception {
        this.date = date;
        setToday(supplier);
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

    private String getCalendarBeginning() {
        return outputGenerator.getOpenInfo()
                + getDaysOfWeek()
                + outputGenerator.getOpenLineTag()
                + getEmptyPartOfCalendar();
    }

    private String getCalendarEnding() {
        return outputGenerator.getCloseInfo();
    }

    private String getDay(int dayOfMonthNumber) {
        return getHighlightedDay(dayOfMonthNumber);
    }

    private boolean isDayEndsWeek(int dayOfMonthNumber) {
        return DayOfWeek.from(date.atDay(dayOfMonthNumber)) == firstDayOfWeek.minus(1)
                || date.lengthOfMonth() == dayOfMonthNumber;
    }

    private boolean isDayBeginsWeek(int dayOfMonthNumber) {
        return DayOfWeek.from(date.atDay(dayOfMonthNumber)) == firstDayOfWeek;
    }

    private String getHighlightedDay(int dayOfMonthNumber) {
        if (today.getDayOfMonth() == dayOfMonthNumber && date.getMonth().equals(today.getMonth()) && (date.getYear() == today.getYear())) {
            return outputGenerator.getHighlightedDayToday(dayOfMonthNumber);
        } else {
            if (isWeekend(DayOfWeek.from(date.atDay(dayOfMonthNumber)))) {
                return outputGenerator.getHighlightedDayWeekend(dayOfMonthNumber);
            } else {
                return outputGenerator.getHighlightedDayWork(dayOfMonthNumber);
            }
        }
    }


    private String getEmptyPartOfCalendar() {
        String forTabs = "";
        for (int i = 1; isThisDayGapBeforeFirstDayOfTheMonth(i); i++) {
            forTabs += outputGenerator.getEmptyPartOfCalendar(today);
        }
        return forTabs;
    }

    private boolean isThisDayGapBeforeFirstDayOfTheMonth(int i) {
        return i < (DayOfWeek.from(LocalDate.of(date.getYear(), date.getMonth().getValue(), 1)).getValue()
                - (firstDayOfWeek.getValue() - 1));
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
        return weekendDays.contains(day);
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public void setToday(Supplier<LocalDate> supplier) {
        this.today = supplier.get();
    }

    public void setOutputGenerator(Output outputGenerator) {
        this.outputGenerator = outputGenerator;
    }

    public void setWeekendDays(List<DayOfWeek> weekendDays) {
        this.weekendDays = weekendDays;
    }

    public void setWeekStart(DayOfWeek firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setUpdatingToday(boolean status) {
        this.updatingToday = status;
    }

    public void setSupplier(Supplier<LocalDate> supplier) {
        this.supplier = supplier;
    }
}
