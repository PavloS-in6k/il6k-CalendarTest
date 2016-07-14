package com.in6k.HtmlDocument;

public class HtmlDocument {
    private String document = "";

    public HtmlDocument() {
        document +=
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"Styles.css\">\n" +
                        "</head>\n" +
                        "<body>\n";
    }

    public void addToDocument(String value) {
        document += value;
    }

    public String getDocument() {
        return document + "</body>" + "\n" + "</html>";
    }
}

