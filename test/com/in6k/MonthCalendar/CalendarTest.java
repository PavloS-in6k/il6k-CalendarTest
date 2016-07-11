package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.DayColor;
import com.in6k.MonthCalendar.MonthCalendar;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class CalendarTest {

    private static final int SAT = 5;
    private static final int SUN = 6;
    public MonthCalendar calendar = new MonthCalendar();
    String[] splitedCalendar;
    LocalDate today;


    private void splitCalendarIntoLines() {
        this.splitedCalendar = this.calendar.getStringCalendar(TypeOfHighlighting.COLOR).split("\\.?\n");
    }


    private String[] splitLineIntoDates(int index) {
        return this.splitedCalendar[index].split("\\.?\t");
    }

    private void setAdditionalDataAfterChangingDate() {
        this.calendar.setLocalDate(today);
        splitCalendarIntoLines();
    }

    @Before
    public void setup() {
        today = LocalDate.parse("2016-07-07");
        setAdditionalDataAfterChangingDate();
    }

    @Test
    public void testTodayShownWithRigthColor() {
        assertThat(this.calendar.getStringCalendar(TypeOfHighlighting.COLOR),
                containsString(DayColor.TODAY + today.getDayOfMonth()));
    }

    @Test
    public void dateWithMondayNotFirstDayOfMonth() {
        assertThat(this.splitedCalendar[1], not(startsWith(DayColor.WORK)));
    }

    @Test
    public void isDaysOfWeekWrittenRigth() {
        assertThat(this.splitedCalendar[0], is(equalTo("Mon\tTue\tWed\tThu\tFri\tSat\tSun\t")));
    }

    @Test
    public void isWeekendHighlightedWithAnotherColor() {
        String[] splitedDates = splitLineIntoDates(2);
        assertThat(splitedDates[SAT], startsWith(DayColor.WEEKEND));
        assertThat(splitedDates[SUN], startsWith(DayColor.WEEKEND));
    }

    @Test
    public void isWeekendTodayHighlightedWithRigthColor() {
        this.calendar.setLocalDate(LocalDate.parse("2016-07-09"));
        splitCalendarIntoLines();

        String[] splitedDates = splitLineIntoDates(2);
        assertThat(splitedDates[SAT], startsWith(DayColor.TODAY));
        assertThat(splitedDates[SUN], startsWith(DayColor.WEEKEND));
    }

    @Test
    public void noDaysAfterLastDayOfThisMonth() {
        assertThat(this.splitedCalendar[splitedCalendar.length - 1],
                endsWith("" + today.lengthOfMonth() + "\t"));
    }


    @Test
    public void etalonForSpecialDate() {
        this.calendar.setLocalDate(LocalDate.parse("2016-07-07"));
        String etalonCalendar =
                "Mon Tue Wed Thu Fri Sat Sun \n" +
                        "    1 [2] [3] \n" +
                        "4 5 6 {7} 8 [9] [10] \n" +
                        "11 12 13 14 15 [16] [17] \n" +
                        "18 19 20 21 22 [23] [24] \n" +
                        "25 26 27 28 29 [30] [31] \n";
        etalonCalendar = etalonCalendar.replace(" ", "\t");
        assertThat(this.calendar.getStringCalendar(TypeOfHighlighting.BRACKETS), is(equalTo(etalonCalendar)));
    }


    @Test
    public void isWeekBeginsFromMonday() {
        this.today = LocalDate.parse("2016-07-04");
        this.setAdditionalDataAfterChangingDate();
        this.splitLineIntoDates(2);

        assertThat(today.getDayOfWeek(), equalTo(DayOfWeek.MONDAY));
    }
}