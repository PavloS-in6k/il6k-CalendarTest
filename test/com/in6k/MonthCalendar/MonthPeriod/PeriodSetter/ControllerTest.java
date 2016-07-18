package com.in6k.MonthCalendar.MonthPeriod.PeriodSetter;

import com.in6k.Controller.Commands;
import com.in6k.Controller.Controller;
import com.in6k.MonthCalendar.CalendarInterface;
import com.in6k.MonthCalendar.MonthPeriod.TestCalendar;
import com.in6k.MonthPeriod.MonthPeriod;
import com.in6k.MonthPeriod.MonthPeriodImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.YearMonth;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ControllerTest {
    MonthPeriod monthPeriod = new MonthPeriodImpl(YearMonth.parse("2016-07"));
    Controller controller = new Controller(monthPeriod);
    CalendarInterface calendar = new TestCalendar();

    @Before
    public void setUp() throws Exception {
        monthPeriod = new MonthPeriodImpl(YearMonth.parse("2016-07"));
        controller = new Controller(monthPeriod);
        calendar = new TestCalendar();
    }

    @Test
    public void isMonthWrittenInsideRigth() throws Exception {
        List<YearMonth> YM = controller.getMonthPeriod().getMonths();
        controller.run(Commands.NEXT);
        controller.run(Commands.PREVIOUS);
        assertThat(controller.getMonthPeriod().getMonths().toArray(), equalTo(YM.toArray()));
    }

    @Test
    public void isPeriodChangedRigth() throws Exception {
        controller.run(Commands.NEXT);
        controller.getMonthPeriod().getMonths();
    }
}
