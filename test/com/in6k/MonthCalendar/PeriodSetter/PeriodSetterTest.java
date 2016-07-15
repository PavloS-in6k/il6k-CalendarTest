package com.in6k.MonthCalendar.PeriodSetter;

import com.in6k.MonthPeriod.PeriodSetter.PeriodMonth;
import com.in6k.MonthPeriod.PeriodSetter.PeriodSetter;
import com.in6k.MonthPeriod.PeriodSetter.PeriodYear;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;

public class PeriodSetterTest {
    private PeriodSetter period;
    private static YearMonth CONST_YM = YearMonth.parse("2016-07");

    @Before
    public void setUp() throws Exception {
        period = new PeriodMonth();
    }

    @Test
    public void isSingleMonthReturned() throws Exception {
        assertArrayEquals(period.getMonths(CONST_YM).toArray(), Arrays.asList(YearMonth.parse("2016-07")).toArray());
    }

    @Test
    public void isYearPeriodFollowsAfterMonthPeriod() throws Exception {
        period = new PeriodMonth();
        assertThat(period.getNextPeriod(), instanceOf(PeriodYear.class));
    }


    @Test
    public void isMonthPeriodPrecidesYearPeriod() throws Exception {
        period = new PeriodYear();
        assertThat(period.getPreviousPeriod(), instanceOf(PeriodMonth.class));
    }

    @Test
    public void isAllYearMonthsReturnedInRigthSequense() throws Exception {
        period = period.getNextPeriod();
        ArrayList<YearMonth> yearMonths = new ArrayList<>();
        for (Month ym : Month.values()) {
            yearMonths.add(YearMonth.of(2016, ym));
        }
        assertArrayEquals(period.getMonths(CONST_YM).toArray(), yearMonths.toArray());
    }


}
