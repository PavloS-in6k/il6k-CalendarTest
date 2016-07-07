import com.in6k.MonthCalendar.MonthCalendar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        MonthCalendar calendar = new MonthCalendar();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your today in format YYYY-MM-DD : \n");
        calendar.setLocalDate(LocalDate.parse(sc.next()));
        sc.close();

        System.out.print(calendar.getStringCalendar(false));
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        writer.println(calendar.getStringCalendar(false));
        writer.close();
    }


}