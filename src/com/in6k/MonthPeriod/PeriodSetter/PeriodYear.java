package com.in6k.MonthPeriod.PeriodSetter;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by employee on 7/15/16.
 */
public class PeriodYear implements PeriodSetter {
    @Override
    public List<YearMonth> getMonths(YearMonth yearMonth) {
        ArrayList<YearMonth> months = new ArrayList<>();
        for (Month m : Month.values()) {
            months.add(YearMonth.from(yearMonth.withMonth(m.getValue())));
        }
        return months;
    }

    @Override
    public PeriodSetter getNextPeriod() {
        return new PeriodYear();
    }

    @Override
    public PeriodSetter getPreviousPeriod() {
        return new PeriodMonth();
    }
}
