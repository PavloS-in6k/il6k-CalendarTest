import com.in6k.MonthCalendar.MonthCalendar;
import com.in6k.MonthCalendar.TypeOfHighlighting;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        MonthCalendar calendar = getDateFromKeyboard();
        System.out.print(calendar.getStringCalendar(TypeOfHighlighting.COLOR));
        generateFileOutput(calendar);
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
        writer.println(calendar.getStringCalendar(TypeOfHighlighting.BRACKETS));
        writer.close();
    }


}