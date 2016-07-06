import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class SuitTest {

    public MyCalendar calendar = new MyCalendar();
    String[] splitedCalendar;


    @Before
    public void setup() {
        this.calendar.setLocalDate(LocalDate.now());
        this.splitedCalendar = this.calendar.getStringCalendar().split("\\.?\n");
    }


    @Test
    public void testTodayShownWithYellow() {
        assertThat(this.calendar.getStringCalendar(), containsString(MyCalendar.yellow + this.calendar.getDate().getDayOfMonth()));
    }

    @Test
    public void dateWithMondayNotFirstDayOfMonth() {
        assertThat(this.splitedCalendar[1], not(startsWith(MyCalendar.black)));
    }


    //This test can be done, if entered date have monday as day of 1 day of month

    // upd: tried to make test condition as written down - and it's doesn't work, no matter that it must work
    // - DayOfWeek enum have method .equals(object), which is only needed requirement to use "equalTo" matcher.
    // I don't know why this matcher not working.
    // anyOf(not(containsString(MyCalendar.black+"1")), not(containsString(MyCalendar.yellow+"1")), is(this.calendar.getDate().getDayOfWeek(),equalTo(DayOfWeek.MONDAY)))
    @Test
    @Ignore
    public void dateWithMondayFirstDayOfMonthAndTodayDay() {
        assertThat(this.calendar.getStringCalendar(), not(containsString(MyCalendar.black + "1")));
    }

    @Test
    public void isDaysOfWeekWrittenRigth() {
        assertThat(this.splitedCalendar[0], is(equalTo("Mon\tTue\tWed\tThu\tFri\tSat\tSun\t")));
    }


    @Test
    public void noDaysAfterLastDayOfThisMonth() {
        assertThat(this.splitedCalendar[splitedCalendar.length - 1], endsWith("" + this.calendar.getDate().lengthOfMonth() + "\t"));
    }

    @Test
    public void etalonForSpecialDate() //for this test required date is 2016-07-06
    {
        assertThat(this.calendar.getStringCalendar(), is(equalTo(
                "Mon\tTue\tWed\tThu\tFri\tSat\tSun\t\n" +
                        "\t\t\t\t\u001B[37m1\t\u001B[31m2\t\u001B[31m3\t\n" +
                        "\u001B[37m4\t\u001B[37m5\t\u001B[33m6\t\u001B[37m7\t\u001B[37m8\t\u001B[31m9\t\u001B[31m10\t\n" +
                        "\u001B[37m11\t\u001B[37m12\t\u001B[37m13\t\u001B[37m14\t\u001B[37m15\t\u001B[31m16\t\u001B[31m17\t\n" +
                        "\u001B[37m18\t\u001B[37m19\t\u001B[37m20\t\u001B[37m21\t\u001B[37m22\t\u001B[31m23\t\u001B[31m24\t\n" +
                        "\u001B[37m25\t\u001B[37m26\t\u001B[37m27\t\u001B[37m28\t\u001B[37m29\t\u001B[31m30\t\u001B[31m31\t\n")));
    }

}
