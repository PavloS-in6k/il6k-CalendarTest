import com.in6k.HtmlDocument.HtmlDocument;
import com.in6k.MonthCalendar.MonthCalendar;
import com.in6k.MonthCalendar.OutputStrategy.BracketsOutput;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Html.HtmlOutput;
import com.in6k.MonthCalendar.OutputStrategy.TypeOfRequestedOutput;
import com.in6k.MonthPeriod.MonthPeriod;
import com.in6k.MonthPeriod.MonthPeriodImpl;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        LocalDate LD = null;
        MonthCalendar calendar = getDateFromKeyboard(LD);
        calendar.setSupplier(LocalDate::now);
        if (args.length == 1) {
            setOutputType(calendar, TypeOfRequestedOutput.valueOf(args[0]));
        }

        MonthPeriod monthPeriod = new MonthPeriodImpl(YearMonth.from(LD));
        String command;
        int status = 1;

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your command : \n");
            command = sc.next();
            sc.close();
            if (command.equals("")) break;
            if (command.equals("next")) {
                if (monthPeriod.getMonths().size() == 1) {
                    if (monthPeriod.getMonths().get(0).getMonth().equals(Month.DECEMBER)) {

                    }
                }
                monthPeriod = monthPeriod.next();
            }
            if (command.equals("previous")) {

            }
            if (command.equals("increase")) {
                monthPeriod = monthPeriod.increase();
                outputCalendar(monthPeriod.getMonths());
            }
            if (command.equals("decrease")) {
                monthPeriod = monthPeriod.decrease();
                outputCalendar(monthPeriod.getMonths());
            }

        }


        generateTextBracketsFileOutput(calendar);
        generateHTMLFileOutput(calendar);
//        setOutputType(calendar, TypeOfRequestedOutput.CONSOLE_COLOR);
//        while (true) {
//            System.out.print(calendar.generateCalendar(YearMonth.from(calendar.getToday())));
//        }
    }

    private static void outputCalendar(List<YearMonth> months) throws Exception {
        for(int i = 0; i <= months.size(); i++)
        {
            MonthCalendar monthCalendar = new MonthCalendar();
            System.out.print(monthCalendar.generateCalendar(months.get(i)));
        }
    }

    private static MonthCalendar getDateFromKeyboard(LocalDate LD) {
        MonthCalendar calendar;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your today in format YYYY-MM-DD : \n");
        LD = LocalDate.parse(sc.next());
        calendar = new MonthCalendar(LD);
        sc.close();
        return calendar;
    }

    private static void generateTextBracketsFileOutput(MonthCalendar calendar) throws Exception {
        setOutputType(calendar, TypeOfRequestedOutput.CONSOLE_BRACKETS);
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        writer.println(calendar.generateCalendar(YearMonth.from(calendar.getToday())));
        writer.close();
    }

    private static void generateHTMLFileOutput(MonthCalendar calendar) throws Exception {
        HtmlDocument document = new HtmlDocument();
        setOutputType(calendar, TypeOfRequestedOutput.HTML_DOCUMENT);
        document.addToDocument(calendar.generateCalendar(YearMonth.from(calendar.getToday())));
        PrintWriter writer = new PrintWriter("calendar.html", "UTF-8");
        writer.println(document.getDocument());
        writer.close();
    }

    private static void setOutputType(MonthCalendar calendar, TypeOfRequestedOutput typeOfRequestedOutput) {
        switch (typeOfRequestedOutput) {
            case CONSOLE_BRACKETS: {
                calendar.setOutputGenerator(new BracketsOutput());
                break;
            }
            case CONSOLE_COLOR: {
                calendar.setOutputGenerator(new ConsoleOutput());
                break;
            }
            case HTML_DOCUMENT: {
                calendar.setOutputGenerator(new HtmlOutput());
                break;
            }
        }
    }
}