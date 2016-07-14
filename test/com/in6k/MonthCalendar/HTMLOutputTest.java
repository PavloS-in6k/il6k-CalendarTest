package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.HTML.CSSHighlighthClass;
import com.in6k.MonthCalendar.OutputStrategy.HTML.HTMLOutput;
import com.in6k.MonthCalendar.OutputStrategy.Output;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class HTMLOutputTest {
    private LocalDate today;
    private Output outputGenerator = new HTMLOutput();

    @Before
    public void setup() throws Exception {
        today = LocalDate.parse("2016-07-07");
    }

    @Test
    public void isDayNamesPartCorrect() throws Exception {
        String dayNames = getDayNames();
        assertThat(dayNames, equalTo(
                "<td class=\"work\">Mon</td><td class=\"work\">Tue</td><td class=\"work\">Wed</td>" +
                        "<td class=\"work\">Thu</td><td class=\"work\">Fri</td><td class=\"weekend\">Sat</td>" +
                        "<td class=\"weekend\">Sun</td>"
        ));
    }

    private String getDayNames() {
        String dayNames = "";
        dayNames += outputGenerator.getDayOfWeekWorkName(DayOfWeek.MONDAY);
        for (DayOfWeek day = DayOfWeek.TUESDAY; !day.equals(DayOfWeek.MONDAY); day = day.plus(1)) {
            if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY))
                dayNames += outputGenerator.getDayOfWeekWeekendName(day);
            else
                dayNames += outputGenerator.getDayOfWeekWorkName(day);
        }
        return dayNames;
    }

    @Test
    public void isTodayHighlighted() throws Exception {
        assertThat(outputGenerator.getHighlightedDayToday(7),
                equalTo("<td class=\"" + CSSHighlighthClass.TODAY + "\">7</td>"));
    }

    @Test
    public void isWorkDayHighlighted() throws Exception {
        assertThat(outputGenerator.getHighlightedDayWork(1), equalTo("<td class=\"" + CSSHighlighthClass.WORK + "\">1</td>"));
    }

    @Test
    public void isWeekendHighlighted() throws Exception {
        assertThat(outputGenerator.getHighlightedDayWeekend(2),
                equalTo("<td class=\"" + CSSHighlighthClass.WEEKEND + "\">2</td>"));
    }

    @Test
    public void isEmptyCellsCreatedRight() throws Exception {
        assertThat(outputGenerator.getEmptyPartOfCalendar(today),
                equalTo("<td class=\"work\"></td><td class=\"work\"></td><td class=\"work\"></td><td class=\"work\"></td>"));
    }


}
