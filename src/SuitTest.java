import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class SuitTest {

    public MyCalendar calendar = new MyCalendar();
    String[] splitedCalendar;
    String[] splitedDates;


    private void splitCalendarIntoLines() {
        this.splitedCalendar = this.calendar.getStringCalendar().split("\\.?\n");
    }


    private void splitLineIntoDates(int index) {
        this.splitedDates = this.splitedCalendar[index].split("\\.?\t");
    }

    @Before
    public void setup() {
        this.calendar.setLocalDate(LocalDate.now());
        splitCalendarIntoLines();
    }

    @Test
    public void testTodayShownWithRigthColor() {
        assertThat(this.calendar.getStringCalendar(),
                containsString(DayColor.TODAY_TEXTCODE + this.calendar.getDate().getDayOfMonth()));
    }

    @Test
    public void dateWithMondayNotFirstDayOfMonth() {
        assertThat(this.splitedCalendar[1], not(startsWith(DayColor.WORK_TEXTCODE)));
    }

    @Test
    public void isDaysOfWeekWrittenRigth() {
        assertThat(this.splitedCalendar[0], is(equalTo("Mon\tTue\tWed\tThu\tFri\tSat\tSun\t")));
    }

    @Test
    public void isWeekendHighlightedWithAnotherColor() {
        this.calendar.setLocalDate(LocalDate.parse("2016-07-07"));
        splitCalendarIntoLines();
        String[] splitedDates = this.splitedCalendar[2].split("\\.?\t");
        assertThat(splitedDates[5], startsWith(DayColor.WEEKEND_TEXTCODE));
        assertThat(splitedDates[6], startsWith(DayColor.WEEKEND_TEXTCODE));
    }

    @Test
    public void isWeekendTodayHighlightedWithRigthColor() {
        this.calendar.setLocalDate(LocalDate.parse("2016-07-09"));
        splitCalendarIntoLines();
        splitLineIntoDates(2);
        assertThat(splitedDates[5], startsWith(DayColor.TODAY_TEXTCODE));
        assertThat(splitedDates[6], startsWith(DayColor.WEEKEND_TEXTCODE));
    }

    @Test
    public void noDaysAfterLastDayOfThisMonth() {
        assertThat(this.splitedCalendar[splitedCalendar.length - 1],
                endsWith("" + this.calendar.getDate().lengthOfMonth() + "\t"));
    }


    @Test
    public void etalonForSpecialDate() {
        this.calendar.setLocalDate(LocalDate.parse("2016-07-06"));
        assertThat(this.calendar.getStringCalendar(), is(equalTo(
                "Mon\tTue\tWed\tThu\tFri\tSat\tSun\t\n" +
                        "\t\t\t\t\u001B[37m1\t\u001B[31m2\t\u001B[31m3\t\n" +
                        "\u001B[37m4\t\u001B[37m5\t\u001B[33m6\t\u001B[37m7\t\u001B[37m8\t\u001B[31m9\t\u001B[31m10\t\n" +
                        "\u001B[37m11\t\u001B[37m12\t\u001B[37m13\t\u001B[37m14\t\u001B[37m15\t\u001B[31m16\t\u001B[31m17\t\n" +
                        "\u001B[37m18\t\u001B[37m19\t\u001B[37m20\t\u001B[37m21\t\u001B[37m22\t\u001B[31m23\t\u001B[31m24\t\n" +
                        "\u001B[37m25\t\u001B[37m26\t\u001B[37m27\t\u001B[37m28\t\u001B[37m29\t\u001B[31m30\t\u001B[31m31\t\n")));
    }


    @Test
    public void isWeekBeginsFromMonday() {
        this.calendar.setLocalDate(LocalDate.parse("2016-07-04"));
        this.splitCalendarIntoLines();
        this.splitLineIntoDates(2);
        assertThat(this.calendar.getDate().getDayOfWeek(), equalTo(DayOfWeek.MONDAY));
    }

    /*
    @Test
    public void dateWithMondayFirstDayOfMonthAndTodayDayShownWithRigthColor() {
        this.calendar.setLocalDate(LocalDate.parse("2016-07-04"));
        anyOf(not(containsString(DayColor.WORK_TEXTCODE + "1")),
                not(containsString(DayColor.TODAY_TEXTCODE + "1")),
                is(this.calendar.getDate().getDayOfWeek()), equalTo(DayOfWeek.MONDAY));
    }
    */
}
