package com.in6k.Controller;

import com.in6k.MonthCalendar.MonthCalendar;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Output;
import com.in6k.MonthPeriod.MonthPeriod;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private MonthCalendar calendar = new MonthCalendar();
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
        outputCalendar();
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
        calendar.setSupplier(LocalDate::now);
        List<String> generalCalendar = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            generalCalendar.add("");
        }


        // output.getOpenLineTag() + "?(.)?" + output.getCloseLineTag()
        if (calendarMonths.size() > 1) {
            for (int i = 0; i < calendarMonths.size(); i++) {
                String[] splitedCalendar = calendarMonths.get(i)//calendar.generateCalendar(calendarMonths.getMonths().get(i))
                        .split("\\.?" + output.getCloseLineTag());
                {
                    List<String> spltdClndr = Arrays.asList(splitedCalendar);
                    ArrayList<String> spltdCalendar= new ArrayList<>(spltdClndr);
                    for (int k = splitedCalendar.length; k < 7; k++) {
                            String res = "";
                            for (int j = 0; j <= 7; j++) {
                                res += output.getEmptyPartOfCalendar();
                            }
                            spltdCalendar.add(res);
                    }
                    //splitedCalendar = new String[spltdCalendar.size()];
                    splitedCalendar = spltdCalendar.toArray(splitedCalendar);
                }
                if (i == 0) {
                    for (int k = 0; k < splitedCalendar.length; k++) {
                        generalCalendar.set(k, generalCalendar.get(k).concat(splitedCalendar[k]));
                    }
                }
                if ((i != 0) && (i != calendarMonths.size() - 1)) {
                    for (int k = 0; k < splitedCalendar.length; k++) {
                        generalCalendar.set(k, generalCalendar.get(k).concat(splitedCalendar[k].replaceAll(output.getOpenLineTag(), "")));
                    }
                }
                if (i == calendarMonths.size() - 1) {
                    for (int k = 0; k < splitedCalendar.length; k++) {
                        generalCalendar.set(k, generalCalendar.get(k).concat(splitedCalendar[k].concat(output.getCloseLineTag())));
                    }
                }
            }
//            for (int i = 0; i < generalCalendar.size(); i++) {
//                generalCalendar.set(i, generalCalendar.get(i).concat(output.getCloseLineTag()));
//            }
            for (int i = 0; i < generalCalendar.size(); i++) {
                System.out.print(generalCalendar.get(i));
            }
        } else {
            for (int i = 0; i < calendarMonths.size(); i++) {
                System.out.print(calendarMonths.get(i));
            }
        }
    }

    public List<String> getCalendarMonths() {
        return calendarMonths;
    }

    public MonthPeriod getMonthPeriod() {
        return monthPeriod;
    }
}

