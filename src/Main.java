import com.in6k.HTMLDocument.HTMLDocument;
import com.in6k.MonthCalendar.MonthCalendar;
import com.in6k.MonthCalendar.OutputStrategy.TypeOfRequestedOutput;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        MonthCalendar calendar = getDateFromKeyboard();
        System.out.print(calendar.getStringCalendar(TypeOfRequestedOutput.CONSOLE_COLOR));
        generateFileOutput(calendar);
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

    private static void generateFileOutput(MonthCalendar calendar) throws Exception {
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        writer.println(calendar.getStringCalendar(TypeOfRequestedOutput.CONSOLE_BRACKETS));
        writer.close();
    }

    private static void generateHTMLFileOutput(MonthCalendar calendar) throws Exception {
        HTMLDocument document = new HTMLDocument();
        document.addToDocument(calendar.getStringCalendar(TypeOfRequestedOutput.HTML_DOCUMENT));
        PrintWriter writer = new PrintWriter("calendar.html", "UTF-8");
        writer.println(document.getDocument());
        writer.close();
    }

}