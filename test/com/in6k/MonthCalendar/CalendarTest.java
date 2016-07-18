package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.BracketsOutput;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.DayColor;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CalendarTest {
    private static final int SAT = 5;
    private static final int SUN = 6;
    private MonthCalendar calendar = new MonthCalendar();
    private String[] splitedCalendar;
    private LocalDate today;

    private void splitCalendarIntoLines() throws Exception {
        splitedCalendar = calendar.generateCalendar(YearMonth.from(today)).split("\\.?\n");
    }

    private String[] splitLineIntoDates(int index) {
        return splitedCalendar[index].split("\\.?\t");
    }

    private void setAdditionalDataAfterChangingDate() throws Exception {
        calendar.setToday(today);
        splitCalendarIntoLines();
    }

    @Before
    public void setup() throws Exception {
        today = LocalDate.parse("2016-07-07");
        calendar.setOutputGenerator(new ConsoleOutput());
        calendar.setUpdatingToday(false);
        calendar.setSupplier(() -> calendar.getToday());
        setAdditionalDataAfterChangingDate();
    }

    @Test
    public void todayShownWithRigthColor() throws Exception {
        assertThat(calendar.generateCalendar(YearMonth.from(today)),
                containsString(DayColor.TODAY + today.getDayOfMonth()));
    }

    @Test
    public void dateWithMondayAsFirstDayOfMonthShownCorrectly() throws Exception {
        today = LocalDate.parse("2016-02-01");
        setAdditionalDataAfterChangingDate();
        assertThat(splitedCalendar[1], startsWith(DayColor.TODAY + 1));
    }

    @Test
    public void isWeekendHighlightedWithAnotherColor() {
        String[] splitedDates = splitLineIntoDates(2);
        assertThat(splitedDates[SAT], startsWith(DayColor.WEEKEND));
        assertThat(splitedDates[SUN], startsWith(DayColor.WEEKEND));
    }

    @Test
    public void isWeekendTodayHighlightedWithRigthColor() throws Exception {
        calendar.setToday(LocalDate.parse("2016-07-09"));
        splitCalendarIntoLines();
        String[] splitedDates = splitLineIntoDates(2);
        assertThat(splitedDates[SAT], startsWith(DayColor.TODAY));
        assertThat(splitedDates[SUN], startsWith(DayColor.WEEKEND));
    }

    @Test
    public void etalonForSpecialDate() throws Exception {
        calendar.setToday(LocalDate.parse("2016-07-07"));
        calendar.setOutputGenerator(new BracketsOutput());
        String etalonCalendar =
                "Mon Tue Wed Thu Fri [Sat] [Sun]  \n" +
                        "    1 [2] [3]  \n" +
                        "4 5 6 {7} 8 [9] [10]  \n" +
                        "11 12 13 14 15 [16] [17]  \n" +
                        "18 19 20 21 22 [23] [24]  \n" +
                        "25 26 27 28 29 [30] [31]  \n";
        etalonCalendar = etalonCalendar.replace(" ", "\t");
        assertThat(calendar.generateCalendar(YearMonth.from(today)), is(equalTo(etalonCalendar)));
    }

    @Test
    public void isWeekBeginsFromMonday() throws Exception {
        today = LocalDate.parse("2016-07-04");
        setAdditionalDataAfterChangingDate();
        splitLineIntoDates(2);
        assertThat(today.getDayOfWeek(), equalTo(DayOfWeek.MONDAY));
    }

    @Test
    public void isWeekStartWithRigthDay() throws Exception {
        setDayAsFirstDayOfWeekAndUpdateTestData(DayOfWeek.FRIDAY);
        assertThat(splitedCalendar[1], startsWith(DayColor.WORK + 1));
    }

    private void setDayAsFirstDayOfWeekAndUpdateTestData(DayOfWeek day) throws Exception {
        calendar.setWeekStart(day);
        splitCalendarIntoLines();
    }

    @Test
    public void isWeekendsHighlightedRight() throws Exception {
        calendar.setWeekendDays(Arrays.asList(DayOfWeek.MONDAY));
        assertThat(splitedCalendar[2], startsWith(DayColor.WORK + 4));
    }

    @Test
    public void isWeekStartConstractorWorks() throws Exception {
        setDayAsFirstDayOfWeekAndUpdateTestData(DayOfWeek.WEDNESDAY);
        calendar.setWeekendDays(Arrays.asList(DayOfWeek.MONDAY));
        splitCalendarIntoLines();
        assertThat(splitedCalendar[1], containsString(DayColor.WEEKEND + 4));
        assertThat(splitedCalendar[2], containsString(DayColor.TODAY + 7));
    }

    @Test
    public void isFirstConstructorWorks() throws Exception {
        calendar = new MonthCalendar(DayOfWeek.THURSDAY);
        setup();
        assertThat(splitedCalendar[2], startsWith(DayColor.TODAY + 7));
    }

    @Test
    public void isConstructorForWeekendsWorks() throws Exception {
        calendar = new MonthCalendar(LocalDate.parse("2016-07-07"));
        assertThat(calendar.getToday(), equalTo(LocalDate.parse("2016-07-07")));
    }

    private void outputCalendarInConsole() throws Exception {
        System.out.print(calendar.generateCalendar(YearMonth.from(today)));
    }
}