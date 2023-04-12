package com.digdes.school;

import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyPredicate {
    public static Predicate<Map<String, Object>> lastNameToPredicate(String str) {
        Predicate<Map<String, Object>> predicate = null;
        String regex = "^\\s*'(?i)lastName'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int startSign = matcher.end();
            String bufferString = str.substring(startSign, str.length());
            if (bufferString.matches("\\s*=\\s*('[^',%]*'|" + RegexContainer.nullWord +")\\s*")) {
                if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                    predicate = x -> x.get("lastName") == null;
                } else {
                    Pattern pattern2 = Pattern.compile("'[^',%]*'");
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        String substring = bufferString.substring(start + 1, finale - 1);
                        predicate = x -> {
                            boolean flag = false;
                            if (x.get("lastName") != null) {
                                flag = x.get("lastName").equals(substring);
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s*!=\\s*('[^',%]*'|" + RegexContainer.nullWord +")\\s*")) {
                Pattern pattern1 = Pattern.compile(RegexContainer.nullWord);
                Matcher matcher1 = pattern1.matcher(bufferString);
                if (matcher1.find()) {
                    predicate = x -> x.get("lastName") != null;
                } else {
                    Pattern pattern2 = Pattern.compile("'[^',%]*'");
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        String substring = bufferString.substring(start + 1, finale - 1);
                        predicate = x -> {
                            boolean flag = true;
                            if (x.get("lastName") != null) {
                                flag = !(x.get("lastName").equals(substring));
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s+like\\s+'[%]?[^',%]*[%]?'\\s*")) {
                Pattern pattern2 = Pattern.compile("'[^',%]*'");
                Matcher matcher2 = pattern2.matcher(bufferString);
                Pattern pattern3 = Pattern.compile("'%[^',%]*'");
                Matcher matcher3 = pattern3.matcher(bufferString);
                Pattern pattern4 = Pattern.compile("'[^',%]*%'");
                Matcher matcher4 = pattern4.matcher(bufferString);
                Pattern pattern5 = Pattern.compile("'%[^',%]*%'");
                Matcher matcher5 = pattern5.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    String substring = bufferString.substring(start + 1, finale - 1);
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("lastName") != null) {
                            flag = x.get("lastName").equals(substring);
                        }
                        return flag;
                    };
                } else if (matcher3.find()) {
                    int start = matcher3.start();
                    int finale = matcher3.end();
                    String substring = bufferString.substring(start + 2, finale - 1);
                    String test = "[^',%]*" + substring;
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("lastName") != null) {
                            flag = x.get("lastName").toString().matches(test);
                        }
                        return flag;
                    };
                } else if (matcher4.find()) {
                    int start = matcher4.start();
                    int finale = matcher4.end();
                    String substring = bufferString.substring(start + 1, finale - 2);
                    String test = substring + "[^',%]*";
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("lastName") != null) {
                            flag = x.get("lastName").toString().matches(test);
                        }
                        return flag;
                    };
                } else if (matcher5.find()) {
                    int start = matcher5.start();
                    int finale = matcher5.end();
                    String substring = bufferString.substring(start + 2, finale - 2);
                    String test = "[^',%]*" + substring + "[^',%]*";
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("lastName") != null) {
                            flag = x.get("lastName").toString().matches(test);
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s+ilike\\s+'[%]?[^',%]*[%]?'\\s*")) {
                Pattern pattern2 = Pattern.compile("'[^',%]*'");
                Matcher matcher2 = pattern2.matcher(bufferString);
                Pattern pattern3 = Pattern.compile("'%[^',%]*'");
                Matcher matcher3 = pattern3.matcher(bufferString);
                Pattern pattern4 = Pattern.compile("'[^',%]*%'");
                Matcher matcher4 = pattern4.matcher(bufferString);
                Pattern pattern5 = Pattern.compile("'%[^',%]*%'");
                Matcher matcher5 = pattern5.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    String substring = bufferString.substring(start + 1, finale - 1);
                    String test = substring.toLowerCase();
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("lastName") != null) {
                            flag = x.get("lastName").toString().toLowerCase().matches(test);
                        }
                        return flag;
                    };
                } else if (matcher3.find()) {
                    int start = matcher3.start();
                    int finale = matcher3.end();
                    String substring = bufferString.substring(start + 2, finale - 1);
                    String test = "[^',%]*" + substring.toLowerCase();
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("lastName") != null) {
                            flag = x.get("lastName").toString().toLowerCase().matches(test);
                        }
                        return flag;
                    };
                } else if (matcher4.find()) {
                    int start = matcher4.start();
                    int finale = matcher4.end();
                    String substring = bufferString.substring(start + 1, finale - 2);
                    String test = substring.toLowerCase() + "[^',%]*";
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("lastName") != null) {
                            flag = x.get("lastName").toString().toLowerCase().matches(test);
                        }
                        return flag;
                    };
                } else if (matcher5.find()) {
                    int start = matcher5.start();
                    int finale = matcher5.end();
                    String substring = bufferString.substring(start + 2, finale - 2);
                    String test = "[^',%]*" + substring.toLowerCase() + "[^',%]*";
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("lastName") != null) {
                            flag = x.get("lastName").toString().toLowerCase().matches(test);
                        }
                        return flag;
                    };
                }
            }
        }
        return predicate;
    }

    public static Predicate<Map<String, Object>> idToPredicate(String str) {
        Predicate<Map<String, Object>> predicate = null;
        String regex = "^\\s*'(?i)id'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int startSign = matcher.end();
            String bufferString = str.substring(startSign, str.length());
            if (bufferString.matches("\\s*=\\s*(\\d+|" + RegexContainer.nullWord + ")\\s*")) {
                if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                    predicate = x -> x.get("id") == null;
                } else {
                    Pattern pattern2 = Pattern.compile("(\\d+)");
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        Long substring = Long.parseLong(bufferString.substring(start, finale));
                        predicate = x -> {
                            boolean flag = false;
                            if (x.get("id") != null) {
                                flag = x.get("id").equals(substring);
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s*!=\\s*(\\d+|" + RegexContainer.nullWord + ")\\s*")) {
                Pattern pattern1 = Pattern.compile(RegexContainer.nullWord);
                Matcher matcher1 = pattern1.matcher(bufferString);
                if (matcher1.find()) {
                    predicate = x -> x.get("id") != null;
                } else {
                    Pattern pattern2 = Pattern.compile("(\\d+)");
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        Long substring = Long.parseLong(bufferString.substring(start, finale));
                        predicate = x -> {
                            boolean flag = true;
                            if (x.get("id") != null) {
                                flag = !(x.get("id").equals(substring));
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s*>\\s*(\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Long substring = Long.parseLong(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("id") != null) {
                            flag = ((Long) x.get("id")) > substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*<\\s*(\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Long substring = Long.parseLong(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("id") != null) {
                            flag = ((Long) x.get("id")) < substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*>=\\s*(\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Long substring = Long.parseLong(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("id") != null) {
                            flag = ((Long) x.get("id")) >= substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*<=\\s*(\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Long substring = Long.parseLong(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("id") != null) {
                            flag = ((Long) x.get("id")) <= substring;
                        }
                        return flag;
                    };
                }
            }
        }
        return predicate;
    }

    public static Predicate<Map<String, Object>> costToPredicate(String str) {
        Predicate<Map<String, Object>> predicate = null;
        String regex = "^\\s*'(?i)cost'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int startSign = matcher.end();
            String bufferString = str.substring(startSign, str.length());
            if (bufferString.matches("\\s*=\\s*(\\d+[.]\\d+|" + RegexContainer.nullWord + ")\\s*")) {
                if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                    predicate = x -> x.get("cost") == null;
                } else {
                    Pattern pattern2 = Pattern.compile("(\\d+[.]\\d+)");
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        Double substring = Double.parseDouble(bufferString.substring(start, finale));
                        predicate = x -> {
                            boolean flag = false;
                            if (x.get("cost") != null) {
                                flag = x.get("cost").equals(substring);
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s*!=\\s*(\\d+[.]\\d+|" + RegexContainer.nullWord + ")\\s*")) {
                Pattern pattern1 = Pattern.compile(RegexContainer.nullWord);
                Matcher matcher1 = pattern1.matcher(bufferString);
                if (matcher1.find()) {
                    predicate = x -> x.get("cost") != null;
                } else {
                    Pattern pattern2 = Pattern.compile("(\\d+[.]\\d+)");
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        Double substring = Double.parseDouble(bufferString.substring(start, finale));
                        predicate = x -> {
                            boolean flag = true;
                            if (x.get("cost") != null) {
                                flag = !(x.get("cost").equals(substring));
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s*>\\s*(\\d+[.]\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+[.]\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Double substring = Double.parseDouble(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("cost") != null) {
                            flag = ((Double) x.get("cost")) > substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*<\\s*(\\d+[.]\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+[.]\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Double substring = Double.parseDouble(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("cost") != null) {
                            flag = ((Double) x.get("cost")) < substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*>=\\s*(\\d+[.]\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+[.]\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Double substring = Double.parseDouble(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("cost") != null) {
                            flag = ((Double) x.get("cost")) >= substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*<=\\s*(\\d+[.]\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+[.]\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Double substring = Double.parseDouble(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("cost") != null) {
                            flag = ((Double) x.get("cost")) <= substring;
                        }
                        return flag;
                    };
                }
            }
        }
        return predicate;
    }

    public static Predicate<Map<String, Object>> ageToPredicate(String str) {
        Predicate<Map<String, Object>> predicate = null;
        String regex = "^\\s*'(?i)age'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int startSign = matcher.end();
            String bufferString = str.substring(startSign, str.length());
            if (bufferString.matches("\\s*=\\s*(\\d+|" + RegexContainer.nullWord + ")\\s*")) {
                if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                    predicate = x -> x.get("id") == null;
                } else {
                    Pattern pattern2 = Pattern.compile("(\\d+)");
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        Long substring = Long.parseLong(bufferString.substring(start, finale));
                        predicate = x -> {
                            boolean flag = false;
                            if (x.get("age") != null) {
                                flag = x.get("age").equals(substring);
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s*!=\\s*(\\d+|" + RegexContainer.nullWord + ")\\s*")) {
                Pattern pattern1 = Pattern.compile(RegexContainer.nullWord);
                Matcher matcher1 = pattern1.matcher(bufferString);
                if (matcher1.find()) {
                    predicate = x -> x.get("age") != null;
                } else {
                    Pattern pattern2 = Pattern.compile("(\\d+)");
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        Long substring = Long.parseLong(bufferString.substring(start, finale));
                        predicate = x -> {
                            boolean flag = true;
                            if (x.get("age") != null) {
                                flag = !(x.get("age").equals(substring));
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s*>\\s*(\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Long substring = Long.parseLong(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("age") != null) {
                            flag = ((Long) x.get("age")) > substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*<\\s*(\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Long substring = Long.parseLong(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("age") != null) {
                            flag = ((Long) x.get("age")) < substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*>=\\s*(\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Long substring = Long.parseLong(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("age") != null) {
                            flag = ((Long) x.get("age")) >= substring;
                        }
                        return flag;
                    };
                }
            } else if (bufferString.matches("\\s*<=\\s*(\\d+)\\s*")) {
                Pattern pattern2 = Pattern.compile("(\\d+)");
                Matcher matcher2 = pattern2.matcher(bufferString);
                if (matcher2.find()) {
                    int start = matcher2.start();
                    int finale = matcher2.end();
                    Long substring = Long.parseLong(bufferString.substring(start, finale));
                    predicate = x -> {
                        boolean flag = false;
                        if (x.get("age") != null) {
                            flag = ((Long) x.get("age")) <= substring;
                        }
                        return flag;
                    };
                }
            }
        }
        return predicate;
    }

    public  static Predicate<Map<String, Object>> activeToPredicate(String str) {
        Predicate<Map<String, Object>> predicate = null;
        String regex = "^\\s*'(?i)active'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int startSign = matcher.end();
            String bufferString = str.substring(startSign, str.length());
            if (bufferString.matches("\\s*=\\s*" + RegexContainer.groupTrueFalseNull + "\\s*")) {
                if (Pattern.compile(RegexContainer.nullWord).matcher(bufferString).find()) {
                    predicate = x -> x.get("active") == null;
                } else {
                    Pattern pattern2 = Pattern.compile(RegexContainer.trueFalseWord);
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        Boolean substring = Boolean.valueOf(bufferString.substring(start, finale));
                        predicate = x -> {
                            boolean flag = false;
                            if (x.get("active") != null) {
                                flag = x.get("active").equals(substring);
                            }
                            return flag;
                        };
                    }
                }
            } else if (bufferString.matches("\\s*!=\\s*" + RegexContainer.groupTrueFalseNull + "\\s*")) {
                Pattern pattern1 = Pattern.compile(RegexContainer.nullWord);
                Matcher matcher1 = pattern1.matcher(bufferString);
                if (matcher1.find()) {
                    predicate = x -> x.get("active") != null;
                } else {
                    Pattern pattern2 = Pattern.compile(RegexContainer.trueFalseWord);
                    Matcher matcher2 = pattern2.matcher(bufferString);
                    if (matcher2.find()) {
                        int start = matcher2.start();
                        int finale = matcher2.end();
                        Boolean substring = Boolean.valueOf(bufferString.substring(start, finale));
                        predicate = x -> {
                            boolean flag = true;
                            if (x.get("active") != null) {
                                flag = !(x.get("active").equals(substring));
                            }
                            return flag;
                        };
                    }
                }
            }
        }
        return predicate;
    }
}

