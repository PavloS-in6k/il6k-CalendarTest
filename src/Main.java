import com.in6k.Controller.Commands;
import com.in6k.Controller.Controller;
import com.in6k.HtmlDocument.HtmlDocument;
import com.in6k.MonthCalendar.MonthCalendar;
import com.in6k.MonthCalendar.OutputStrategy.BracketsOutput;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Html.HtmlOutput;
import com.in6k.MonthCalendar.OutputStrategy.TypeOfRequestedOutput;
import com.in6k.MonthPeriod.MonthPeriodImpl;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        MonthCalendar calendar = getDateFromKeyboard(sc);
        LocalDate LD = calendar.getToday();
        calendar.setSupplier(LocalDate::now);
        if (args.length == 1) {
            setOutputType(calendar, TypeOfRequestedOutput.valueOf(args[0]));
        }

        String command;

        Controller controller = new Controller(new MonthPeriodImpl(YearMonth.from(LD)));

        while (true) {
            System.out.print("Enter your command (next, previous, increase, decrease, or exit): \n");
            command = sc.next();

            if (command.equals("exit")) break;
            if (command.equals("next")) controller.run(Commands.NEXT);
            if (command.equals("previous")) controller.run(Commands.PREVIOUS);
            if (command.equals("increase")) controller.run(Commands.INCREASE);
            if (command.equals("decrease")) controller.run(Commands.DECREASE);
        }

        sc.close();

        generateTextBracketsFileOutput(calendar);
        generateHTMLFileOutput(calendar);
    }


    private static MonthCalendar getDateFromKeyboard(Scanner sc) {
        MonthCalendar calendar;
        System.out.print("Enter your today in format YYYY-MM-DD : \n");
        calendar = new MonthCalendar(LocalDate.parse(sc.nextLine()));
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