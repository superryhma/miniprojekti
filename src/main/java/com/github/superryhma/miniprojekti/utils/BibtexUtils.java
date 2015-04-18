package com.github.superryhma.miniprojekti.utils;

import java.util.HashMap;
import java.util.Map;

public class BibtexUtils {
    private static final Map<Character, String> CHARACTER_MAP = new HashMap<Character, String>() {
        {
            put('ä', "\\\"{a}");
            put('ö', "\\\"{o}");
        }
    };

    public static String escapeBiBTeXCharacter(char c) {
        return CHARACTER_MAP.getOrDefault(c, "" + c);
    }

    public static String escapeBiBTeXString(String s) {
        StringBuilder str = new StringBuilder();
        for (char c : s.toCharArray()) {
            str.append(escapeBiBTeXCharacter(c));
        }
        return str.toString();
    }
}
