package com.in6k.HTMLDocument;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class HTMLDocument {
    private String document = "";

    public HTMLDocument() {
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

    public void generateDocument(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName + ".html", "UTF-8");
        writer.println(getDocument());
        writer.close();
    }
}

