package com.in6k.MonthCalendar;

import com.in6k.MonthCalendar.OutputStrategy.*;
import com.in6k.MonthCalendar.OutputStrategy.HTML.CSSHighlighthClass;
import com.in6k.MonthCalendar.OutputStrategy.HTML.HTMLOutput;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class HTMLOutputTest {
    LocalDate today;
    Output outputGenerator = new HTMLOutput();

    @Before
    public void setup() throws Exception {
        today = LocalDate.parse("2016-07-07");
    }

    @Test
    public void isTodayHighlighted() throws Exception {
        assertThat(outputGenerator.getHighlightedDayToday(7),
                equalTo("<td class=\"" + CSSHighlighthClass.TODAY + "\">7</td>"));
    }

    @Test
    public void isEmptyCellsCreatedRigth() throws Exception {
        assertThat(outputGenerator.getEmptyPartOfCalendar(today),
                equalTo("<td class=\"work\"></td><td class=\"work\"></td><td class=\"work\"></td><td class=\"work\"></td>"));
    }

    @Test
    public void isWeekendHighlighted() throws Exception {
        assertThat(outputGenerator.getHighlightedDayToday(2),
                equalTo("<td class=\"" + CSSHighlighthClass.WEEKEND + "\">2</td>"));
    }
}
