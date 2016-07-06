import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class MyCalendar
{
    private LocalDate date;

    public static final String yellow = "\033[33m";
    public static final String red = "\033[31m";
    public static final String black = "\033[37m";

    public LocalDate getDate() {
        return date;
    }

    public MyCalendar()
    {}


    public String getStringCalendar() {
        String calendar = "";
        //calendar += "\tMon\tTue\tWed\tThu\tFri\tSat\tSun\n";
        for (int i = 0; i <= 6; i++) {
            calendar += (DayOfWeek.values())[i].getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t";
        }

        calendar += "\n";
        for (int i = 1; i < DayOfWeek.from(this.date.withDayOfMonth(1)).getValue(); i++) {
            calendar += "\t";
        }

        for (int dayOfMonthNumber = 1; dayOfMonthNumber <= this.date.lengthOfMonth(); dayOfMonthNumber++) {

            calendar += getColor(this.date, dayOfMonthNumber) + dayOfMonthNumber + "\t";

            if (DayOfWeek.from(this.date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                calendar += "\n";
            }
        }
        return calendar;
    }

    public  String getColor(LocalDate date, int dayOfMonthNumber) {
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            return yellow; // Yellow color
        } else {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                    || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                return red; // Red color
            } else {
                return black;
            }
        }
    }

    public void setLocalDate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your date in format YYYY-MM-DD : \n");
        this.date = LocalDate.parse(sc.next());
        sc.close();
    }

    public void setLocalDate(LocalDate date) {
        this.date = date;
    }
}
