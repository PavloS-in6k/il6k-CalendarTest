package com.in6k.MonthCalendar.OutputStrategy.HTML;

import com.in6k.MonthCalendar.OutputStrategy.Output;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class HTMLOutput implements Output {
    @Override
    public String getDaysOfWeekNames() {
        String result = "";
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {
                result += wrapContentInTag((day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)),
                        HTMLTags.CELL, CSSHighlighthClass.WEEKEND);
            } else {
                result += wrapContentInTag((day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)),
                        HTMLTags.CELL, CSSHighlighthClass.WORK);
            }
        }
        result = wrapContentInTag(result, HTMLTags.TABLE_ROW);
        return result;
    }

    @Override
    public String getEmptyPartOfCalendar(LocalDate date) {
        String forTabs = "";
        for (int i = 1; i < DayOfWeek.from(date.withDayOfMonth(1)).getValue(); i++) {
            forTabs += wrapContentInTag("", HTMLTags.CELL, CSSHighlighthClass.WORK);
        }
        return forTabs;
    }

    @Override
    public String getHighlightedDay(LocalDate date, int dayOfMonthNumber) {
        String cssClass = "";
        if (date.getDayOfMonth() == dayOfMonthNumber) {
            cssClass = CSSHighlighthClass.TODAY;
        } else {
            if (DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SATURDAY
                    || DayOfWeek.from(date.withDayOfMonth(dayOfMonthNumber)) == DayOfWeek.SUNDAY) {
                cssClass = CSSHighlighthClass.WEEKEND;
            } else {
                cssClass = CSSHighlighthClass.WORK;
            }
        }
        return wrapContentInTag(String.valueOf(dayOfMonthNumber), HTMLTags.CELL, cssClass);
    }

    @Override
    public String getOpenLineTag() {
        return "<" + HTMLTags.TABLE_ROW + ">";
    }

    @Override
    public String getCloseLineTag() {
        return "</" + HTMLTags.TABLE_ROW + ">" + "\n";
    }

    @Override
    public String getOpenInfo() {
        return "<" + HTMLTags.TABLE + ">" + "\n";
    }

    @Override
    public String getCloseInfo() {
        return "</" + HTMLTags.TABLE + ">" + "\n";
    }

    private String wrapContentInTag(String content, String tag) {
        return "<" + tag + ">" + content + "</" + tag + ">";
    }

    private String wrapContentInTag(String content, String tag, String cssClass) {
        return "<" + tag + " class=\"" + cssClass + "\">" + content + "</" + tag + ">";
    }
}
