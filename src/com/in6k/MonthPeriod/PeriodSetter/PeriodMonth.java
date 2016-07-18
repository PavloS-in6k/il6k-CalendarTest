package com.in6k.MonthPeriod.PeriodSetter;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

/**
 * Created by employee on 7/15/16.
 */
public class PeriodMonth implements PeriodSetter {
    @Override
    public List<YearMonth> getMonths(YearMonth yearMonth) {
        return Arrays.asList(yearMonth);
    }

    @Override
    public PeriodSetter getNextPeriod() {
        return new PeriodYear();
    }

    @Override
    public PeriodSetter getPreviousPeriod() {
        return this;
    }
}
