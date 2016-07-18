package com.in6k.Controller;

import com.in6k.MonthCalendar.CalendarInterface;
import com.in6k.MonthCalendar.MonthCalendar;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Output;
import com.in6k.MonthPeriod.MonthPeriod;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private CalendarInterface calendar = new MonthCalendar();
    private MonthPeriod monthPeriod;
    private List<String> calendarMonths = new ArrayList<>();

    public Controller(MonthPeriod monthPeriod) {
        this.monthPeriod = monthPeriod;
    }

    public void run(Commands command) throws Exception {
        if (command.equals(Commands.NEXT)) {
            monthPeriod = monthPeriod.next();
        }
        if (command.equals(Commands.PREVIOUS)) {
            monthPeriod = monthPeriod.previous();
        }
        if (command.equals(Commands.INCREASE)) {
            monthPeriod = monthPeriod.increase();
        }
        if (command.equals(Commands.DECREASE)) {
            monthPeriod = monthPeriod.decrease();
        }
        storeInfo();
    }

    private void storeInfo() throws Exception {
        MonthCalendar monthCalendar = new MonthCalendar();
        monthCalendar.setSupplier(LocalDate::now);
        List<YearMonth> returnedMonths = monthPeriod.getMonths();
        for (int i = 0; i < returnedMonths.size(); i++) {
            calendarMonths.add(monthCalendar.generateCalendar(returnedMonths.get(i)));
        }
    }


    private void outputCalendar() throws Exception {
        Output output = new ConsoleOutput();
        List<String> calendar = new ArrayList<>();
        splitedCalendar = calendar.generateCalendar(YearMonth.from(today)).split("\\.?\n");
        output.getOpenLineTag() + "?(.)?" + output.getCloseLineTag()
        if (calendarMonths.size() > 1)
            for (int i = 0; i < calendarMonths.size(); i++) {
                if (i == 0) {
                    calendarMonths.get(i).replace(output.getCloseLineTag(), "")
                }


            }


        for (int i = 0; i < calendarMonths.size(); i++) {
            System.out.print(calendarMonths.get(i));
        }
    }

    public List<String> getCalendarMonths() {
        return calendarMonths;
    }

    public MonthPeriod getMonthPeriod() {
        return monthPeriod;
    }
}

