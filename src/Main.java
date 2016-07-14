import com.in6k.HtmlDocument.HtmlDocument;
import com.in6k.MonthCalendar.MonthCalendar;
import com.in6k.MonthCalendar.OutputStrategy.BracketsOutput;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Html.HtmlOutput;
import com.in6k.MonthCalendar.OutputStrategy.TypeOfRequestedOutput;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        MonthCalendar calendar = getDateFromKeyboard();
        if (args.length == 1) {
            setOutputType(calendar, TypeOfRequestedOutput.valueOf(args[0]));
        }
        generateTextBracketsFileOutput(calendar);
        generateHTMLFileOutput(calendar);
        setOutputType(calendar, TypeOfRequestedOutput.CONSOLE_COLOR);
        while (true) {
            System.out.print(calendar.generateCalendar(YearMonth.from(calendar.getToday())));
        }
    }

    private static MonthCalendar getDateFromKeyboard() {
        MonthCalendar calendar;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your today in format YYYY-MM-DD : \n");
        calendar = new MonthCalendar(LocalDate.parse(sc.next()));
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