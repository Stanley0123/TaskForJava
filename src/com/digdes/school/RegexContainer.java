package com.digdes.school;

public class RegexContainer {
    public static final String insert = "^\\s*(?i)insert\\s*";
    public static final String select = "^\\s*(?i)select\\s*";
    public static final String update = "^\\s*(?i)update\\s*";
    public static final String delete = "^\\s*(?i)delete\\s*";
    public static final String whereWord = "(\\s+\\b(?i)where\\b\\s+)";
    public static final String valuesWord = "(\\b(?i)values\\b)";
    public static final String andWord = "^\\s*(\\b(?i)AND\\b)";
    public static final String orWord = "^\\s*(\\b(?i)OR\\b)";
    public static final String orAndWord = "(\\b[^']\\s*((?i)AND|(?i)OR)\\s*[^']\\b)";
    public static final String nullWord = "\\s*\\b((?i)null)\\b\\s*";
    public static final String trueFalseWord = "\\b((?i)true|(?i)false)\\b";
    public static final String groupTrueFalseNull = "\\b((?i)true|(?i)false|(?i)null)\\b";


    //-------------------------------------------
    public static final String idEqual = "(\\s*'(?i)id'\\s*=\\s*(\\d+|null)\\s*)";
    public static final String lastNameEqual = "(\\s*'(?i)lastName'\\s*=\\s*('[^',%]*'|null)\\s*)";
    public static final String ageEqual = "(\\s*'(?i)age'\\s*=\\s*(\\d+|null)\\s*)";
    public static final String costEqual = "(\\s*(?i)'cost'\\s*=\\s*(\\d+[.]\\d+|null)\\s*)";
    public static final String activeEqual = "(\\s*'(?i)active'\\s*=\\s*(true|false|null)\\s*)";
    public static final String groupEquals = idEqual + "|" + costEqual + "|" + lastNameEqual + "|" + ageEqual + "|" + activeEqual;


    public static final String idCondition = "(\\s*'(?i)id'\\s*(((=|!=)\\s*null)|((=|!=|>=|<=|>|<)\\s*(\\d+)))\\s*)";
    public static final String ageCondition = "(\\s*'(?i)age'\\s*(((=|!=)\\s*null)|((=|!=|>=|<=|>|<)\\s*(\\d+)))\\s*)";
    public static final String activeCondition = "(\\s*'(?i)active'\\s*(=|!=)\\s*(true|false|null)\\s*)";
    public static final String costCondition = "(\\s*(?i)'cost'\\s*(((=|!=)\\s*null)|((=|!=|>=|<=|>|<)\\s*(\\d+[.]\\d+)))\\s*)";
    public static final String likeOrIlikeOperator = "((?i)(like|ilike)\\s*('[%]?[^',%]*[%]?'\\s*))";
    public static final String lastNameCondition = "(\\s*'(?i)lastName'\\s*(((=|!=)\\s*('[^',%]*'|null))|"+likeOrIlikeOperator+")\\s*)";
    //группа условий не включающая скобки
    public static final String groupCondition = idCondition + "|" + ageCondition + "|" + activeCondition + "|" + costCondition + "|" + lastNameCondition;
   //группа условий включающая скобки
    public static final String groupConditionAndBracket = "\\s*[(]*\\s*(" + idCondition + "|" + ageCondition + "|" + activeCondition + "|" + costCondition + "|" + lastNameCondition + ")\\s*[)]*\\s*";
    //-------------------------------------------
    //необязательный where
    public static final String whereCondition = "(\\s+(?i)where\\s+[(]*\\s*(" + groupCondition + ")\\s*[)]*\\s*)?";
    //обязательный where
    public static final String whereNecessaryCondition = "(\\s+(?i)where\\s+[(]*\\s*(" + groupCondition + ")\\s*[)]*\\s*)";
    public static final String valuesEquals = "\\s+(?i)values\\s+(" + groupEquals + ")\\s*";
    public static final String values = "\\s+(?i)values\\s+";
}