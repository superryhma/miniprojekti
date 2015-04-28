package com.github.superryhma.miniprojekti.utils;

import com.google.common.collect.AbstractIterator;

public class Sequence extends AbstractIterator<String> {
    private int current;
    private final int CHARSET_LENGTH = 'z' - 'a' + 1;

    public Sequence() {
        current = 0;
    }

    @Override
    public String computeNext() {
        int i = current;
        current++;
        if(current == 1)
            return "";
        StringBuilder stringBuilder = new StringBuilder();
        while(i > 0) {
            i--;
            stringBuilder.append((char)(i % CHARSET_LENGTH + 'a'));
            i /= CHARSET_LENGTH;
        }
        return stringBuilder.reverse().toString();
    }
}
