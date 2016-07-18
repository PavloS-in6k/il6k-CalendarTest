package com.in6k.MonthCalendar.OutputStrategy.Html;

import com.in6k.MonthCalendar.OutputStrategy.Output;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class HtmlOutput implements Output {
    @Override
    public String getDayOfWeekWeekendName(DayOfWeek day) {
        return wrapContentInTag((day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)),
                HtmlTags.CELL, CssHighlighthClass.WEEKEND);
    }

    @Override
    public String getDayOfWeekWorkName(DayOfWeek day) {
        return wrapContentInTag((day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)),
                HtmlTags.CELL, CssHighlighthClass.WORK);
    }

    @Override
    public String getEmptyPartOfCalendar() {
        return wrapContentInTag("", HtmlTags.CELL, CssHighlighthClass.WORK);
    }

    @Override
    public String getHighlightedDayToday(int dayOfMonthNumber) {
        return wrapContentInTag(String.valueOf(dayOfMonthNumber), HtmlTags.CELL, CssHighlighthClass.TODAY);
    }

    @Override
    public String getHighlightedDayWork(int dayOfMonthNumber) {
        return wrapContentInTag(String.valueOf(dayOfMonthNumber), HtmlTags.CELL, CssHighlighthClass.WORK);
    }

    @Override
    public String getHighlightedDayWeekend(int dayOfMonthNumber) {
        return wrapContentInTag(String.valueOf(dayOfMonthNumber), HtmlTags.CELL, CssHighlighthClass.WEEKEND);
    }

    @Override
    public String getOpenLineTag() {
        return "<" + HtmlTags.TABLE_ROW + ">";
    }

    @Override
    public String getCloseLineTag() {
        return "</" + HtmlTags.TABLE_ROW + ">" + "\n";
    }

    @Override
    public String getOpenInfo() {
        return "<" + HtmlTags.TABLE + ">" + "\n";
    }

    @Override
    public String getCloseInfo() {
        return "</" + HtmlTags.TABLE + ">" + "\n";
    }

    private String wrapContentInTag(String content, String tag) {
        return "<" + tag + ">" + content + "</" + tag + ">";
    }

    private String wrapContentInTag(String content, String tag, String cssClass) {
        return "<" + tag + " class=\"" + cssClass + "\">" + content + "</" + tag + ">";
    }
}
