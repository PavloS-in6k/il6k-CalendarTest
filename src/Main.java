import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        MyCalendar calendar = new MyCalendar();
        calendar.setLocalDate();
        System.out.print(calendar.getStringCalendar());
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        writer.println(calendar.getStringCalendar());
        writer.close();
    }


}

