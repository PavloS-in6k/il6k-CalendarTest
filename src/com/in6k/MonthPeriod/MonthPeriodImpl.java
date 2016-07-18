package com.in6k.MonthPeriod;

import com.in6k.MonthPeriod.PeriodSetter.PeriodMonth;
import com.in6k.MonthPeriod.PeriodSetter.PeriodSetter;
import com.in6k.MonthPeriod.PeriodSetter.PeriodYear;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

public class MonthPeriodImpl implements MonthPeriod {
    private List<YearMonth> datesToShow;
    private PeriodSetter period;

    public MonthPeriodImpl(YearMonth date) {
        datesToShow = Arrays.asList(date);
        period = new PeriodMonth();
    }

    public MonthPeriodImpl(List<YearMonth> dates) {
        datesToShow = dates;
        if (dates.size() == 1)
            period = new PeriodMonth();
        else
            period = new PeriodYear();
    }

    private MonthPeriodImpl(List<YearMonth> date, PeriodSetter period) {
        this.datesToShow = date;
        this.period = period;
    }

    @Override
    public MonthPeriod next() {
        if (datesToShow.size() == 1) {
            return new MonthPeriodImpl(datesToShow.get(0).plusMonths(1));
        }
        if (getMonths().size() == 12) {
            for (YearMonth YM : datesToShow) {
                YM = YM.plusYears(1);
            }
            return new MonthPeriodImpl(datesToShow, period);
        }
        return null;
    }

    @Override
    public MonthPeriod previous() {
        if (datesToShow.size() == 1) {
            return new MonthPeriodImpl(datesToShow.get(0).minusMonths(1));
        }
        if (getMonths().size() == 12) {
            for (YearMonth YM : datesToShow) {
                YM = YM.minusYears(1);
            }
            return new MonthPeriodImpl(datesToShow, period);
        }
        return null;
    }

    @Override
    public MonthPeriod increase() {
        return new MonthPeriodImpl(period.getNextPeriod().getMonths(datesToShow.get(0)), period.getNextPeriod());
    }

    @Override
    public MonthPeriod decrease() {
        return new MonthPeriodImpl(period.getPreviousPeriod().getMonths(datesToShow.get(0)), period.getPreviousPeriod());
    }

    @Override
    public List<YearMonth> getMonths() {
        return datesToShow;
    }
}
