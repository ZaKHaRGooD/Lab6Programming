package com.lab6.client.utils;

import java.util.ArrayList;

/**
 * Класс для разделения строки с учетом "\".
 */
public final class Splitter {
    private Splitter() {

    }

    public static String[] smartSplit(String line) {
        ArrayList<String> splitLine = new ArrayList<>();
        StringBuilder currentString = new StringBuilder();
        boolean screeningStarted = false;
        for (char ch : line.toCharArray()) {
            if (ch == ' ' && !screeningStarted) {
                if (!"".equals(currentString.toString())) {
                    splitLine.add(currentString.toString());
                }
                currentString.setLength(0);
            } else if (ch == '"') {
                screeningStarted = !screeningStarted;
            } else {
                currentString.append(ch);
            }
        }
        splitLine.add(currentString.toString());
        String[] args = new String[splitLine.size()];
        splitLine.toArray(args);
        return args;
    }
}
