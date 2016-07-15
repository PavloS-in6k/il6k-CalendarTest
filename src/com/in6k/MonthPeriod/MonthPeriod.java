package com.in6k.MonthPeriod;

import java.time.YearMonth;
import java.util.List;

/**
 * Created by employee on 7/15/16.
 */
public interface MonthPeriod {
    MonthPeriod next();

    MonthPeriod previous();

    MonthPeriod increase();

    MonthPeriod decrease();

    List<YearMonth> getMonths();
}
