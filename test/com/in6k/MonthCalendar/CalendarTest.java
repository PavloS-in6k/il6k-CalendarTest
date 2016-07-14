package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.BracketsOutput;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.DayColor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CalendarTest {
    private static final int SAT = 5;
    private static final int SUN = 6;
    private MonthCalendar calendar = new MonthCalendar();
    private String[] splitedCalendar;
    private LocalDate today;

    private void splitCalendarIntoLines() throws Exception {
        splitedCalendar = calendar.getStringCalendar().split("\\.?\n");
    }

    private String[] splitLineIntoDates(int index) {
        return splitedCalendar[index].split("\\.?\t");
    }

    private void setAdditionalDataAfterChangingDate() throws Exception {
        calendar.setLocalDate(today);
        splitCalendarIntoLines();
    }

    @Before
    public void setup() throws Exception {
        today = LocalDate.parse("2016-07-07");
        calendar.setOutputGenerator(new ConsoleOutput());
        setAdditionalDataAfterChangingDate();
    }

    @Test
    public void todayShownWithRigthColor() throws Exception {
        assertThat(calendar.getStringCalendar(),
                containsString(DayColor.TODAY + today.getDayOfMonth()));
    }

    @Test
    public void dateWithMondayAsFirstDayOfMonthShownCorrectly() throws Exception {
        today = LocalDate.parse("2016-02-01");
        setAdditionalDataAfterChangingDate();
        assertThat(splitedCalendar[1], startsWith(DayColor.TODAY + 1));
    }

    @Test
    @Ignore("Find way to identify color of day and remove ignore")
    public void isDaysOfWeekWrittenRigth() {

        assertThat(splitedCalendar[0], is(equalTo("Mon\tTue\tWed\tThu\tFri\tSat\tSun\t")));
    }

    @Test
    public void isWeekendHighlightedWithAnotherColor() {
        String[] splitedDates = splitLineIntoDates(2);
        assertThat(splitedDates[SAT], startsWith(DayColor.WEEKEND));
        assertThat(splitedDates[SUN], startsWith(DayColor.WEEKEND));
    }

    @Test
    public void isWeekendTodayHighlightedWithRigthColor() throws Exception {
        calendar.setLocalDate(LocalDate.parse("2016-07-09"));
        splitCalendarIntoLines();
        String[] splitedDates = splitLineIntoDates(2);
        assertThat(splitedDates[SAT], startsWith(DayColor.TODAY));
        assertThat(splitedDates[SUN], startsWith(DayColor.WEEKEND));
    }

    @Test
    public void noDaysAfterLastDayOfThisMonth() {
        assertThat(splitedCalendar[splitedCalendar.length - 1],
                endsWith("" + today.lengthOfMonth() + "\t"));
    }

    @Test
    public void etalonForSpecialDate() throws Exception {
        calendar.setLocalDate(LocalDate.parse("2016-07-07"));
        calendar.setOutputGenerator(new BracketsOutput());
        String etalonCalendar =
                "Mon Tue Wed Thu Fri [Sat] [Sun] \n" +
                        "    1 [2] [3] \n" +
                        "4 5 6 {7} 8 [9] [10] \n" +
                        "11 12 13 14 15 [16] [17] \n" +
                        "18 19 20 21 22 [23] [24] \n" +
                        "25 26 27 28 29 [30] [31] \n";
        etalonCalendar = etalonCalendar.replace(" ", "\t");
        assertThat(calendar.getStringCalendar(), is(equalTo(etalonCalendar)));
    }

    @Test
    public void isWeekBeginsFromMonday() throws Exception {
        today = LocalDate.parse("2016-07-04");
        setAdditionalDataAfterChangingDate();
        splitLineIntoDates(2);
        assertThat(today.getDayOfWeek(), equalTo(DayOfWeek.MONDAY));
    }
}