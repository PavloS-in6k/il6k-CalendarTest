import com.in6k.HtmlDocument.HtmlDocument;
import com.in6k.MonthCalendar.MonthCalendar;
import com.in6k.MonthCalendar.OutputStrategy.BracketsOutput;
import com.in6k.MonthCalendar.OutputStrategy.ConsoleOutput;
import com.in6k.MonthCalendar.OutputStrategy.Html.HtmlOutput;
import com.in6k.MonthCalendar.OutputStrategy.TypeOfRequestedOutput;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        MonthCalendar calendar = getDateFromKeyboard();

        if (args.length == 1) {
            setOutputType(calendar, TypeOfRequestedOutput.valueOf(args[0]));
        }

        System.out.print(calendar.getStringCalendar());
        generateTextBracketsFileOutput(calendar);
        generateHTMLFileOutput(calendar);
    }

    private static MonthCalendar getDateFromKeyboard() {
        MonthCalendar calendar = new MonthCalendar();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your today in format YYYY-MM-DD : \n");
        calendar.setLocalDate(LocalDate.parse(sc.next()));
        sc.close();
        return calendar;
    }

    private static void generateTextBracketsFileOutput(MonthCalendar calendar) throws Exception {
        setOutputType(calendar, TypeOfRequestedOutput.CONSOLE_BRACKETS);
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        writer.println(calendar.getStringCalendar());
        writer.close();
    }

    private static void generateHTMLFileOutput(MonthCalendar calendar) throws Exception {
        HtmlDocument document = new HtmlDocument();
        setOutputType(calendar, TypeOfRequestedOutput.HTML_DOCUMENT);
        document.addToDocument(calendar.getStringCalendar());
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