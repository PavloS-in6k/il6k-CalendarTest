import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class MyCalendar {
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public MyCalendar() {
    }


    public String getStringCalendar() {
        String calendar = "";
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

    public String getColor(LocalDate date, int dayOfMonthNumber) {
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            return DayColor.TODAY_TEXTCODE;
        } else {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                    || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                return DayColor.WEEKEND_TEXTCODE;
            } else {
                return DayColor.WORK_TEXTCODE;
            }
        }
    }


    public void setLocalDate(LocalDate date) {
        this.date = date;
    }
}
