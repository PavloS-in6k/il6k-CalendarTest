package com.in6k.MonthPeriod.PeriodSetter;

import java.time.YearMonth;
import java.util.List;

public interface PeriodSetter {
    List<YearMonth> getMonths(YearMonth yearMonth);

    PeriodSetter getNextPeriod();

    PeriodSetter getPreviousPeriod();
}
