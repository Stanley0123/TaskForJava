package com.digdes.school;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String update1 = "update values 'lAstname' = '   where    '";
        System.out.println(Pattern.compile("[^'][^lL][^aA][^sS][^tT][^nN][^aA][^mM][^eE][^']\\s*[^=]\\s*(\\b[^']\\s*(?i)where\\s*[^']\\b)").matcher(update1).find());
    }
}
