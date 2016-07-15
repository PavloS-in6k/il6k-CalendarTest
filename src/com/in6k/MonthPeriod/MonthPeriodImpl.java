package com.in6k.MonthPeriod;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

/**
 * Created by employee on 7/15/16.
 */
public class MonthPeriodImpl implements MonthPeriod {
    private List<YearMonth> datesToShow;


    public MonthPeriodImpl(YearMonth date) {
        datesToShow = Arrays.asList(date);
    }

    public MonthPeriodImpl(List<YearMonth> dates) {
        datesToShow = dates;
    }

    @Override
    public MonthPeriod next() {
        if (datesToShow.size() == 1) {
            return new MonthPeriodImpl(datesToShow.get(0).plusMonths(1));
        } else {
            for (YearMonth YM : datesToShow) {
                YM.withYear(YM.getYear() + 1);
            }
            return new MonthPeriodImpl(datesToShow);
        }
    }

    @Override
    public MonthPeriod previous() {
        return new MonthPeriodImpl(datesToShow.get(0).minusMonths(1));
    }

    @Override
    public MonthPeriod increase() {
        return null;
    }

    @Override
    public MonthPeriod decrease() {
        return null;
    }

    @Override
    public List<YearMonth> getMonths() {
        return datesToShow;
    }
}
