package com.in6k.MonthCalendar.OutputStrategy.HTML;

import com.in6k.MonthCalendar.OutputStrategy.Output;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class HTMLOutput implements Output {
    @Override
    public String getDayOfWeekWeekendName(DayOfWeek day) {
        return wrapContentInTag((day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)),
                HTMLTags.CELL, CSSHighlighthClass.WEEKEND);
    }

    @Override
    public String getDayOfWeekWorkName(DayOfWeek day) {
        return wrapContentInTag((day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)),
                HTMLTags.CELL, CSSHighlighthClass.WORK);
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
    public String getHighlightedDayToday(int dayOfMonthNumber) {
        return wrapContentInTag(String.valueOf(dayOfMonthNumber), HTMLTags.CELL, CSSHighlighthClass.TODAY);
    }

    @Override
    public String getHighlightedDayWork(int dayOfMonthNumber) {
        return wrapContentInTag(String.valueOf(dayOfMonthNumber), HTMLTags.CELL, CSSHighlighthClass.WORK);
    }

    @Override
    public String getHighlightedDayWeekend(int dayOfMonthNumber) {
        return wrapContentInTag(String.valueOf(dayOfMonthNumber), HTMLTags.CELL, CSSHighlighthClass.WEEKEND);
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
