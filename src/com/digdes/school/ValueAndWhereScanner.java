package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueAndWhereScanner {
    public Map<String, Object> valuesScanner(String str) {
        Map<String, Object> map = new HashMap<>();
        String[] massive = str.split(",");
        for (int i = 0; i < massive.length; i++){
            if (massive[i].matches(RegexContainer.idCondition)) {
                String regex = "^\\s*'(?i)id'\\s*=\\s*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(massive[i]);
                if (matcher.find()) {
                    int startSign = matcher.end();
                    String bufferString = massive[i].substring(startSign, massive[i].length());
                    if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                        map.put("id", null);
                    } else {
                        Pattern pattern2 = Pattern.compile("(\\d+)");
                        Matcher matcher2 = pattern2.matcher(bufferString);
                        if (matcher2.find()) {
                            int start = matcher2.start();
                            int finale = matcher2.end();
                            Long substring = Long.parseLong(bufferString.substring(start, finale));
                            map.put("id", substring);
                        }
                    }
                }
            } else if (massive[i].matches(RegexContainer.lastNameCondition)) {
                String regex = "^\\s*'(?i)lastName'\\s*=\\s*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(massive[i]);
                if (matcher.find()) {
                    int startSign = matcher.end();
                    String bufferString = massive[i].substring(startSign, massive[i].length());
                    if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                        map.put("lastName", null);
                    } else {
                        Pattern pattern2 = Pattern.compile("'[^',%]*'");
                        Matcher matcher2 = pattern2.matcher(bufferString);
                        if (matcher2.find()) {
                            int start = matcher2.start();
                            int finale = matcher2.end();
                            String substring = bufferString.substring(start + 1, finale - 1);
                            map.put("lastName", substring);
                        }
                    }
                }
            } else if (massive[i].matches(RegexContainer.costCondition)) {
                String regex = "^\\s*'(?i)cost'\\s*=\\s*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(massive[i]);
                if (matcher.find()) {
                    int startSign = matcher.end();
                    String bufferString = massive[i].substring(startSign, massive[i].length());
                    if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                        map.put("cost", null);
                    } else {
                        Pattern pattern2 = Pattern.compile("(\\d+[.]\\d+)");
                        Matcher matcher2 = pattern2.matcher(bufferString);
                        if (matcher2.find()) {
                            int start = matcher2.start();
                            int finale = matcher2.end();
                            Double substring = Double.parseDouble(bufferString.substring(start, finale));
                            map.put("cost", substring);
                        }
                    }
                }
            }
            else if (massive[i].matches(RegexContainer.ageCondition)) {
                String regex = "^\\s*'(?i)age'\\s*=\\s*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(massive[i]);
                if (matcher.find()) {
                    int startSign = matcher.end();
                    String bufferString = massive[i].substring(startSign, massive[i].length());
                    if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                        map.put("age", null);
                    } else {
                        Pattern pattern2 = Pattern.compile("(\\d+)");
                        Matcher matcher2 = pattern2.matcher(bufferString);
                        if (matcher2.find()) {
                            int start = matcher2.start();
                            int finale = matcher2.end();
                            Long substring = Long.parseLong(bufferString.substring(start, finale));
                            map.put("age", substring);
                        }
                    }
                }
            } else if (massive[i].matches(RegexContainer.activeCondition)) {
                String regex = "^\\s*'(?i)active'\\s*=\\s*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(massive[i]);
                if (matcher.find()) {
                    int startSign = matcher.end();
                    String bufferString = massive[i].substring(startSign, massive[i].length());
                    if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                        map.put("active", null);
                    } else {
                        Pattern pattern2 = Pattern.compile(RegexContainer.trueFalseWord);
                        Matcher matcher2 = pattern2.matcher(bufferString);
                        if (matcher2.find()) {
                            int start = matcher2.start();
                            int finale = matcher2.end();
                            Boolean substring = Boolean.valueOf(bufferString.substring(start, finale));
                            map.put("active", substring);
                        }
                    }
                }
            }
        }
        return map;
    }

    public Predicate<Map<String, Object>> whereScanner(String str){
        Predicate<Map<String, Object>> predicate = null;
        String buffered = str.strip();
        buffered = deleteExternal(buffered);
        boolean factor = true;
        List<Predicate<Map<String, Object>>> listOr = new ArrayList<>();
        List<Predicate<Map<String, Object>>> listAnd = new ArrayList<>();
        while (factor){
            Predicate<Map<String, Object>> argument = null;
            Matcher mBracket = Pattern.compile("^\\s*[(]").matcher(buffered);
            Matcher mAttribute = Pattern.compile("^\\s*" + RegexContainer.groupCondition).matcher(buffered);
            if (mBracket.find()) {
                int finale = bracketInt(buffered);
                int start = mBracket.start();
                argument = whereScanner(buffered.substring(start, finale));
                buffered = buffered.substring(finale, buffered.length());

            }
            else if (mAttribute.find()){
                int finale = mAttribute.end();
                int start = mAttribute.start();
                String stringArg = buffered.substring(start, finale);
                if (stringArg.matches(RegexContainer.idCondition)){
                    argument = MyPredicate.idToPredicate(stringArg);
                } else if (stringArg.matches(RegexContainer.lastNameCondition)){
                    argument = MyPredicate.lastNameToPredicate(stringArg);
                }
                else if (stringArg.matches(RegexContainer.costCondition)){
                    argument = MyPredicate.costToPredicate(stringArg);
                }
                else if (stringArg.matches(RegexContainer.ageCondition)){
                    argument = MyPredicate.ageToPredicate(stringArg);
                }
                else if (stringArg.matches(RegexContainer.activeCondition)){
                    argument = MyPredicate.activeToPredicate(stringArg);
                }
                buffered = buffered.substring(finale, buffered.length());
            }
            Matcher mOr = Pattern.compile(RegexContainer.orWord).matcher(buffered);
            Matcher mAnd = Pattern.compile(RegexContainer.andWord).matcher(buffered);
            //a and b and c and d //or t //and y
            if (mOr.find()){
                listAnd.add(argument);
                listOr.add(and(listAnd));//*****
                listAnd.clear();
                int finaleString = mOr.end();
                buffered = buffered.substring(finaleString, buffered.length());
            }
            else if (mAnd.find()){
                listAnd.add(argument);
                int finaleString = mAnd.end();
                buffered = buffered.substring(finaleString, buffered.length());
            }
            else {
                listAnd.add(argument);
                factor = false;
            }
        }
        listOr.add(and(listAnd));
        listAnd.clear();
        predicate = or(listOr);
        return  predicate;
    }
    public  boolean isThisExternalBracket(String str){
        boolean val = false;
        short sizeIndex = 0;
        String regex = "^\\s*[(]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            int start = matcher.start();
            String substring = str.substring(start, str.length());
            int properlyBracketCounter = 0;
            for (int i = 0; i < substring.length(); i++){
                char symbol = substring.charAt(i);
                if (symbol == '('){
                    properlyBracketCounter++;
                }
                else if (symbol == ')'){
                    properlyBracketCounter--;
                }
                sizeIndex++;
                if (properlyBracketCounter == 0){
                    break;
                }
            }
            if (sizeIndex == substring.length()){
                val = true;
            } else if (substring.substring(sizeIndex, substring.length()).matches("^\\s+$")) {
                val = true;
            }
        }

        return val;
    }

    public  int bracketInt(String str){
        boolean val = false;
        short sizeIndex = 0;
        String regex = "[(]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        int start = matcher.start();
        int properlyBracketCounter = 0;
        for (int i = start; i < str.length(); i++){
            char symbol = str.charAt(i);
            if (symbol == '('){
                properlyBracketCounter++;
            }
            else if (symbol == ')'){
                properlyBracketCounter--;
            }
            sizeIndex++;
            if (properlyBracketCounter == 0){
                break;
            }
        }
        return start + sizeIndex;
    }

    public  String deleteExternal(String str){
        boolean factor = true;
        while (factor){
            str = str.strip();
            if (isThisExternalBracket(str)){
                str = str.substring(1, str.length()-1);
            }
            else {
                factor = false;
            }
        }
        return str;
    }


    public  Predicate<Map<String, Object>> or(List<Predicate<Map<String, Object>>> list) {
//        Predicate<Map<String, Object>> predicate = list.get(0);
//        for (int i = 1; i < list.size(); i++){
//            predicate = predicate.or(list.get(i));
//        }
        return list.stream().reduce(Predicate::or).orElse(x -> false);
    }

    protected  Predicate<Map<String, Object>> and(List<Predicate<Map<String, Object>>> list) {
//        Predicate<Map<String, Object>> predicate = list.get(0);
//        for (int i = 1; i < list.size(); i++){
//            predicate = predicate.or(list.get(i));
//        }
        return list.stream().reduce(Predicate::and).orElse(x -> true);
    }
}
